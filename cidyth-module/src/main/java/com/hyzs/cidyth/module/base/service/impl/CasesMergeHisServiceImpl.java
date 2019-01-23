package com.hyzs.cidyth.module.base.service.impl;

import com.google.common.collect.Lists;
import com.hyzs.cidyth.module.base.dao.CasesMergeHisMapper;
import com.hyzs.cidyth.module.base.entity.CasesMergeHis;
import com.hyzs.cidyth.module.base.service.CasesMergeHisService;
import com.hyzs.psd.gafa.daf.mybatis.tk.entity.Example;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesMergeHisService")
public class CasesMergeHisServiceImpl implements CasesMergeHisService {

	private static final Logger logger = LoggerFactory.getLogger(CasesMergeHisServiceImpl.class);

	@Autowired
	private CasesMergeHisMapper casesMergeHisMapper;

	/**
	 * 批量插入
	 *
	 * @param lsString
	 */
	@Override
	public void insertList(List<String> lsString, String cbabh, String xsbh) {
		List<CasesMergeHis> lsCasesMergeHis = Lists.newArrayList();
		lsString.forEach(i -> {
			CasesMergeHis casesMergeHis = new CasesMergeHis();
			casesMergeHis.setAjbh(i);
			casesMergeHis.setCbabh(cbabh);
			casesMergeHis.setXsbh(xsbh);
			casesMergeHis.setLrsj(new Date());
			lsCasesMergeHis.add(casesMergeHis);
		});
		casesMergeHisMapper.insertList(lsCasesMergeHis);
	}

	@Override
	public void insertList(List<CasesMergeHis> lsCasesMergeHis) {
		casesMergeHisMapper.insertList(lsCasesMergeHis);
	}

	/**
	 * 根据案件编号获取所在同一批案件的所有案件编号
	 *
	 * @param ajbh
	 * @return
	 */
	@Override
	public List<String> listMergeCaseCodeByCaseCode(String ajbh) {
		List<String> lsResult = Lists.newArrayList();
		if(StringUtils.isBlank(ajbh)){
			return lsResult;
		}
		Example example = new Example(CasesMergeHis.class);
		example.createCriteria().andEqualTo("ajbh", ajbh);
		example.setOrderByClause("id desc");
		List<CasesMergeHis> lsCasesMergeHis = casesMergeHisMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(lsCasesMergeHis)){
			return lsResult;
		}
		//获取倒序后的第一条案件记录的串并编号
		String maxCbabh = lsCasesMergeHis.get(0).getCbabh();
		CasesMergeHis queryParam = new CasesMergeHis();
		queryParam.setCbabh(maxCbabh);
		List<CasesMergeHis> lsBatchCasesMerge = casesMergeHisMapper.select(queryParam);

		lsBatchCasesMerge.forEach(i -> lsResult.add(i.getAjbh()));

		return lsResult;
	}

	/**
	 * 根据串并案编号获取同一批串并的案件编号
	 *
	 * @param ajbh
	 * @return
	 */
	@Override
	public List<String> listCaseCodeByCbabh(String cbabh) {
		List<String> lsResult = Lists.newArrayList();
		CasesMergeHis queryParam = new CasesMergeHis();
		queryParam.setCbabh(cbabh);
		casesMergeHisMapper.select(queryParam).forEach(i -> lsResult.add(i.getAjbh()));
		return lsResult;
	}

	@Override
	public Integer countMergeCase(String xsbh) {
		CasesMergeHis queryParam = new CasesMergeHis();
		queryParam.setXsbh(xsbh);
		return casesMergeHisMapper.selectCount(queryParam);
	}
}
