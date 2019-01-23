package com.hyzs.cidyth.module.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.enums.SexEnum;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.analysis.vo.AnalysisSuspectDetailVO;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.dao.CasesSuspectMapper;
import com.hyzs.cidyth.module.base.entity.CasesMerge;
import com.hyzs.cidyth.module.base.entity.CasesSuspect;
import com.hyzs.cidyth.module.base.entity.CasesSuspectCid;
import com.hyzs.cidyth.module.base.service.CasesSuspectCidService;
import com.hyzs.cidyth.module.base.service.CasesSuspectService;
import com.hyzs.cidyth.module.dic.service.DicService;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.psd.gafa.daf.mybatis.tk.entity.Example;
import org.apache.commons.collections.CollectionUtils;
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
@Service("CasesSuspectService")
public class CasesSuspectServiceImpl implements CasesSuspectService {

	private static final Logger logger = LoggerFactory.getLogger(CasesSuspectServiceImpl.class);

	@Autowired
	private CasesSuspectMapper casesSuspectMapper;
	@Autowired
	private CasesSuspectCidService casesSuspectCidService;
	@Autowired
	private TimeNodeService timeNodeService;
	@Autowired
	private AttachmentService attachmentService;

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh) {
		List<CasesSuspectCid> lsCcasesSuspectCid = casesSuspectCidService.listCasesSuspectByAjbh(ajbh);
		// 从警综库查询到，则插入
		if(lsCcasesSuspectCid != null && lsCcasesSuspectCid.size() > 0){
			for(CasesSuspectCid casesSuspectcid : lsCcasesSuspectCid){
				if(!isExist(ajbh, casesSuspectcid.getRybh())){
					CasesSuspect casesSuspect = new CasesSuspect();
					BeanUtils.copyProperties(casesSuspectcid, casesSuspect);
					casesSuspect.setAjbh(ajbh);
					casesSuspectMapper.insert(casesSuspect);
				}
			}
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh, Dept sendDept, Dept receiveDept) {
		List<CasesSuspectCid> lsCcasesSuspectCid = casesSuspectCidService.listCasesSuspectByAjbh(ajbh);
		// 从警综库查询到，则插入
		if(lsCcasesSuspectCid != null && lsCcasesSuspectCid.size() > 0){
			StringBuilder stringBuilder = new StringBuilder();
			for(CasesSuspectCid casesSuspectcid : lsCcasesSuspectCid){
				if(!isExist(ajbh, casesSuspectcid.getRybh())){
					CasesSuspect casesSuspect = new CasesSuspect();
					BeanUtils.copyProperties(casesSuspectcid, casesSuspect);
					casesSuspect.setAjbh(ajbh);
					casesSuspectMapper.insert(casesSuspect);
					stringBuilder.append(casesSuspect.getXm()).append("、");
				}
			}
			if(stringBuilder.length() > 0){
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
				timeNodeService.autoInsertBySyncData(ajbh, TimeNodeEnum.CASE_SUSPECT, "嫌疑人：" + stringBuilder.toString(), sendDept.getCode(), sendDept.getFullname(), receiveDept.getCode(), receiveDept.getFullname());
			}
		}
	}

	@Override
	public List<CasesSuspect> list(String ajbh) {
		CasesSuspect casesSuspectParam = new CasesSuspect();
		casesSuspectParam.setAjbh(ajbh);
		List<CasesSuspect> lsCasesSuspect = casesSuspectMapper.select(casesSuspectParam);
		return lsCasesSuspect;
	}

	/**
	 * 判断嫌疑人员是否存在
	 * @param ajbh 案件编号
	 * @param rybh 人员编号
	 * @return true表示存在，false表示不存在
	 */
	private boolean isExist(String ajbh, String rybh) {
		CasesSuspect param = new CasesSuspect();
		param.setAjbh(ajbh);
		param.setRybh(rybh);
		return casesSuspectMapper.selectCount(param) > 0 ? true : false;
	}

	/**
	 * 根据案件编号、证件号码
	 *
	 * @param ajbh
	 * @param zjhm
	 * @return
	 */
	@Override
	public CasesSuspect getCasesSuspect(String ajbh, String zjhm) {
		CasesSuspect param = new CasesSuspect();
		param.setAjbh(ajbh);
		param.setZjhm(zjhm);
		List<CasesSuspect> lsCasesSuspect = casesSuspectMapper.select(param);
		return CollectionUtils.isNotEmpty(lsCasesSuspect) ? lsCasesSuspect.get(0) : null;
	}

	/**
	 * 保存嫌疑人
	 *
	 * @param casesSuspect
	 */
	@Override
	public void save(CasesSuspect casesSuspect) {
		casesSuspectMapper.insert(casesSuspect);
	}

	/**
	 * 根据条件查询嫌疑人
	 *
	 * @param casesSuspect
	 * @return
	 */
	@Override
	public PageInfo<CasesSuspect> listSuspectDetail(List<String> lsAjbh, Page page) {
		List<CasesSuspect> lsCasesSuspect = Lists.newArrayList();
		PageInfo<CasesSuspect> pageResult = new PageInfo<>(lsCasesSuspect);
		if(CollectionUtils.isEmpty(lsAjbh)){
			return pageResult;
		}
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		PageInfo<CasesSuspect> pageInfo = new PageInfo<>(casesSuspectMapper.listCasesSuspectByAjbh(lsAjbh));
		return pageInfo;
	}

	@Override
	public List<CasesSuspect> listSuspectDetail(List<String> lsAjbh) {
		if(CollectionUtils.isEmpty(lsAjbh)){
			return Lists.newArrayList();
		}
		return casesSuspectMapper.listCasesSuspectByAjbh(lsAjbh);
	}
}
