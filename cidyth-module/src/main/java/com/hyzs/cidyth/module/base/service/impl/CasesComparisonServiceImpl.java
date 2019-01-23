package com.hyzs.cidyth.module.base.service.impl;

import com.hyzs.cidyth.common.config.MultiThreadTaskConfig;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.common.utils.MessageUtil;
import com.hyzs.cidyth.module.base.dao.CasesComparisonCidMapper;
import com.hyzs.cidyth.module.base.dao.CasesComparisonMapper;
import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.cidyth.module.base.service.CasesComparisonService;
import com.hyzs.cidyth.module.base.service.CasesService;
import com.hyzs.cidyth.module.mind.service.MindService;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("CasesComparisonService")
public class CasesComparisonServiceImpl implements CasesComparisonService {

	private static final Logger logger = LoggerFactory.getLogger(CasesComparisonServiceImpl.class);

	@Autowired
	private CasesComparisonMapper casesComparisonMapper;
	@Autowired
	private CasesComparisonCidMapper casesComparisonCidMapper;
	@Autowired
	private TimeNodeService timeNodeService;
	@Autowired
	private MindService mindService;
	@Autowired
	private CasesService casesService;

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(String ajbh) {
		List<CasesComparison> lsCasesComparison = casesComparisonCidMapper.listCasesComparisonByAjbh(ajbh);
		for(CasesComparison item : lsCasesComparison){
			casesComparisonMapper.insert(item);
		}
	}

	@Override
	@Async(MultiThreadTaskConfig.MULTI_THREAD_TASK_EXECUTOR_BEAN_NAME)
	public void insert(Integer ajid, String ajbh, Dept sendDept, Dept receiveDept, User receiveUser) {
		List<CasesComparison> lsCasesComparison = casesComparisonCidMapper.listCasesComparisonByAjbh(ajbh);
		StringBuilder stringBuilder = new StringBuilder();
		String sendMessage = "";
		for(CasesComparison item : lsCasesComparison){
			if(!isExist(item.getBzly(), item.getAjbh(), item.getWzbh(), item.getBzlybh())){
				casesComparisonMapper.insert(item);
				mindService.insert(ajbh, item.getId(), ajid, TimeNodeEnum.COMPARISON_INFO.name(), TimeNodeEnum.CASE.name());
				stringBuilder.append(item.getXm()).append("、");
			}
		}
		if(stringBuilder.length() > 0){
			sendMessage = sendDept.getFullname() + "向您发送一条比中信息：案件编号为" + ajbh + "，比中人员：" + stringBuilder.toString();
			stringBuilder.deleteCharAt(stringBuilder.length()-1);
			timeNodeService.autoInsertBySyncData(ajbh, TimeNodeEnum.COMPARISON_INFO, "比中人员：" + stringBuilder.toString(), sendDept.getCode(), sendDept.getFullname(), receiveDept.getCode(), receiveDept.getFullname());
			MessageUtil.sendSMS(receiveUser.getPhone(), sendDept.getFullname(), "系统", sendMessage);
		}
	}

	/**
	 * 判断比中信息是否存在
	 * @param bzly 比中来源
	 * @param ajbh 案件编号
	 * @param wzbh 物证编号
	 * @param bzlybh 比中来源编号
	 * @return
	 */
	private boolean isExist(String bzly, String ajbh, String wzbh, String bzlybh) {
		CasesComparison param = new CasesComparison();
		param.setBzly(bzly);
		param.setAjbh(ajbh);
		param.setWzbh(wzbh);
		param.setBzlybh(bzlybh);
		return casesComparisonMapper.selectCount(param) > 0 ? true : false;
	}
}
