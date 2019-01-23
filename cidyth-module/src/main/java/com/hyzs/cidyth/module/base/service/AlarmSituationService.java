package com.hyzs.cidyth.module.base.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.entity.AlarmSituation;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface AlarmSituationService {
	
	/**
	 * 新增警情（从警综库查询警情，再插入到本地库）
	 * @param ajbh 案件编号
	 * @return
	 */
	void insert(String ajbh);
	/**
	 * 获取警情
	 * @param alarmSituationVO 案件编号
	 * @return
	 */
	PageInfo<AlarmSituation> list(AlarmSituation alarmSituation, Page page);
	/**
	 * 根据警情编号查询案件
	 * @param ajbhs 多个情编号逗号分隔
	 * @return
	 */
	AlarmSituation getAlarmByAjbh(String ajbhs);
}
