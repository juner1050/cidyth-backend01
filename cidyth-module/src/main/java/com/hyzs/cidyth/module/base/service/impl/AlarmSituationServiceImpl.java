package com.hyzs.cidyth.module.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.dao.AlarmSituationMapper;
import com.hyzs.cidyth.module.base.entity.AlarmSituation;
import com.hyzs.cidyth.module.base.entity.AlarmSituationCid;
import com.hyzs.cidyth.module.base.service.*;
import com.hyzs.cidyth.module.dic.service.DicService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.vo.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("AlarmSituationService")
public class AlarmSituationServiceImpl implements AlarmSituationService {

	private static final Logger logger = LoggerFactory.getLogger(AlarmSituationServiceImpl.class);

	@Autowired
	private AlarmSituationMapper alarmSituationMapper;
	@Autowired
	private AlarmSituationCidService alarmSituationCidService;
	@Autowired
	private CasesSuspectService casesSuspectService;
	@Autowired
	private CasesRecordService casesRecordService;
	@Autowired
	private CasesInformantService casesInformantService;
	@Autowired
	private CasesLawBookService casesLawBookService;
	@Autowired
	private CasesGoodsService casesGoodsService;
	@Autowired
	private SceneService sceneService;
	@Autowired
	private DicService dicService;

	@Override
	public void insert(String ajbh) {
		AlarmSituationCid AlarmSituationCidParam = new AlarmSituationCid();
		AlarmSituationCidParam.setAjbh(ajbh);
		List<AlarmSituation> lsAlarmSituationResult = alarmSituationCidService.listAlarmSituation(AlarmSituationCidParam);
		// 从警综库查询到，则插入
		if(lsAlarmSituationResult != null && lsAlarmSituationResult.size() > 0){
			for(AlarmSituation alarmSituationVO : lsAlarmSituationResult){
				AlarmSituation AlarmSituation = new AlarmSituation();
				BeanUtils.copyProperties(alarmSituationVO, AlarmSituation);
				alarmSituationMapper.insert(AlarmSituation);
			}
		}
	}

	@Override
	public PageInfo<AlarmSituation> list(AlarmSituation alarmSituationVO, Page page) {
		AlarmSituation alarmSituationParam = new AlarmSituation();
		BeanUtils.copyProperties(alarmSituationVO, alarmSituationParam);
		PageInfo<AlarmSituation> pageInfo = new PageInfo<>(alarmSituationMapper.select(alarmSituationParam));
		return pageInfo;
	}

	@Override
	public AlarmSituation getAlarmByAjbh(String ajbhs) {
		if(ajbhs.contains(",") && LocalTime.now().isBefore(LocalTime.of(18,0))){
			throw new IllegalArgumentException("请在晚上6点之后批量提取案件！");
		}
		// 获取用户
		User loginUser = UserUtil.getUser();
		if(loginUser == null){
			throw new IllegalArgumentException("无法获取当前登录用户！");
		}
		AlarmSituation result = null;
		if(StringUtils.isBlank(ajbhs)){
			throw new IllegalArgumentException("警情编号不能为空!");
		}
		String[] arrAjbh = ajbhs.split(",");
		for(String ajbh : arrAjbh) {
			AlarmSituation param = new AlarmSituation();
			param.setAjbh(ajbh);
			AlarmSituation alarmLocal = alarmSituationMapper.selectOne(param);//从本地库查询
			logger.debug("case {} from local.",ajbh);
			if(alarmLocal!=null){
				result = new AlarmSituation();
				BeanUtils.copyProperties(alarmLocal, result);
			}else{
				//从警踪查询
				AlarmSituationCid alarmSituationCid = alarmSituationCidService.selectAlarmByAjbh(ajbh);
				logger.debug("case {} from remote.",ajbh);
				if(alarmSituationCid!=null){
					BeanUtils.copyProperties(alarmSituationCid, param);
					if(loginUser != null){
						param.setLrry(loginUser.getAccount());
						param.setLrrymc(loginUser.getName());
					}
					// 保存嫌疑人到本地库
					casesSuspectService.insert(ajbh);
					// 保存物品到本地库
					casesGoodsService.insert(ajbh);
					// 保存笔录到本地库
					casesRecordService.insert(ajbh);
					// 保存报案人、受害人到本地库
					casesInformantService.insert(ajbh);
					// 保存法律文书到本地库
					casesLawBookService.insert(ajbh);
					// 保存现场基本信息到本地库
					sceneService.insert(ajbh);
					// 保存案件到本地库
					int effectCount = alarmSituationMapper.insertSelective(param);
					logger.debug("saved {} alarm into local db.",effectCount);
					if(effectCount>0){
						//保存成功.
						BeanUtils.copyProperties(alarmSituationCid, result);
					}
				}
				// 时间轴新增
				//timeNodeService.insert(param.getAjbh(), param.getId(), "CASE_PICK", result.getAjmc(), result.getZyaq());
			}
		}
		return result;
	}
}
