package com.hyzs.cidyth.module.base.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.CasesInformantCidMapper;
import com.hyzs.cidyth.module.base.entity.CasesInformantCid;
import com.hyzs.cidyth.module.base.service.CasesInformantCidService;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesInformantCidService")
public class CasesInformantCidServiceImpl implements CasesInformantCidService {

	private static final Logger logger = LoggerFactory.getLogger(CasesInformantCidServiceImpl.class);

	@Autowired
	private CasesInformantCidMapper casesInformantCidMapper;

	@Override
	public List<CasesInformantCid> listCasesInformantByAjbh(String ajbh) {
		return casesInformantCidMapper.listCasesInformantByAjbh(ajbh);
	}

	@Override
	public String getCasesInformantType(String rybh, String ajbh) {
		List<CasesInformantCid> lsCasesInformantCid = listCasesInformantByAjbh(ajbh);
		if(CollectionUtils.isNotEmpty(lsCasesInformantCid)){
			//CasesInformantCid casesInformantCid = lsCasesInformantCid.get(0);
			for(CasesInformantCid casesInformantCid : lsCasesInformantCid){
				if(StringUtils.isNotEmpty(casesInformantCid.getRybh()) && casesInformantCid.getRybh().equals(rybh)){
					if(StringUtils.isNotEmpty(casesInformantCid.getRylx())){
						if(casesInformantCid.getRylx().equals("01")){
							return "报案、受害人";
						}else if(casesInformantCid.getRylx().equals("02")){
							return "报案人";
						}else if(casesInformantCid.getRylx().equals("03")){
							return "受害人";
						}else {
							return "未知";
						}
					}
				}
			}
		}
		return null;
	}
}
