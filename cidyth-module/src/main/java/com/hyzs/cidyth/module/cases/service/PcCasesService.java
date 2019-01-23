package com.hyzs.cidyth.module.cases.service;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.dto.CasesAbortDTO;
import com.hyzs.cidyth.module.base.dto.CasesFinishDTO;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.entity.CasesComparison;
import com.hyzs.cidyth.module.base.entity.CasesMerge;
import com.hyzs.cidyth.module.base.entity.CasesSuspect;
import com.hyzs.cidyth.module.base.vo.CasesMergeVO;
import com.hyzs.cidyth.module.base.vo.CasesRecordKLParam;
import com.hyzs.cidyth.module.base.vo.CasesVO;
import com.hyzs.cidyth.module.base.vo.PersonalCasesVO;
import com.hyzs.cidyth.module.cases.vo.*;
import com.hyzs.cidyth.module.uc.vo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface PcCasesService {

	/**
	 * 获取要提取的案件数据（查询本地数据库）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param entity
	 * @param page 分页对象
	 * @return java.util.List<com.hyzs.cidyth.module.entity.Cases>
	 */
	PageInfo<CasesVO> listCaseLocal(CasesVO entity, Page page);

	/**
	 * 获取要提取的案件数据（查询本地数据库）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param entity
	 * @param page 分页对象
	 * @return java.util.List<com.hyzs.cidyth.module.base.entity.Cases>
	 */
	PageInfo<CasesVO> listCaseLocalAll(CasesVO casesParam, Page page);

	/**
	 * 获取要提取的案件数据（查询本地数据库）
	 * @author 陈铭
	 * @date 2018-04-10 17:21:53
	 * @param entity
	 * @return java.util.List<com.hyzs.cidyth.module.entity.Cases>
	 */
	List<CasesVO> listCaseLocal(Cases entity);

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
	CasesBO detail(String ajbh);

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
	 * @param casesMergeVO 串并案对象
	 * @return
	 */
	void seriesAjByAjbh(CasesMergeVO casesMergeVO);

	/**
	 * 根据案件编号串并案
	 * @param casesMergeVO 串并案对象
	 * @return
	 */
	void seriesAjByAjbh(CasesMergeVO casesMergeVO, String xsbh);

	/**
	 * 案件导出
	 * @param casesVO 案件视图对象
	 * @return
	 */
	void excelExport(CasesVO casesVO);

	/**
	 * 案件详情
	 * @param ajbh 案件编号
	 * @return
	 */
	List<SceneBO> getSurveyedSceneInfo(String ajbh);

	/**
	 * 案件新增
	 * @param casesVO 案件对象
	 * @return
	 */
	Integer insert(CasesVO casesVO);

	/**
	 * 案件新增的初始化数据源
	 * @return
	 */
	Map<String, Object> initData();

	/**
	 * 案件合成作战小组
	 * @param ajbh 案件编号
	 * @return
	 */
	List<Map<String, Object>>memberGroup(String ajbh);

	/**
	 * 判断案件是否结束，true：挂起/侦结，false：侦办中
	 * @param ajbh
	 * @return
	 */
	boolean isFinish(String ajbh);

	/**
	 * 批量提取案件
	 * @param file Excel文件
	 */
	void batchPick(MultipartFile file);

	/**
	 * 获取本月合成作战案件情况
	 * @param page 分页对象
	 * @return
	 */
	//PageInfo<PersonalCasesVO> listCasesByMonth(Page page);

	/**
	 * 获取本月合成作战案件情况（让前端传开始时间、结束时间）
	 * @param page 分页对象
	 * @return
	 */
	PageInfo<PersonalCasesVO> listCasesPartner(Page page, String beginTime, String endTime);

	/**
	 * 根据 A-机构编号-年-月 的前缀编号查找警综最大的编号
	 * @param prefixCode
	 */
	String getMaxAjbhByYearMonth(String prefixCode);

	/**
	 * 根据案件编号、信息ID获取手印痕迹
	 * @param ajbh 案件编号
	 * @param xxid 信息ID
	 * @return
	 */
	List<SceneFingerPrintVO> listFingerPrints(String ajbh, String xxid);

	/**
	 * 根据案件编号、信息ID获取DNA痕迹
	 * @param ajbh 案件编号
	 * @param xxid 信息ID
	 * @return
	 */
	List<SceneBiologyPrintVO> listBiologyPrints(String ajbh, String xxid);

	/**
	 * 根据案件编号、信息ID获取足迹痕迹
	 * @param ajbh 案件编号
	 * @param xxid 信息ID
	 * @return
	 */
	List<SceneFootPrintVO> listFootPrints(String ajbh, String xxid);

	/**
	 * 根据案件编号获取现场信息
	 * @param ajbh 案件编号
	 * @return
	 */
	List<SceneVO> listSceneVO(String ajbh);

	/**
	 * 根据案件编号获取涉案物品
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesGoodsVO> listCasesGoodsVO(String ajbh);
	/**
	 * 根据案件编号获取报案受害信息
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesInformantVO> listCasesInformantVO(String ajbh);
	/**
	 * 根据案件编号获取笔录信息
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesRecordVO> listCasesRecordVO(String ajbh);
	/**
	 * 根据案件编号获取嫌疑人信息
	 * @param ajbh 案件编号
	 * @return
	 */
	List<CasesSuspectVO> listCasesSuspectVO(String ajbh);
	/**
	 * 根据案件编号获取生物DNA信息
	 * @param ajbh 案件编号
	 * @return
	 */
	List<SceneBiologyPrintVO> listSceneBiologyPrintVO(String ajbh);
	/**
	 * 根据案件编号获取手印痕迹信息
	 * @param ajbh 案件编号
	 * @return
	 */
	List<SceneFingerPrintVO> listSceneFingerPrintVO(String ajbh);
	/**
	 * 根据案件编号获取足迹痕迹信息
	 * @param ajbh 案件编号
	 * @return
	 */
	List<SceneFootPrintVO> listSceneFootPrintVO(String ajbh);

	/**
	 * 根据笔录参数获取笔录信息
	 * @param casesRecordKLParam
	 * @return
	 */
	List<CasesRecordVO> listCasesRecordKLParam(List<CasesRecordKLParam> casesRecordKLParam);

	/**
	 * 查询警综数据库
	 * @param casesVO
	 * @param page
	 * @return
	 */
	PageInfo<CasesVO> listPickCases(CasesVO casesVO, Page page);

	/**
	 * 查询警综数据库（本部门或所有子部门的案件）
	 * @param casesVO
	 * @param page
	 * @return
	 */
	PageInfo<CasesVO> listPickDeptCases(CasesVO casesVO, Page page);

	/**
	 * 个人中心-破案统计
	 * @return
	 */
	Map<String, Object> finishTotal();

	/**
	 * 添加协办人
	 * @param casesVO
	 * @return 返回添加后的所有协办人名称
	 */
	Map<String, Object> addCoordinator(CasesVO casesVO);

	/**
	 * 是否是合成作战小组成员
	 * @param ajbh
	 * @param account
	 * @return
	 */
	Map<String, Object> isMemberGroup(String ajbh, String account);

	/**
	 * 是否是我提取的案件
	 * @param ajbh
	 */
	Map<String, Object> isMyPick(String ajbh);

	/**
	 * 是否允许查看串并案
	 * @param ajbh
	 */
	Map<String, Object> allowReadSeries(String ajbh);

	/**
	 * 首页：分局侦查情况
	 * @param kssj 开始时间
	 * @param jssj 结束时间
	 * @return
	 */
	List<Map<String, Object>> branchInvestigate(String kssj, String jssj);

	/**
	 * 定时任务同步数据：嫌疑人信息、物品信息、笔录信息、报案人受害人信息、法律文书信息、现场基本信息
	 */
	void autoExtractCasesOtherInfo();
	/**
	 * 手动执行同步数据：嫌疑人信息、物品信息、笔录信息、报案人受害人信息、法律文书信息、现场基本信息
	 */
	void extractCasesOtherInfo();
	/**
	 * 根据编号同步数据：嫌疑人信息、物品信息、笔录信息、报案人受害人信息、法律文书信息、现场基本信息
	 * @param ajbh
	 */
	void ajbhExtractCasesOtherInfo(String ajbh);
	/**
	 * 获取比中信息
	 * @param ajbh
	 * @param page
	 * @return
	 */
	List<CasesComparison> listCasesComparison(String ajbh);

	/**
	 * 获取统计现勘对比数据
	 * @param kssj
	 * @param jssj
	 * @param page
	 * @return
	 */
	PageInfo<Map<String, Object>> listXK(String kssj, String jssj, Page page);

	/**
	 * 根据研判线索编号获取串并的案件数量
	 * @param xsbh
	 * @return
	 */
	Integer countMergeCaseByXsbh(String xsbh);

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
	 * 根据案件编号获取案件的串并案
	 * @param xsbh
	 * @return
	 */
	List<Map<String, String>> listMergeCaseInfoByAjbh(String ajbh);

	/**
	 * 根据线索编号获取串并案对象
	 * @return
	 */
	CasesMerge getCasesMergeByXsbh(String xsbh);

	/**
	 * 根据案件编号、证件号码获取嫌疑人
	 * @param ajbh
	 * @param zjhm
	 * @return
	 */
	CasesSuspect getCasesSuspect(String ajbh, String zjhm);

	/**
	 * 保存嫌疑人
	 * @param casesSuspect
	 */
	void saveCasesSuspect(CasesSuspect casesSuspect);

	/**
	 * 根据案件编号获取案件对象
	 * @param lsAjbh
	 * @return
	 */
	List<CasesVO> listCasesVOByAjbhs(List<String> lsAjbh);
}
