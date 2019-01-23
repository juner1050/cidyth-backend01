package com.hyzs.cidyth.module.base.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.dto.CasesAbortDTO;
import com.hyzs.cidyth.module.base.dto.CasesFinishDTO;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.entity.CasesGroup;
import com.hyzs.cidyth.module.base.entity.CasesMerge;
import com.hyzs.cidyth.module.base.entity.CasesPartner;
import com.hyzs.cidyth.module.base.vo.CasesMergeVO;
import com.hyzs.cidyth.module.base.vo.CasesVO;
import com.hyzs.cidyth.module.base.vo.PersonalCasesVO;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface CasesService {

	/**
	 * 获取要提取的案件数据（查询本地数据库）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param entity
	 * @param page 分页对象
	 * @return java.util.List<com.hyzs.cidyth.module.base.entity.Cases>
	 */
	PageInfo<Cases> listCaseLocal(CasesVO cases, Page page);

	/**
	 * 获取要提取的案件数据（查询本地数据库）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param entity
	 * @param page 分页对象
	 * @return java.util.List<com.hyzs.cidyth.module.base.entity.Cases>
	 */
	PageInfo<Cases> listCaseLocalAll(CasesVO casesParam, Page page);

	/**
	 * 获取要提取的案件数据（查询本地数据库）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param entity
	 * @return java.util.List<com.hyzs.cidyth.module.base.entity.Cases>
	 */
	List<Cases> listCaseLocal(Cases cases);

    /**
     * 根据案件编号查询案件
     * @param ajbh
     * @return
     */
    Cases getCaseByAjbh(String ajbh);
    /**
     * 根据案件编号查询案件（不带新增时间轴节点）
     * @param ajbh
     * @return
     */
    Cases casesPick(String ajbh);
    
    /**
     * 获取本地案件状态的数量分组（每组的本地案件状态和数量用逗号分隔）
     * @return
     */
	Map<String, String> countGroupByBdajstate();
	
    /**
     * 根据案件编号获取本地案件
     * @param ajbh 案件编号
     * @return
     */
	Cases detail(String ajbh);

	/**
	 * 根据案件编号侦结该案件
	 * @param casesFinishDTO
	 * @return
	 */
	void casesFinish(CasesFinishDTO casesFinishDTO);

	/**
	 * 根据案件编号挂起该案件
	 * @param casesAbortDTO
	 * @return
	 */
	void casesAbort(CasesAbortDTO casesAbortDTO);

	/**
	 * 根据案件编号审核案件
	 * @param ajbh 案件编号
	 * @param checkStatus 审核状态
	 * @param checkResult 审核结果
	 * @return
	 */
	void casesCheck(String ajbh, Integer checkStatus, String checkResult);

	/**
	 * 根据案件编号串并案
	 * @param casesMerge 串并案对象
	 * @param oAjbh 原案件编号
	 * @param nAjbh 串并编号（多个用逗号分隔）
	 * @return
	 */
	void seriesAjByAjbh(CasesMergeVO casesMergeVO);

	/**
	 * 根据案件编号串并案
	 * @param casesMerge 串并案对象
	 * @param oAjbh 原案件编号
	 * @param nAjbh 串并编号（多个用逗号分隔）
	 * @return
	 */
	void seriesAjByAjbh(CasesMergeVO casesMergeVO, String xsbh);

	/**
	 * 案件新增
	 * @param cases 案件对象
	 * @return
	 */
	Integer insert(Cases cases);

	/**
	 * 案件合成作战小组
	 * @param ajbh 案件编号
	 * @return
	 */
	List<Map<String, Object>> memberGroup(String ajbh);

	/**
	 * 判断案件是否结束，true：挂起/侦结，false：侦办中
	 * @param ajbh
	 * @return
	 */
	boolean isFinish(String ajbh);

	/**
	 * 获取本月合成作战案件情况
	 * @param page 分页对象
	 * @return
	 */
	PageInfo<PersonalCasesVO> listCasesByMonth(Page page, String beginTime, String endTime);

	/**
	 * 个人中心-破案统计
	 * @return
	 */
	Map<String, Object> finishTotal();

	/**
	 * 添加协办人员
	 * @param ajbh 案件编号
	 * @param ajxbry 协办人员
	 */
	void addCoordinator(String ajbh, String ajxbry);

	/**
	 * 是否是合成作战小组成员
	 * @param ajbh
	 * @param account
	 * @return
	 */
	Map<String, Object> isMemberGroup(String ajbh, String account);

	/**
	 * 根据警号，案件编号，生成合成作战小组对象
	 * @param account
	 * @param ajbh
	 * @return
	 */
	CasesPartner getCasesPartnerByUserName(String account, String ajbh);

	/**
	 * 根据警号，案件编号，生成合成作战小组对象
	 * @param account
	 * @param ajbh
	 * @return
	 */
	CasesGroup getCasesGroupByUserName(String account, String ajbh);

	/**
	 * 是否允许查看串并案
	 * @param ajbh
	 */
	Map<String, Object> allowReadSeries(String ajbh);

	/**
	 * 获取所有本地的警综案件的编号
	 * @return
	 */
	List<String> listLocalAjbh();

	/**
	 * 根据案件编号获取：嫌疑人信息、物品信息、笔录信息、报案人受害人信息、法律文书信息、现场基本信息
	 * @param ajbh
	 */
	void extractCasesOtherInfo(String ajbh);

	/**
	 * 获取现勘统计数据
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @return
	 */
	PageInfo<Map<String, Object>> listXK(String kssj, String jssj, Page page);

	/**
	 * 根据研判线索编号获取串并案
	 * @param xsbh
	 * @return
	 */
	List<String> listMergeCaseCodeByXsbh(String xsbh);

	/**
	 * 根据研判线索编号获取案件信息
	 * @param xsbh
	 * @return
	 */
	List<Cases> listMergeCaseInfoByXsbh(String xsbh);

	/**
	 * 根据案件编号获取串并案
	 * @param ajbh
	 * @return
	 */
	List<Cases> listMergeCaseInfoByAjbh(String ajbh);

	/**
	 * 线索编号
	 * @param xsbh
	 * @return
	 */
	CasesMerge getCasesMergeByXsbh(String xsbh);

}
