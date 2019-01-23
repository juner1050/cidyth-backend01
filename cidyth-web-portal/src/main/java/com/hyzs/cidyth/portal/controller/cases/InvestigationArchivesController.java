package com.hyzs.cidyth.portal.controller.cases;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hyzs.cidyth.module.base.dao.SceneImageCidMapper;
import com.hyzs.cidyth.module.base.entity.SceneImageCid;
import com.hyzs.cidyth.module.base.service.*;
import com.hyzs.cidyth.module.base.vo.CasesVO;
import com.hyzs.cidyth.module.cases.service.PcCasesService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.enums.CaseStateEnum;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.cases.vo.CasesGoodsVO;
import com.hyzs.cidyth.module.cases.vo.CasesInformantVO;
import com.hyzs.cidyth.module.cases.vo.CasesRecordVO;
import com.hyzs.cidyth.module.cases.vo.CasesSuspectVO;
import com.hyzs.cidyth.module.cases.vo.SceneBO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "侦查档案接口" })
@RestController
@RequestMapping("archives")
public class InvestigationArchivesController {
	private static final Logger logger = LoggerFactory.getLogger(InvestigationArchivesController.class);
	@Autowired
	private PcCasesService casesService;
	@Autowired
	private SceneImageCidMapper sceneImageCidMapper;
	@Autowired
	private TechnologyCompareCidService technologyCompareCidService;
	/**
	 * 归档案件列表
	 * 
	 * @author derrick
	 * @date 2018-06-12 16:29
	 * @param casesVO
	 *            案件视图对象
	 * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
	 */
	@GetMapping(value = "caseList")
	@ApiOperation(value = "归档案件列表", httpMethod = "GET", response = CasesVO.class, notes = "归档案件列表")
	public PageInfo<CasesVO> list(CasesVO casesVO, Page page) {
		casesVO.setBdajstate(CaseStateEnum.FINISH.code());
		return casesService.listCaseLocal(casesVO, page);
	}

	/**
	 * 获取获取案件的现场勘查详情数据
	 * 
	 * @author derrick
	 * @date 2018-06-12 16:29
	 * @param ajbh
	 *            案件编号
	 * @return java.util.List<com.hyzs.cidyth.module.cases.vo.SceneBO>
	 */
	@GetMapping(value = "surveyedSceneInfo")
	@ApiOperation(value = "现场勘查信息", httpMethod = "GET", response = SceneBO.class, notes = "获取案件的现场勘查信息(指纹，DNA,足迹等)")
	public List<SceneBO> getSurveyedSceneInfo(
			@ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required = true) String ajbh) {
		return casesService.getSurveyedSceneInfo(ajbh);
	}

	/**
	 * 案件新增的数据源
	 * 
	 * @author derrick
	 * @date 2018-06-12 16:29
	 * @param id
	 *            案件对象
	 * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
	 */
	@GetMapping(value = "sceneImage")
	@ApiOperation(value = "现场照片", httpMethod = "GET", response = SceneImageCid.class, notes = "现场照片")
	public void sceneImage(HttpServletRequest req, HttpServletResponse res, String id) {

		if (StringUtils.isNotEmpty(id)) {
			SceneImageCid sceneImageCid = new SceneImageCid();
			sceneImageCid.setId(id);
			sceneImageCid = sceneImageCidMapper.selectOne(sceneImageCid);
			try {
				ServletOutputStream os = res.getOutputStream();
				InputStream is = new ByteArrayInputStream((byte[]) sceneImageCid.getContent());
				byte[] buf = new byte[1024];
				int len = 0;
				while ((len = is.read(buf, 0, 1024)) != -1) {
					os.write(buf, 0, len);
				}
				os.flush();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据案件获取笔录信息
	 * 
	 * @author derrick
	 * @date 2018-06-12 16:29
	 * @param ajbh
	 *            案件编号
	 * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesRecordVO>
	 */
	@GetMapping(value = "oralConfession")
	@ApiOperation(value = "笔录信息", httpMethod = "GET", response = CasesRecordVO.class, notes = "笔录信息")
	public List<CasesRecordVO> getOralConfession(
			@ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required = true) String ajbh) {
		return casesService.listCasesRecordVO(ajbh);
	}
	
	/**
	 * 
	 * @param ajbh
	 * @return
	 */
	@GetMapping(value = "caseSufferer")
	@ApiOperation(value = "案件详情的报案人/受害人列表信息", httpMethod = "GET", response = CasesInformantVO.class, notes = "案件详情的报案人/受害人列表信息")
	public List<CasesInformantVO> listCaseSufferer(
			@ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required = true) String ajbh) {
		return casesService.listCasesInformantVO(ajbh);
	}
	/**
	 * 获取案件详情的嫌疑人列表信息
	 * 
	 * @author derrick
	 * @date 2018-06-12 16:29
	 * @param ajbh
	 *            案件编号
	 * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesSuspectVO>
	 */
	@GetMapping(value = "caseSuspects")
	@ApiOperation(value = "案件详情的嫌疑人列表信息", httpMethod = "GET", response = CasesSuspectVO.class, notes = "案件详情的嫌疑人列表信息")
	public List<CasesSuspectVO> listCaseSuspects(
			@ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required = true) String ajbh) {
		return casesService.listCasesSuspectVO(ajbh);
	}
	
	/**
	 * 获取案件涉案物品信息
	 * 
	 * @author derrick
	 * @date 2018-06-12 16:29
	 * @param ajbh
	 *            案件编号
	 * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesGoodsVO>
	 */
	@GetMapping(value = "caseGoods")
	@ApiOperation(value = "获取案件涉案物品列表信息", httpMethod = "GET", response = CasesGoodsVO.class, notes = "获取案件涉案物品信息")
	public List<CasesGoodsVO> listCaseGoods(
			@ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required = true) String ajbh) {
		return casesService.listCasesGoodsVO(ajbh);
	}

	/**
	 * 案件的比中信息
	 * 
	 * @author derrick
	 * @date 2018-06-12 16:29
	 * @param ajbh
	 *            案件编号
	 * @param page
	 *            分页信息
	 * @return java.util.List<java.util.Map<String,Object>>
	 */
	@GetMapping(value = "caseCompareInfo")
	@ApiOperation(value = "案件的比中信息", httpMethod = "GET", response = Map.class, notes = "案件的比中信息")
	public PageInfo<Map<String, Object>> loadCaseCompareInfo(@ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required = true)String ajbh, Page page) {
		return technologyCompareCidService.loadCompareInfoByAjbh(ajbh, page);
	}
}
