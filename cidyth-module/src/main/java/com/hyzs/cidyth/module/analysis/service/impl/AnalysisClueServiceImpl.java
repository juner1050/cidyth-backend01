package com.hyzs.cidyth.module.analysis.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.*;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.Constant;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.FileUtil;
import com.hyzs.cidyth.common.utils.excel.ExcelHelper;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.analysis.dao.AnalysisClueMapper;
import com.hyzs.cidyth.module.analysis.dto.*;
import com.hyzs.cidyth.module.analysis.entity.AnalysisClue;
import com.hyzs.cidyth.module.analysis.entity.AnalysisNode;
import com.hyzs.cidyth.module.analysis.service.AnalysisClueService;
import com.hyzs.cidyth.module.analysis.service.AnalysisNodeService;
import com.hyzs.cidyth.module.analysis.vo.AnalysisCaseDetailVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisCaseVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisClueVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisSuspectDetailVO;
import com.hyzs.cidyth.module.attachment.entity.Attachment;
import com.hyzs.cidyth.module.attachment.service.AttachmentService;
import com.hyzs.cidyth.module.base.dao.CasesMapper;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.entity.CasesMerge;
import com.hyzs.cidyth.module.base.entity.CasesSuspect;
import com.hyzs.cidyth.module.base.service.CasesMergeHisService;
import com.hyzs.cidyth.module.base.service.CasesSuspectService;
import com.hyzs.cidyth.module.base.vo.CasesMergeVO;
import com.hyzs.cidyth.module.cases.service.PcCasesService;
import com.hyzs.cidyth.module.dic.service.DicService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.psd.gafa.daf.mybatis.tk.entity.Example;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 研判线索
 */
@Service("analysisClueService")
public class AnalysisClueServiceImpl implements AnalysisClueService {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisClueServiceImpl.class);

	@Autowired
	private AnalysisClueMapper analysisClueMapper;
	@Autowired
	private AnalysisNodeService analysisNodeService;
	@Autowired
	private UserCenterService userCenterService;
	@Autowired
	private PcCasesService pcCasesService;
	@Autowired
	private CasesSuspectService casesSuspectService;
	@Autowired
	@Qualifier("localAttachmentService")
	private AttachmentService attachmentService;
	@Autowired
	private CasesMapper casesMapper;
	@Autowired
	private DicService dicService;
	@Autowired
	private CasesMergeHisService casesMergeHisService;

	/**
	 * 研判线索
	 *
	 * @param analysisQueryParam
	 * @param page
	 * @return
	 */
	@Override
	public PageInfo<AnalysisClueVO> list(QueryAnalysisParam queryParam, Page page) {
		User loginUser = UserUtil.getUser();
		queryParam.setLrry(loginUser.getAccount());
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		PageInfo<AnalysisClue> pageInfo = new PageInfo<>(analysisClueMapper.list(queryParam));
		List<AnalysisClueVO> lsAnalysisClueVO = Lists.newArrayList();
		
		pageInfo.getList().stream().forEach(i -> {
			AnalysisClueVO vo = new AnalysisClueVO();
			BeanUtils.copyProperties(i, vo);
			vo.setAb(i.getAb());
			vo.setBlzt(AnalysisStatusEnum.ofName(i.getBlzt()).descp());
			vo.setAjsl(pcCasesService.countMergeCaseByXsbh(i.getXsbh()));

			//接收单位为空代表未下发，显示待发送状态
			if(StringUtils.isEmpty(i.getJsdwbh())){
				vo.setOperateStatus(AnalysisNodeEnum.WAIT_SEND.name());
			}else{
				//获取节点
				AnalysisNode node = analysisNodeService.getMyHandleNode(loginUser.getAccount(), i.getXsbh());
				if(node == null){
					vo.setOperateStatus(AnalysisNodeEnum.FINISH.name());
				}else{
					//为空则代表已完成所有节点，则获取最大id的节点状态
					node = analysisNodeService.getMyLastNode(loginUser.getAccount(), i.getXsbh());
					vo.setOperateStatus(node.getJdzt());
				}
			}
			lsAnalysisClueVO.add(vo);
		});
		PageInfo<AnalysisClueVO> pageResult = new PageInfo<>(lsAnalysisClueVO);
		pageResult.setTotal(pageInfo.getTotal());
		pageResult.setPageNum(pageInfo.getPageNum());
		pageResult.setPageSize(pageInfo.getPageSize());
		return pageResult;
	}

	/**
	 * 新增研判线索
	 *
	 * @param param
	 */
	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = {RuntimeException.class, Exception.class})
	public Map<String, Object> save(SaveAnalysisParam param) {
		User loginUser = UserUtil.getUser();
		Dept loginUserDept = loginUser.getDepartment();
		String fileName = param.getFile().getOriginalFilename();
		String suffixName = fileName.substring(fileName.indexOf(".") + 1);
		Map<Integer, List<String>> mapExcel = null;
		if (!suffixName.equals("xls") && !suffixName.equals("xlsx")) {
			throw new ServiceException("文件格式错误");
		}
		try {
			String filePath = FileUtil.upload(param.getFile());
			String serverFileName = filePath + "." + suffixName;
			File saveFile = new File(Constant.SERVER_UPLOAD_PATH + serverFileName);
			mapExcel = ExcelHelper.getInstance().readExcelContent(saveFile, 0, 2);

		} catch (Exception e) {
			throw new ServiceException("导入失败：" + e.getMessage());
		}
		if(MapUtils.isEmpty(mapExcel)){
			throw new ServiceException("数据为空");
		}
		if(mapExcel.size() == 1){
			throw new ServiceException("串并案必须2宗以上");
		}else{
			List<String> lsCasesCode = Lists.newArrayList();
			for (List<String> item : mapExcel.values()) {
				lsCasesCode.add(item.get(0));
			}
			String oAjbh = lsCasesCode.get(0);
			lsCasesCode.remove(0);
			CasesMergeVO casesMergeVO = new CasesMergeVO();
			casesMergeVO.setCbabt("研判分析");
			casesMergeVO.setCbayy("研判分析");
			casesMergeVO.setoAjbh(oAjbh);
			casesMergeVO.setnAjbh(Joiner.on(",").join(lsCasesCode));
			pcCasesService.seriesAjByAjbh(casesMergeVO, param.getXsbh());

			//第一个案件的案别
			Cases firstCases = pcCasesService.getCaseByAjbh(oAjbh);
			String ab = "";
			if(firstCases != null){
				ab = dicService.getValueByKey("ab", firstCases.getAb());
			}

			AnalysisClue analysisClue = new AnalysisClue();
			BeanUtils.copyProperties(param, analysisClue);
			analysisClue.setAb(ab);
			analysisClue.setFsdwbh(loginUserDept.getCode());
			analysisClue.setFsdwmc(loginUserDept.getFullname());
			analysisClue.setLrry(loginUser.getAccount());
			analysisClue.setLrrymc(loginUser.getName());
			analysisClue.setBlzt(AnalysisStatusEnum.NOT_HANDLER.name());
			try {
				analysisClue.setYpsj(DateUtil.parseToDate(param.getYpsj(), DateUtil.Y_M_D));
			} catch (Exception e) {
				throw new ServiceException("时间转换失败");
			}
			analysisClueMapper.insertSelective(analysisClue);

			Map<String, Object> mapResult = Maps.newHashMap();
			mapResult.put("xsbh", param.getXsbh());
			return mapResult;
		}
	}

	/**
	 * 发送到单位
	 * 1、修改AnalysisClue记录状态：接收单位编号、接收单位名称为发送的单位
	 * 2、该单位的所有领导加入到AnalysisNode表，标记AnalysisNode办理状态为待指派
	 * 3、该单位的所有领导加入到CaseGroup表
	 *
	 * @param sendDeptCodeParam
	 */
	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = { RuntimeException.class, Exception.class })
	public void sendToUnit(SendDeptCodeParam sendDeptCodeParam) {
		User loginUser = UserUtil.getUser();
		String xsbh = sendDeptCodeParam.getXsbh();
		String deptCode = sendDeptCodeParam.getDeptCode();
		if(StringUtils.isBlank(deptCode) || StringUtils.isBlank(xsbh)){
			throw new ServiceException(SystemExceptionEnum.ILLEGAL_PARAMETER.code(), SystemExceptionEnum.ILLEGAL_PARAMETER.descp());
		}
		Dept dept = null;
		try {
			dept = userCenterService.getDeptByCode(deptCode);
		} catch (Exception e) {
			throw new ServiceException(SystemExceptionEnum.REQUEST_FAILED.code(), SystemExceptionEnum.REQUEST_FAILED.descp());
		}
		if(dept == null){
			throw new ServiceException(SystemExceptionEnum.NULL_DATA.code(), SystemExceptionEnum.NULL_DATA.descp());
		}

		AnalysisClue updateObject = new AnalysisClue();
		updateObject.setJsdwbh(dept.getCode());
		updateObject.setJsdwmc(dept.getFullname());

		Example conditionObject = new Example(AnalysisClue.class);
		conditionObject.createCriteria().andEqualTo("xsbh", xsbh);

		analysisClueMapper.updateByExampleSelective(updateObject, conditionObject);

		List<User> deptUsers = Lists.newArrayList();
		try {
			deptUsers = userCenterService.getUsersByDepartmentCodeAndPremission(dept.getCode(), Lists.newArrayList(SystemMenuEnum.ANALYSIS_ALLOCATE.getPremissionCode()));
		} catch (Exception e) {
			throw new ServiceException(SystemExceptionEnum.REQUEST_FAILED.code(), SystemExceptionEnum.REQUEST_FAILED.descp());
		}
		if(CollectionUtils.isNotEmpty(deptUsers)){
			analysisNodeService.saveAllocateLeader(xsbh, loginUser, deptUsers);
		}
	}

	/**
	 * 指派人员接收
	 * 1、修改该AnalysisClue记录的blzt为已办理
	 * 2、修改该AnalysisClue下的AnalysisNode节点的所有待指派节点为结束标记
	 * 3、新增指派所有成员到AnalysisNode表（流程节点）
	 * 4、新增指派所有成员到CaseGroup表（合成作战小组的人具备案件发送需求权限）
	 *
	 * @param allocateListParam
	 */
	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = { RuntimeException.class, Exception.class })
	public void allocatePersons(AllocateListParam allocateListParam) {
		User loginUser = UserUtil.getUser();
		String xsbh = allocateListParam.getXsbh();
		AnalysisClue queryParam = new AnalysisClue();
		queryParam.setXsbh(xsbh);
		queryParam = analysisClueMapper.selectOne(queryParam);
		// 未办理代表未指派
		if(queryParam != null && queryParam.getBlzt().equals(AnalysisStatusEnum.NOT_HANDLER.name())){

			AnalysisClue updateObject = new AnalysisClue();
			updateObject.setBlzt(AnalysisStatusEnum.HANDLER.name());
			updateObject.setId(queryParam.getId());
			analysisClueMapper.updateByPrimaryKeySelective(updateObject);

			analysisNodeService.finishAllocate(xsbh);

			analysisNodeService.saveSignPerson(xsbh, loginUser, allocateListParam.getPersons());
		}
	}

	/**
	 * 签收研判线索
	 */
	@Override
	public void sign(String xsbh) {
		analysisNodeService.sign(xsbh);
	}

	/**
	 * 退回研判线索
	 */
	@Override
	public void retreat(String xsbh) {
		analysisNodeService.retreat(xsbh);
	}

	/**
	 * 获取生成线索编号
	 *
	 * @return
	 */
	@Override
	public String getCode() {
		return "X" + DateUtil.getTimeStamp();
	}

	/**
	 * 获取研判线索的案件信息
	 *
	 * @param xsbh
	 * @return
	 */
	@Override
	public List<AnalysisCaseVO> listCaseInfo(String xsbh) {
		List<AnalysisCaseVO> lsAnalysisCaseVO = Lists.newArrayList();

		List<Cases> lsCases = pcCasesService.listMergeCaseInfoByXsbh(xsbh);
		lsCases.forEach(i -> {
			AnalysisCaseVO vo = new AnalysisCaseVO();
			vo.setAjbh(i.getAjbh());
			vo.setAjmc(i.getAjmc());
			vo.setZyaq(i.getZyaq());
			lsAnalysisCaseVO.add(vo);
		});
		return lsAnalysisCaseVO;
	}

	/**
	 * 上传案件图片
	 *
	 * @param param
	 */
	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = {RuntimeException.class, Exception.class})
	public void uploadCaseImage(SaveCaseImageParam param) {
		User loginUser = UserUtil.getUser();
		param.getAjbh();
		Cases cases = pcCasesService.getCaseByAjbh(param.getAjbh());
		List<Attachment> lsAttachment = Lists.newArrayList();
		param.getFiles().forEach(i -> {
			try {
				String fileId = FileUtil.upload(i, TimeNodeEnum.CASE.name().toLowerCase());
				String fileName = i.getOriginalFilename();
				// 获取文件扩展名（如exe、doc）
				String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
				// 创建附件对象
				Attachment attachment = new Attachment();
				attachment.setReferenceId(String.valueOf(cases.getId()));
				attachment.setFjlx(TimeNodeEnum.CASE.name());
				attachment.setFjmc(i.getOriginalFilename());
				attachment.setWjdx(i.getSize());
				attachment.setFileType(Integer.valueOf(FileTypeEnum.FILE_OTHER.code()));
				attachment.setLrry(loginUser.getAccount());
				attachment.setLrsj(new Date());
				attachment.setLrrymc(loginUser.getName());
				attachment.setSmbz(null);
				attachment.setWjgs(extName);
				attachment.setFileId(fileId);
				lsAttachment.add(attachment);
			} catch (Exception e) {
				throw new ServiceException("文件上传失败！");
			}
		});

		attachmentService.insertList(lsAttachment);
	}

	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = {RuntimeException.class, Exception.class})
	public void uploadCaseImage(SaveCaseImageParamBatch param) {
		param.getLsSaveCaseImageParam().forEach(i -> {
			if(CollectionUtils.isNotEmpty(i.getFiles())){
				uploadCaseImage(i);
			}
		});
	}

	/**
	 * 上传嫌疑人图片
	 *
	 * @param param
	 */
	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = {RuntimeException.class, Exception.class})
	public void uploadSuspectImage(List<String> lsAjbh, SaveSuspectParam param) {
		User loginUser = UserUtil.getUser();
		if(CollectionUtils.isNotEmpty(lsAjbh)){
			String fileCode;
			try {
				fileCode = FileUtil.upload(param.getFile(), TimeNodeEnum.CASE_SUSPECT.name().toLowerCase());
			} catch (Exception e) {
				throw new ServiceException("文件上传失败！");
			}
			final String fileId = fileCode;
			if(StringUtils.isNotBlank(fileId)){
				lsAjbh.forEach(ajbh -> {
					CasesSuspect casesSuspect = pcCasesService.getCasesSuspect(ajbh, param.getZjhm());
					if(casesSuspect == null){
						casesSuspect = new CasesSuspect();
						BeanUtils.copyProperties(param, casesSuspect);
						casesSuspect.setAjbh(ajbh);
						pcCasesService.saveCasesSuspect(casesSuspect);
						// 获取文件名
						String fileName = param.getFile().getOriginalFilename();
						// 获取文件扩展名（如exe、doc）
						String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
						// 创建附件对象
						Attachment attachment = new Attachment();
						attachment.setReferenceId(String.valueOf(casesSuspect.getId()));
						attachment.setFjlx(TimeNodeEnum.CASE_SUSPECT.name());
						attachment.setFjmc(param.getFile().getOriginalFilename());
						attachment.setWjdx(param.getFile().getSize());
						attachment.setFileType(Integer.valueOf(FileTypeEnum.FILE_OTHER.code()));
						attachment.setLrry(loginUser.getAccount());
						attachment.setLrsj(new Date());
						attachment.setLrrymc(loginUser.getName());
						attachment.setSmbz(null);
						attachment.setWjgs(extName);
						attachment.setFileId(fileId);
						attachmentService.insert(attachment);
					}
				});
			}
		}
	}

	@Override
	@Transactional(value = "masterTransactionManager", rollbackFor = {RuntimeException.class, Exception.class})
	public void uploadSuspectImage(SaveSuspectParamBatch param) {
		List<String> lsCaseCode = pcCasesService.listMergeCaseCodeByXsbh(param.getXsbh());
		if(CollectionUtils.isEmpty(lsCaseCode)){
			throw new ServiceException("无法通过线索编号获取案件编号");
		}
		param.getLsSaveSuspectParam().forEach(i -> {
			uploadSuspectImage(lsCaseCode, i);
		});
	}

	/**
	 * 根据线索编号获取串并案详情数据
	 *
	 * @param xsbh
	 * @return
	 */
	@Override
	public PageInfo<AnalysisCaseDetailVO> listCaseDetail(String xsbh, QueryCaseParam queryParam, Page page) {
		List<AnalysisCaseDetailVO> lsAnalysisCaseDetailVO = Lists.newArrayList();
		PageInfo<AnalysisCaseDetailVO> pageResult = new PageInfo<>(lsAnalysisCaseDetailVO);
		CasesMerge casesMerge = pcCasesService.getCasesMergeByXsbh(xsbh);
		if(casesMerge == null){
			return pageResult;
		}
		List<String> lsAjbh = casesMergeHisService.listCaseCodeByCbabh(casesMerge.getCbabh());
		if(CollectionUtils.isEmpty(lsAjbh)){
			return pageResult;
		}
		Example example = new Example(Cases.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andIn("ajbh", lsAjbh);
		if(StringUtils.isNotBlank(queryParam.getFssjks()) && StringUtils.isNotBlank(queryParam.getFssjjs())){
			criteria.andBetween("fasjcz", queryParam.getFssjks(), queryParam.getFssjjs());
		}
		if(StringUtils.isNotBlank(queryParam.getAjbh())){
			criteria.andLike("ajbh", "%" + queryParam.getAjbh() + "%");
		}
		if(StringUtils.isNotBlank(queryParam.getAjmc())){
			criteria.andLike("ajmc", "%" + queryParam.getAjmc() + "%");
		}
		if(StringUtils.isNotBlank(queryParam.getAjmc())){
			criteria.andEqualTo("zbdw", queryParam.getZbdw());
		}
		if(StringUtils.isNotBlank(queryParam.getAjmc())){
			criteria.andEqualTo("ajzbry", queryParam.getAjzbry());
		}
		PageHelper.startPage(page.getPageNum(), page.getPageSize(), page.getOrderBy());
		PageInfo<Cases> casePageInfo = new PageInfo<>(casesMapper.selectByExample(example));
		casePageInfo.getList().forEach(i -> {
			AnalysisCaseDetailVO vo = new AnalysisCaseDetailVO();
			BeanUtils.copyProperties(i, vo);
			//设置该案件是否被串并过
			try {
				if(StringUtils.isNotBlank(i.getZbdw())){
					Dept deptZbdw = userCenterService.getDeptByCode(i.getZbdw());
					vo.setZbdw(deptZbdw == null ? "" : deptZbdw.getFullname());
				}
				if(StringUtils.isNotBlank(i.getAjzbry())){
					User userZbry = userCenterService.getUserLazyByUserName(i.getAjzbry());
					vo.setAjzbry(userZbry == null ? "" : userZbry.getName());
				}
			} catch (Exception e) {
				throw new ServiceException("获取机构失败");
			}
			vo.setBdajstate(dicService.getValueByKey("bdajstate", i.getBdajstate()));
			vo.setAjlx(dicService.getValueByKey("ab", i.getAb()));
			vo.setFileIds(attachmentService.listFileIdByReference(String.valueOf(i.getId()), TimeNodeEnum.CASE.name()));
			lsAnalysisCaseDetailVO.add(vo);
		});
		pageResult.setList(lsAnalysisCaseDetailVO);
		pageResult.setTotal(casePageInfo.getTotal());
		pageResult.setPageNum(page.getPageNum());
		pageResult.setPageSize(page.getPageSize());

		return pageResult;
	}

	/**
	 * 根据线索编号获取串并案嫌疑人详情数据
	 *
	 * @param xsbh
	 * @return
	 */
	@Override
	public PageInfo<AnalysisSuspectDetailVO> listSuspectDetail(String xsbh, Page page) {
		List<AnalysisSuspectDetailVO> lsResult = Lists.newArrayList();
		PageInfo<AnalysisSuspectDetailVO> pageResult = new PageInfo<>(lsResult);
		List<String> lsAjbh = pcCasesService.listMergeCaseCodeByXsbh(xsbh);
		if(CollectionUtils.isEmpty(lsAjbh)){
			return pageResult;
		}
		PageInfo<CasesSuspect> pageInfo = casesSuspectService.listSuspectDetail(lsAjbh, page);
		pageInfo.getList().forEach(i -> {
			List<String> lsFileId = attachmentService.listFileIdByReference(String.valueOf(i.getId()), TimeNodeEnum.CASE_SUSPECT.name());
			AnalysisSuspectDetailVO vo = new AnalysisSuspectDetailVO();
			BeanUtils.copyProperties(i, vo);
			vo.setXb(SexEnum.ofCode(i.getXb()) == null ? "" : SexEnum.ofCode(i.getXb()).descp());
			vo.setFileId(CollectionUtils.isEmpty(lsFileId) ? null : lsFileId.get(0));
			lsResult.add(vo);
		});
		pageResult.setTotal(pageInfo.getTotal());
		pageResult.setPageNum(page.getPageNum());
		pageResult.setPageSize(page.getPageSize());
		return pageResult;
	}

	@Override
	public List<AnalysisSuspectDetailVO> getSuspectByXsbh(String xsbh) {
		List<AnalysisSuspectDetailVO> lsResult = Lists.newArrayList();
		List<String> lsAjbh = pcCasesService.listMergeCaseCodeByXsbh(xsbh);
		if(CollectionUtils.isEmpty(lsAjbh)){
			return lsResult;
		}
		List<CasesSuspect> lsCasesSuspect = casesSuspectService.listSuspectDetail(lsAjbh);
		lsCasesSuspect.forEach(i -> {
			List<String> lsFileId = attachmentService.listFileIdByReference(String.valueOf(i.getId()), TimeNodeEnum.CASE_SUSPECT.name());
			AnalysisSuspectDetailVO vo = new AnalysisSuspectDetailVO();
			BeanUtils.copyProperties(i, vo);
			vo.setXb(SexEnum.ofCode(i.getXb()) == null ? "" : SexEnum.ofCode(i.getXb()).descp());
			vo.setFileId(CollectionUtils.isEmpty(lsFileId) ? null : lsFileId.get(0));
			lsResult.add(vo);
		});
		return lsResult;
	}
}
