package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.module.base.dao.CasesLawBookMapper;
import com.hyzs.cidyth.module.base.entity.CasesLawBook;
import com.hyzs.cidyth.module.base.entity.CasesLawBookCid;
import com.hyzs.cidyth.module.base.service.CasesLawBookCidService;
import com.hyzs.cidyth.module.base.service.CasesLawBookService;
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
@Service("CasesLawBookService")
public class CasesLawBookServiceImpl implements CasesLawBookService {

	private static final Logger logger = LoggerFactory.getLogger(CasesLawBookServiceImpl.class);

	@Autowired
	private CasesLawBookMapper casesLawBookMapper;
	@Autowired
	private CasesLawBookCidService casesLawBookCidService;

	@Override
	public List<CasesLawBook> listCasesLawBookByAjbh(String ajbh) {
		CasesLawBook casesLawBook = new CasesLawBook();
		casesLawBook.setAjbh(ajbh);
		List<CasesLawBook> lsCasesLawBook = casesLawBookMapper.select(casesLawBook);
		return lsCasesLawBook;
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh) {
		List<CasesLawBookCid> lsCasesLawBookCid = casesLawBookCidService.listCasesLawBookByAjbh(ajbh);
		for(CasesLawBookCid casesLawBookCid : lsCasesLawBookCid){
			if(!isExist(casesLawBookCid.getWritid())){
				CasesLawBook casesLawBook = new CasesLawBook();
				BeanUtils.copyProperties(casesLawBookCid, casesLawBook);
				casesLawBook.setAjbh(ajbh);
				casesLawBookMapper.insert(casesLawBook);
			}
		}
	}

	private boolean isExist(String writid) {
		CasesLawBook casesLawBook = new CasesLawBook();
		casesLawBook.setWritid(writid);
		return casesLawBookMapper.selectCount(casesLawBook) > 0 ? true : false;
	}

}
