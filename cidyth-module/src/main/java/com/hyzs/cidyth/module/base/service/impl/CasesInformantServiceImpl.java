package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.module.base.dao.CasesInformantMapper;
import com.hyzs.cidyth.module.base.entity.CasesInformant;
import com.hyzs.cidyth.module.base.entity.CasesInformantCid;
import com.hyzs.cidyth.module.base.service.CasesInformantCidService;
import com.hyzs.cidyth.module.base.service.CasesInformantService;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesInformantService")
public class CasesInformantServiceImpl implements CasesInformantService {

	private static final Logger logger = LoggerFactory.getLogger(CasesInformantServiceImpl.class);

	@Autowired
	private CasesInformantMapper casesInformantMapper;
	@Autowired
	private CasesInformantCidService casesInformantCidService;

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh) {
		List<CasesInformantCid> lsCcasesInformantCid = casesInformantCidService.listCasesInformantByAjbh(ajbh);
		// 从警综库查询到，则插入
		if(lsCcasesInformantCid != null && lsCcasesInformantCid.size() > 0){
			for(CasesInformantCid casesInformantcid : lsCcasesInformantCid){
				if(!isExist(casesInformantcid.getSystemid())){
					CasesInformant casesInformant = new CasesInformant();
					BeanUtils.copyProperties(casesInformantcid, casesInformant);
					casesInformant.setAjbh(ajbh);
					casesInformantMapper.insert(casesInformant);
				}
			}
		}
	}

	@Override
	public List<CasesInformant> list(String ajbh) {
		CasesInformant casesInformantParam = new CasesInformant();
		casesInformantParam.setAjbh(ajbh);
		List<CasesInformant> lsCasesInformant = casesInformantMapper.select(casesInformantParam);
		return lsCasesInformant;
	}

	private boolean isExist(String systemid) {
		CasesInformant casesInformant = new CasesInformant();
		casesInformant.setSystemid(systemid);
		return casesInformantMapper.selectCount(casesInformant) > 0 ? true : false;
	}

}
