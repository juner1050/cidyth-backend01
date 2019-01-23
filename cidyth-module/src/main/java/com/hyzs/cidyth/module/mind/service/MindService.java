package com.hyzs.cidyth.module.mind.service;

import java.util.Map;

import com.hyzs.cidyth.module.mind.vo.MindVO;

public interface MindService {
	

	/**
     * 插入思维导图表
     * @author 陈铭
     * @date 2018-04-16 17:21:53
     * @param ajbh 案件编号
     * @param sourceId 源id
     * @param targetId 目标id
     * @param sourceType 源类型
     * @param targetType 目标类型
     * @return 
     */
	void insert(String ajbh, int sourceId, int targetId, String sourceType, String targetType);
	
	/**
     * 思维导图列表，根据案件编号获取所有相关的【串并案】、【需求】、【信息】、【反馈线索】、【上传线索】
     * @author 陈铭
     * @date 2018-05-09 16:56:16
     * @param ajbh 案件编号
     * @return 
     */
	Map<String, Object> listMindByAjbh(String ajbh);
	
	/**
     * 保存思维导图坐标
     * @author 陈铭
     * @date 2018-05-09 16:56:16
     * @param mindVO 思维导图VO对象
     * @return 
     */
	void saveMind(MindVO mindVO);
}
