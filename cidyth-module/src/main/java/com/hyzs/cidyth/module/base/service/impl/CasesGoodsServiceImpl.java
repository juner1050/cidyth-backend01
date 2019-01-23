package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hyzs.cidyth.module.base.dao.CasesGoodsMapper;
import com.hyzs.cidyth.module.base.entity.CasesGoods;
import com.hyzs.cidyth.module.base.entity.CasesGoodsCid;
import com.hyzs.cidyth.module.base.service.CasesGoodsCidService;
import com.hyzs.cidyth.module.base.service.CasesGoodsService;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesGoodsService")
public class CasesGoodsServiceImpl implements CasesGoodsService {

	private static final Logger logger = LoggerFactory.getLogger(CasesGoodsServiceImpl.class);

	@Autowired
	private CasesGoodsMapper casesGoodsMapper;
	@Autowired
	private CasesGoodsCidService casesGoodsCidService;

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh) {
		CasesGoodsCid casesGoodsCidParam = new CasesGoodsCid();
		casesGoodsCidParam.setAjbh(ajbh);
		List<CasesGoodsCid> lsCasesGoodsResult = casesGoodsCidService.listCasesGoods(casesGoodsCidParam);
		// 从警综库查询到，则插入
		if(lsCasesGoodsResult != null && lsCasesGoodsResult.size() > 0){
			for(CasesGoodsCid casesGoodsCid : lsCasesGoodsResult){
				if(!isExist(casesGoodsCid.getWpbh())){
					CasesGoods casesGoods = new CasesGoods();
					BeanUtils.copyProperties(casesGoodsCid, casesGoods);
					casesGoodsMapper.insert(casesGoods);
				}
			}
		}
	}

	@Override
	public List<CasesGoods> list(String ajbh) {
		CasesGoods casesGoodsParam = new CasesGoods();
		casesGoodsParam.setAjbh(ajbh);
		List<CasesGoods> lsCasesGoods = casesGoodsMapper.select(casesGoodsParam);
		return lsCasesGoods;
	}

	private boolean isExist(String wpbh) {
		CasesGoods casesGoods = new CasesGoods();
		casesGoods.setWpbh(wpbh);
		return casesGoodsMapper.selectCount(casesGoods) > 0 ? true : false;
	}
}
