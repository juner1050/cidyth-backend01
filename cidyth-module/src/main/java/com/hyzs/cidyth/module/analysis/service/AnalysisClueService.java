package com.hyzs.cidyth.module.analysis.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.analysis.dto.*;
import com.hyzs.cidyth.module.analysis.vo.AnalysisCaseDetailVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisCaseVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisClueVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisSuspectDetailVO;

import java.util.List;
import java.util.Map;

public interface AnalysisClueService {

	/**
	 * 研判线索
	 * @param queryParam
	 * @param page
	 * @return
	 */
	PageInfo<AnalysisClueVO> list(QueryAnalysisParam analysisQueryParam, Page page);

	/**
	 * 新增研判线索
	 * @param param
	 */
	Map<String, Object> save(SaveAnalysisParam param);

	/**
	 * 发送到单位
	 * @param sendDeptCodeParam
	 */
	void sendToUnit(SendDeptCodeParam sendDeptCodeParam);

	/**
	 * 指派人员接收
	 * @param allocateListParam
	 */
	void allocatePersons(AllocateListParam allocateListParam);

	/**
	 * 签收研判线索
	 */
	void sign(String xsbh);

	/**
	 * 退回研判线索
	 */
	void retreat(String xsbh);

	/**
	 * 获取生成线索编号
	 * @return
	 */
	String getCode();

	/**
	 * 获取研判线索的案件信息
	 * @param xsbh
	 * @return
	 */
	List<AnalysisCaseVO> listCaseInfo(String xsbh);

	/**
	 * 上传案件图片
	 * @param param
	 */
	void uploadCaseImage(SaveCaseImageParam param);

	/**
	 * 上传案件图片（批量）
	 * @param param
	 */
	void uploadCaseImage(SaveCaseImageParamBatch param);

	/**
	 * 上传嫌疑人图片
	 * @param param
	 */
	void uploadSuspectImage(List<String> lsAjbh, SaveSuspectParam param);

	/**
	 * 上传嫌疑人图片（批量）
	 * @param param
	 */
	void uploadSuspectImage(SaveSuspectParamBatch param);

	/**
	 * 根据线索编号获取串并案详情数据
	 * @param xsbh
	 * @return
	 */
	PageInfo<AnalysisCaseDetailVO> listCaseDetail(String xsbh, QueryCaseParam queryParam, Page page);

	/**
	 * 根据线索编号获取串并案嫌疑人详情数据
	 * @param xsbh
	 * @return
	 */
	PageInfo<AnalysisSuspectDetailVO> listSuspectDetail(String xsbh, Page page);

	/**
	 * 根据线索编号获取串并案嫌疑人
	 * @param xsbh
	 * @return
	 */
	List<AnalysisSuspectDetailVO> getSuspectByXsbh(String xsbh);

}
