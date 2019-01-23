package com.hyzs.cidyth.module.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.dao.AlarmSituationCidMapper;
import com.hyzs.cidyth.module.base.entity.AlarmSituation;
import com.hyzs.cidyth.module.base.entity.AlarmSituationCid;
import com.hyzs.cidyth.module.base.service.AlarmSituationCidService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Service("AlarmSituationCidService")
public class AlarmSituationCidServiceImpl implements AlarmSituationCidService {

	private static final Logger logger = LoggerFactory.getLogger(AlarmSituationCidServiceImpl.class);

	@Autowired
	private AlarmSituationCidMapper alarmSituationCidMapper;
	
	public List<AlarmSituation> listAlarmSituation(AlarmSituationCid alarmSituationCid) {
		List<AlarmSituationCid> lsAlarmSituationCid = alarmSituationCidMapper.select(alarmSituationCid);
		List<AlarmSituation> lsAlarmSituation = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(lsAlarmSituationCid)){
			for(AlarmSituationCid alarm : lsAlarmSituationCid){
				AlarmSituation alarmSituation = new AlarmSituation();
				BeanUtils.copyProperties(alarm, alarmSituation);
				lsAlarmSituation.add(alarmSituation);
			}
		}
		return lsAlarmSituation;
	}

	@Override
	public PageInfo<AlarmSituation> listPickAlarm(AlarmSituation alarmSituation, Page page) {
		AlarmSituationCid alarmSituationCid = new AlarmSituationCid();
		BeanUtils.copyProperties(alarmSituation, alarmSituationCid);

		PageInfo<AlarmSituationCid> pageInfo = new PageInfo<AlarmSituationCid>(alarmSituationCidMapper.select(alarmSituationCid));
		PageInfo<AlarmSituation> pageResult = new PageInfo<AlarmSituation>();
		BeanUtils.copyProperties(pageInfo, pageResult);

		List<AlarmSituationCid> lsAlarmSituationCid = pageInfo.getList();
		List<AlarmSituation> lsAlarmSituation = Lists.newArrayList();

		if(CollectionUtils.isNotEmpty(lsAlarmSituationCid)){
			for(AlarmSituationCid alarm : lsAlarmSituationCid){
				AlarmSituation vo = new AlarmSituation();
				BeanUtils.copyProperties(alarm, vo);
				lsAlarmSituation.add(vo);
			}
		}

		pageResult.setList(lsAlarmSituation);
		return pageResult;
	}

	@Override
	public AlarmSituationCid selectAlarmByAjbh(String ajbh) {
		if(StringUtils.isBlank(ajbh)){
			return null;
		}
		AlarmSituationCid alarmSituationCid = new AlarmSituationCid();
		alarmSituationCid.setAjbh(ajbh);
		return alarmSituationCidMapper.selectOne(alarmSituationCid);
	}
}
