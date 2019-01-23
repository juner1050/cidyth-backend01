package com.hyzs.cidyth.module.base.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.entity.AlarmSituation;
import com.hyzs.cidyth.module.base.entity.AlarmSituationCid;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface AlarmSituationCidService {

	/**
	 * 获取警情
	 * @param alarmSituationCid
	 * @return
	 */
	List<AlarmSituation> listAlarmSituation(AlarmSituationCid alarmSituationCid);
	/**
	 * 获取警情
	 * @param alarmSituationVO
	 * @return
	 */
	PageInfo<AlarmSituation> listPickAlarm(AlarmSituation alarmSituation, Page page);
	/**
	 * 获取警情
	 * @param ajbh
	 * @return
	 */
	AlarmSituationCid selectAlarmByAjbh(String ajbh);

}
