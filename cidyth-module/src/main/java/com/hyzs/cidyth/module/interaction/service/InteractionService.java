package com.hyzs.cidyth.module.interaction.service;

import java.util.Collection;
import java.util.List;

import com.hyzs.cidyth.module.interaction.vo.AbstractDemandDetail;
import com.hyzs.cidyth.module.reply.vo.InteractionContent;


/**
 * 互动服务(BBS)
 * @author Administrator
 *
 */
public interface InteractionService {
	/**
	 * 加载针对需求的互动内容
	 * @param referenceIdList 目标id列表(需求Id、线索id、回复id的列表)
	 * @param replyType 回复类型
	 * @return
	 */
	public InteractionContent loadInteractionBodyForDemand(Collection<String> demandIdList);
	/**
	 * 加载针对信息的互动内容
	 * @param infoIdList 目标id列表(信息id、回复id的列表)
	 * @return
	 */
	public InteractionContent loadInteractionBodyForInfo(Collection<String> infoIdList);
	/**
	 * 加载单条互动内容
	 * @param referenceId 目标id
	 * @param type 内容类型
	 * @return
	 */
	public AbstractDemandDetail loadInteractionContentItem(String referenceId,String type);
    /**
     * 根据案件编号分页查询相关需求
     * @param caseNo 案件编号
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
//    PageInfo<DemandDetail> getDemandDetailList(String caseNo,int pageNum, int pageSize);
    /**
     * 根据案件编号查询相关需求
     * @param caseNo 案件编号
     * @param pageNum 页码
     * @param pageSize 每页记录数
     * @return
     */
	List<AbstractDemandDetail> loadInteractionContentList(String caseNo);
	/**
	 * 根据案件编号查询相关需求和案件的信息
	 * @param caseNo 案件编号
	 * @param loadReplies 是否加载回复内容
	 * @return
	 */
	List<AbstractDemandDetail> loadInteractionContentList(String caseNo,boolean loadReplies);
}
