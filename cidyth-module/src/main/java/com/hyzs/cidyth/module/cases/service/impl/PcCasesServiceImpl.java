package com.hyzs.cidyth.module.cases.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.CaseFromEnum;
import com.hyzs.cidyth.common.enums.CaseSponsorEnum;
import com.hyzs.cidyth.common.enums.SceneImageTypeEnum;
import com.hyzs.cidyth.common.enums.YesNoEnum;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.Constant;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.DepartmentUtil;
import com.hyzs.cidyth.common.utils.FileUtil;
import com.hyzs.cidyth.common.utils.excel.ExcelHelper;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.dao.*;
import com.hyzs.cidyth.module.base.dto.CasesAbortDTO;
import com.hyzs.cidyth.module.base.dto.CasesFinishDTO;
import com.hyzs.cidyth.module.base.entity.*;
import com.hyzs.cidyth.module.base.service.*;
import com.hyzs.cidyth.module.base.vo.CasesMergeVO;
import com.hyzs.cidyth.module.base.vo.CasesRecordKLParam;
import com.hyzs.cidyth.module.base.vo.CasesVO;
import com.hyzs.cidyth.module.base.vo.PersonalCasesVO;
import com.hyzs.cidyth.module.cases.service.PcCasesService;
import com.hyzs.cidyth.module.cases.vo.*;
import com.hyzs.cidyth.module.dic.service.DicService;
import com.hyzs.cidyth.module.integral.entity.IntegralHis;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.uc.common.UserUtil;
import com.hyzs.cidyth.module.uc.service.UserCenterService;
import com.hyzs.cidyth.module.uc.vo.Dept;
import com.hyzs.cidyth.module.uc.vo.Role;
import com.hyzs.cidyth.module.uc.vo.User;
import com.hyzs.psd.gafa.daf.mybatis.tk.entity.Example;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

@Service("PcCasesService")
public class PcCasesServiceImpl implements PcCasesService {

    private static final Logger logger = LoggerFactory.getLogger(PcCasesServiceImpl.class);

    @Autowired
    private CasesService casesService;
    @Autowired
    private CasesMapper casesMapper;
    @Autowired
    private UserCenterService userCenterService;
    @Autowired
    private DicService dicService;
    @Autowired
    private SceneBiologyPrintService sceneBiologyPrintService;
    @Autowired
    private SceneFingerPrintService sceneFingerPrintService;
    @Autowired
    private SceneFootPrintService sceneFootPrintService;
    @Autowired
    private SceneImageCidMapper sceneImageCidMapper;
    @Autowired
    private SceneMapper sceneMapper;
    @Autowired
    private CasesGoodsService casesGoodsService;
    @Autowired
    private CasesInformantService casesInformantService;
    @Autowired
    private CasesRecordService casesRecordService;
    @Autowired
    private CasesSuspectService casesSuspectService;
    @Autowired
    private CasesCidService casesCidService;
    @Autowired
    private SceneImageCidService sceneImageCidService;
    @Autowired
    private CasesGroupMapper casesGroupMapper;
    @Autowired
    private CasesComparisonMapper casesComparisonMapper;
    @Autowired
    private CasesGroupService casesGroupService;
    @Autowired
    private CasesMergeHisService casesMergeHisService;
    @Autowired
    private IntegralHisService integralHisService;

    @Override
    public Cases getCaseByAjbh(String ajbhs) {
        return casesService.getCaseByAjbh(ajbhs);
    }

    @Override
    public Cases casesPick(String ajbh) {
        return casesService.casesPick(ajbh);
    }

    @Override
    public PageInfo<CasesVO> listCaseLocal(CasesVO casesParam, Page page) {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        if (casesParam.getCaseSponsor() != null) {
            if (casesParam.getCaseSponsor() == Integer.valueOf(CaseSponsorEnum.CASE_SELF.code())) {
                casesParam.setAjzbry(loginUser.getAccount());
            }
            if (casesParam.getCaseSponsor() == Integer.valueOf(CaseSponsorEnum.CASE_ASSIST.code())) {
                casesParam.setAjxbry(loginUser.getAccount());
            }
            if (casesParam.getCaseSponsor() == Integer.valueOf(CaseSponsorEnum.CASE_PICK.code())) {
                casesParam.setLrry(loginUser.getAccount());
            }
            if (casesParam.getCaseSponsor() == Integer.valueOf(CaseSponsorEnum.CASE_PARTNER.code())) {
                casesParam.setJybh(loginUser.getAccount());
            }
        }
        page.setOrderBy(" LRSJ DESC");
        PageInfo<Cases> pageCases = casesService.listCaseLocal(casesParam, page);
        List<Cases> lsCases = pageCases.getList();
        List<CasesVO> lsCasesVO = new ArrayList<>();
        // po转vo
        if (lsCases != null && lsCases.size() > 0) {
            for (Cases cases : lsCases) {
                CasesVO casesVO = new CasesVO();
                BeanUtils.copyProperties(cases, casesVO);
                casesVO.setFasjcz(DateUtil.formatDate(cases.getFasjcz()));
                casesVO.setFasjzz(DateUtil.formatDate(cases.getFasjzz()));
                casesVO.setSljjsj(DateUtil.formatDate(cases.getSljjsj()));
                casesVO.setLasj(DateUtil.formatDate(cases.getLasj()));
                listCasesConvertSectionCasesVO(casesVO, cases);
                //案别
                if (StringUtils.isNotEmpty(casesVO.getAb())) {
                    casesVO.setAbCn(dicService.getValueByKey("ab", casesVO.getAb()));
                }
                //案件状态(本地)
                if (StringUtils.isNotEmpty(casesVO.getBdajstate())) {
                    casesVO.setBdajstateCn(dicService.getValueByKey("bdajstate", casesVO.getBdajstate()));
                }
                lsCasesVO.add(casesVO);
            }
        }

        PageInfo<CasesVO> pageCasesVO = new PageInfo<CasesVO>(lsCasesVO);
        pageCasesVO.setTotal(pageCases.getTotal());
        pageCasesVO.setPageNum(page.getPageNum());
        pageCasesVO.setPageSize(page.getPageSize());
        return pageCasesVO;
    }

    @Override
    public PageInfo<CasesVO> listCaseLocalAll(CasesVO casesParam, Page page) {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        if (casesParam.getCaseSponsor() != null) {
            if (casesParam.getCaseSponsor() == Integer.valueOf(CaseSponsorEnum.CASE_SELF.code())) {
                casesParam.setAjzbry(loginUser.getAccount());
            }
            if (casesParam.getCaseSponsor() == Integer.valueOf(CaseSponsorEnum.CASE_ASSIST.code())) {
                casesParam.setAjxbry(loginUser.getAccount());
            }
            if (casesParam.getCaseSponsor() == Integer.valueOf(CaseSponsorEnum.CASE_PICK.code())) {
                casesParam.setLrry(loginUser.getAccount());
            }
            if (casesParam.getCaseSponsor() == Integer.valueOf(CaseSponsorEnum.CASE_PARTNER.code())) {
                casesParam.setJybh(loginUser.getAccount());
            }
        }
        page.setOrderBy(" LRSJ DESC");
        PageInfo<Cases> pageCases = casesService.listCaseLocalAll(casesParam, page);
        List<Cases> lsCases = pageCases.getList();
        List<CasesVO> lsCasesVO = new ArrayList<>();
        // po转vo
        if (lsCases != null && lsCases.size() > 0) {
            for (Cases cases : lsCases) {
                CasesVO casesVO = new CasesVO();
                BeanUtils.copyProperties(cases, casesVO);
                casesVO.setFasjcz(DateUtil.formatDate(cases.getFasjcz()));
                casesVO.setFasjzz(DateUtil.formatDate(cases.getFasjzz()));
                casesVO.setSljjsj(DateUtil.formatDate(cases.getSljjsj()));
                casesVO.setLasj(DateUtil.formatDate(cases.getLasj()));
                commonCasesConvertSectionCasesVO(casesVO, cases);
                StringBuilder stringBuilder = new StringBuilder();
                //案别
                if (StringUtils.isNotEmpty(casesVO.getAb())) {
                    casesVO.setAbCn(dicService.getValueByKey("ab", casesVO.getAb()));
                }
                //案件状态(本地)
                if (StringUtils.isNotEmpty(casesVO.getBdajstate())) {
                    casesVO.setBdajstateCn(dicService.getValueByKey("bdajstate", casesVO.getBdajstate()));
                }
                lsCasesVO.add(casesVO);
            }
        }
        PageInfo<CasesVO> pageCasesVO = new PageInfo<CasesVO>(lsCasesVO);
        pageCasesVO.setTotal(pageCases.getTotal());
        pageCasesVO.setPageNum(page.getPageNum());
        pageCasesVO.setPageSize(page.getPageSize());
        return pageCasesVO;
    }

    @Override
    public List<CasesVO> listCaseLocal(Cases casesParam) {
        List<Cases> lsCases = casesService.listCaseLocal(casesParam);
        List<CasesVO> lsCasesVO = new ArrayList<>();
        // po转vo
        if (lsCases != null && lsCases.size() > 0) {
            for (Cases cases : lsCases) {
                CasesVO casesVO = new CasesVO();
                BeanUtils.copyProperties(cases, casesVO);
                commonCasesConvertSectionCasesVO(casesVO, cases);
                if (StringUtils.isNotEmpty(casesVO.getAb())) {
                    casesVO.setAbCn(dicService.getValueByKey("ab", casesVO.getAb()));
                }
                if (StringUtils.isNotEmpty(casesVO.getBdajstate())) {
                    casesVO.setBdajstateCn(dicService.getValueByKey("bdajstate", casesVO.getBdajstate()));
                }
                lsCasesVO.add(casesVO);
            }
        }
        return lsCasesVO;
    }

    @Override
    public Map<String, String> countGroupByBdajstate() {
        return casesService.countGroupByBdajstate();
    }

    @Override
    public CasesBO detail(String ajbh) {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        CasesBO casesBO = new CasesBO();
        Cases cases = casesService.detail(ajbh);
        CasesVO casesVO = new CasesVO();
        BeanUtils.copyProperties(cases, casesVO);
        casesVO.setFasjcz(DateUtil.formatDate(cases.getFasjcz()));
        casesVO.setFasjzz(DateUtil.formatDate(cases.getFasjzz()));
        casesVO.setSljjsj(DateUtil.formatDate(cases.getSljjsj()));
        // 登录人等于提取人则该案件可以看到【添加协办人】按钮
        if (loginUser.getAccount().equals(casesVO.getLrry())) {
            casesVO.setAddAjxbry(true);
        } else {
            casesVO.setAddAjxbry(false);
        }
        commonCasesConvertSectionCasesVO(casesVO, cases);
        casesVO.setAbCn(dicService.getValueByKey("ab", casesVO.getAb()));
        casesVO.setBdajstateCn(dicService.getValueByKey("bdajstate", casesVO.getBdajstate()));
        casesVO.setZaztCn(dicService.getValueByKey("zazt", casesVO.getZazt()));
        casesVO.setSlJjfsCn(dicService.getValueByKey("sljjfs", casesVO.getSlJjfs()));
        casesVO.setAjlxCn(dicService.getValueByKey("ajlx", casesVO.getAjlx()));
        casesVO.setZabzCn(dicService.getValueByKey("zabz", casesVO.getZabz()));
        casesVO.setFaddQxCn(dicService.getValueByKey("faddqx", casesVO.getFaddQx()));
        casesVO.setFaddJdCn(dicService.getValueByKey("faddjd", casesVO.getFaddJd()));
        casesVO.setAjssjqCn(dicService.getValueByKey("ajssjq", casesVO.getAjssjq()));
        casesVO.setSssqCn(dicService.getValueByKey("sssq", casesVO.getSssq()));
        casesVO.setFadyCn(dicService.getValueByKey("fady", casesVO.getFady()));
        casesVO.setFxxsCn(dicService.getValueByKey("fxxs", casesVO.getFxxs()));
        casesVO.setAjwhcdCn(dicService.getValueByKey("ajwhcd", casesVO.getAjwhcd()));
        casesVO.setXzsjCn(dicService.getValueByKey("xzsj", casesVO.getXzsj()));
        casesVO.setXzcsCn(dicService.getValueByKey("xzcs", casesVO.getXzcs()));
        casesVO.setZagjCn(dicService.getValueByKey("xzdx", casesVO.getXzdx()));
        casesVO.setXzwpCn(dicService.getValueByKey("xzwp", casesVO.getXzwp()));
        casesVO.setZagjCn(dicService.getValueByKey("zagj", casesVO.getZagj()));
        casesVO.setXzbwCn(dicService.getValueByKey("xzbw", casesVO.getXzbw()));
        casesVO.setSdtdCn(dicService.getValueByKey("sdtd", casesVO.getSdtd()));
        casesVO.setSazzCn(dicService.getValueByKey("sazz", casesVO.getSazz()));
        casesVO.setDbjbCn(dicService.getValueByKey("dbjb", casesVO.getDbjb()));
        casesVO.setFzztlxCn(dicService.getValueByKey("fzztlx", casesVO.getFzztlx()));
        casesVO.setSfswCn(dicService.getValueByKey("sfsw", casesVO.getSfsw()));
        casesVO.setSjgjdqCn(dicService.getValueByKey("sjgjdq", casesVO.getSjgjdq()));
        casesVO.setAjFromCn(CaseFromEnum.ofCode(String.valueOf(casesVO.getAjFrom())) == null ? "" : CaseFromEnum.ofCode(String.valueOf(casesVO.getAjFrom())).descp());
        casesVO.setSecuritygradeCn(dicService.getValueByKey("securitygrade", casesVO.getSecuritygrade()));

        casesBO.setCasesVO(casesVO);
        casesBO.setLsCasesGoodsVO(listCasesGoodsVO(ajbh));
        casesBO.setLsCasesInformantVO(listCasesInformantVO(ajbh));
        casesBO.setLsCasesRecordVO(listCasesRecordVO(ajbh));
        casesBO.setLsCasesSuspectVO(listCasesSuspectVO(ajbh));
        casesBO.setLsSceneBO(getSurveyedSceneInfo(ajbh));
        casesBO.setLsCasesComparison(listCasesComparison(ajbh));

        return casesBO;
    }

    /**
     * 通用Cases转换CasesVO的部分外键值
     * @param casesVO
     * @param cases
     */
    private void commonCasesConvertSectionCasesVO(CasesVO casesVO, Cases cases) {
        try {
            // 受理接收单位不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getSljsdw())) {
                Dept deptSljsdw = null;
                deptSljsdw = userCenterService.getDeptByCode(cases.getSljsdw());
                casesVO.setSljsdwCn(deptSljsdw == null ? "" : deptSljsdw.getFullname());
            }
            // 立案单位不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getLadw())) {
                Dept deptLadw = userCenterService.getDeptByCode(cases.getLadw());
                casesVO.setLadwCn(deptLadw == null ? "" : deptLadw.getFullname());
            }
            // 主办单位不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getZbdw())) {
                Dept deptZbdw = userCenterService.getDeptByCode(cases.getZbdw());
                casesVO.setZbdwCn(deptZbdw == null ? "" : deptZbdw.getFullname());
            }
            // 主办人员不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getAjzbry())) {
                User userAjzbry = userCenterService.getUserByUserName(cases.getAjzbry());
                casesVO.setAjzbryCn(userAjzbry == null ? "" : userAjzbry.getName());
            }
            // 协办人员不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getAjxbry())) {
                StringBuilder stringBuilder = new StringBuilder();
                String[] ajxbry = cases.getAjxbry().split(",");
                for (String item : ajxbry) {
                    if(StringUtils.isNotEmpty(item)){
                        User userAjxbry = userCenterService.getUserByUserName(item);
                        if (userAjxbry != null) {
                            stringBuilder.append(userAjxbry.getName()).append(",");
                        }
                    }
                }
                if(stringBuilder.length() > 0){
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    casesVO.setAjxbryCn(stringBuilder.toString());
                }
            }
            // 提取人员
            casesVO.setLrryCn(cases.getLrrymc());
        } catch (Exception e) {
            throw new ServiceException("无法获取用户中心的数据!");
        }
    }

    /**
     * 列表Cases转换CasesVO的部分外键值
     * @param casesVO
     * @param cases
     */
    private void listCasesConvertSectionCasesVO(CasesVO casesVO, Cases cases) {
        try {
            // 受理接收单位不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getSljsdw())) {
                Dept deptSljsdw = null;
                deptSljsdw = userCenterService.getDeptByCode(cases.getSljsdw());
                casesVO.setSljsdwCn(deptSljsdw == null ? "" : deptSljsdw.getFullname());
            }
            // 主办人员不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getAjzbry())) {
                User userAjzbry = userCenterService.getUserByUserName(cases.getAjzbry());
                casesVO.setAjzbryCn(userAjzbry == null ? "" : userAjzbry.getName());
            }
            // 协办人员不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getAjxbry())) {
                StringBuilder stringBuilder = new StringBuilder();
                String[] ajxbry = cases.getAjxbry().split(",");
                for (String item : ajxbry) {
                    if(StringUtils.isNotEmpty(item)){
                        User userAjxbry = userCenterService.getUserByUserName(item);
                        if (userAjxbry != null) {
                            stringBuilder.append(userAjxbry.getName()).append(",");
                        }
                    }
                }
                if(stringBuilder.length() > 0){
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    casesVO.setAjxbryCn(stringBuilder.toString());
                }
            }
            // 提取人员
            casesVO.setLrryCn(cases.getLrrymc());
        } catch (Exception e) {
            throw new ServiceException("无法获取用户中心的数据!");
        }
    }

    @Override
    public void casesFinish(CasesFinishDTO casesFinishDTO) {
        casesService.casesFinish(casesFinishDTO);
    }

    @Override
    public void casesAbort(CasesAbortDTO casesAbortDTO) {
        casesService.casesAbort(casesAbortDTO);
    }

    @Override
    public void casesCheck(String ajbh, Integer checkStatus, String checkResult) {
        casesService.casesCheck(ajbh, checkStatus, checkResult);
    }

    @Override
    public void seriesAjByAjbh(CasesMergeVO casesMergeVO) {
        casesService.seriesAjByAjbh(casesMergeVO);
    }

    @Override
    public void seriesAjByAjbh(CasesMergeVO casesMergeVO, String xsbh) {
        casesService.seriesAjByAjbh(casesMergeVO, xsbh);
    }

    @Override
    public void excelExport(CasesVO param) {
		/*List<CasesVO> lsCasesVO = listCaseLocal(param);
		List<String> lsTitle = new ArrayList<String>();
		lsTitle.add("案件编号");  lsTitle.add("受理接收单位");  lsTitle.add("案件状态");  lsTitle.add("作案状态");  lsTitle.add("报警受理号");
		lsTitle.add("接警方式");  lsTitle.add("案件类型");  lsTitle.add("案件类别");  lsTitle.add("专案标识");  lsTitle.add("案件危害");
		lsTitle.add("案发区县");  lsTitle.add("案发街道");  lsTitle.add("案发所属警区");  lsTitle.add("案发所属社区");  lsTitle.add("案发地点");
		lsTitle.add("案发地域");  lsTitle.add("案发城市");  lsTitle.add("发现形式");  lsTitle.add("案件危害程度");  lsTitle.add("补立原因");
		lsTitle.add("主要案情");  lsTitle.add("选择时机");  lsTitle.add("选择处所");  lsTitle.add("选择对象");  lsTitle.add("选择物品");
		lsTitle.add("选择部位");  lsTitle.add("作案工具");  lsTitle.add("作案人数");  lsTitle.add("手段特点");  lsTitle.add("死亡人数");
		lsTitle.add("受伤人数");  lsTitle.add("损失价值");  lsTitle.add("督办级别");  lsTitle.add("犯罪主体类型");  lsTitle.add("是否涉外");
		lsTitle.add("涉及国家地区");  lsTitle.add("立案时间");  lsTitle.add("立案单位");  lsTitle.add("立案人员");  lsTitle.add("主办单位");
		lsTitle.add("主办人员");  lsTitle.add("协办人员");
		List<List<Object>> lsRowContent = new ArrayList<List<Object>>();
		for(CasesVO casesVO : lsCasesVO){
			List<Object> lsCellContent = new ArrayList<Object>();
			lsCellContent.add(casesVO.getAjbh());lsCellContent.add(casesVO.getSljsdwCn());lsCellContent.add(casesVO.getBdajstateCn());lsCellContent.add(casesVO.getZaztCn());lsCellContent.add(casesVO.getSlBjslh());
			lsCellContent.add(casesVO.getSlJjfsCn());lsCellContent.add(casesVO.getAjlxCn());lsCellContent.add(casesVO.getAbCn());lsCellContent.add(casesVO.getZabzCn());lsCellContent.add(casesVO.getAjwh());
			lsCellContent.add(casesVO.getFaddQxCn());lsCellContent.add(casesVO.getFaddJdCn());lsCellContent.add(casesVO.getAjssjqCn());lsCellContent.add(casesVO.getSssqCn());lsCellContent.add(casesVO.getFadd());
			lsCellContent.add(casesVO.getFadyCn());lsCellContent.add(casesVO.getSlfacs());lsCellContent.add(casesVO.getFxxsCn());lsCellContent.add(casesVO.getAjwhcdCn());lsCellContent.add(casesVO.getBlyy());
			lsCellContent.add(casesVO.getZyaq());lsCellContent.add(casesVO.getXzsjCn());lsCellContent.add(casesVO.getXzcsCn());lsCellContent.add(casesVO.getXzdxCn());lsCellContent.add(casesVO.getXzwpCn());
			lsCellContent.add(casesVO.getXzbwCn());lsCellContent.add(casesVO.getZagjCn());lsCellContent.add(casesVO.getZars());lsCellContent.add(casesVO.getSdtdCn());lsCellContent.add(casesVO.getSwrs());
			lsCellContent.add(casesVO.getSsrs());lsCellContent.add(casesVO.getSsjz());lsCellContent.add(casesVO.getDbjbCn());lsCellContent.add(casesVO.getFzztlxCn());lsCellContent.add(casesVO.getSfswCn());
			lsCellContent.add(casesVO.getSjgjdqCn());lsCellContent.add(casesVO.getLasj());lsCellContent.add(casesVO.getLadwCn());lsCellContent.add(casesVO.getAjlaryCn());lsCellContent.add(casesVO.getZbdwCn());
			lsCellContent.add(casesVO.getAjzbryCn());lsCellContent.add(casesVO.getAjxbryCn());
			lsRowContent.add(lsCellContent);
		}
		try {
			ExcelHelper.getInstance().writeExcel("D://workbook.xls", 0, lsTitle, lsRowContent);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
    }

    @Override
    public List<SceneBO> getSurveyedSceneInfo(String ajbh) {
        List<SceneBO> result = Lists.newArrayList();
        //获取CasesVO对象内容
//		CasesVO casesVO = getCasesVOByCaseNO(ajbh);

        //获取现场勘查基本信息sceneVO对象内容
        List<SceneVO> lsSceneVO = getSceneVOListByCaseNO(ajbh);

        //组装现场的图片、足迹、手印、DNA
        if (CollectionUtils.isNotEmpty(lsSceneVO)) {
            for (SceneVO sceneRecord : lsSceneVO) {
                SceneBO sceneBO = new SceneBO();
                //获取现场SceneVO对象
                sceneBO.setSceneVO(sceneRecord);
                //获取现场勘查基本信息(表)的ID
                String xxid = sceneRecord.getXxid().toString();
                if (StringUtils.isNotEmpty(xxid)) {
                    //获取现场勘查指纹信息SceneFingerPrintVO列表对象
                    sceneBO.setLsSceneFingerPrintVO(listFingerPrints(ajbh, xxid));
                    //获取现场勘查DNA信息SceneBiologyPrintVO(DNA)列表对象
                    sceneBO.setLsSceneBiologyPrintVO(listBiologyPrints(ajbh, xxid));
                    //获取现场勘查足迹信息SceneFootPrintVO列表对象
                    sceneBO.setLsSceneFootPrintVO(listFootPrints(ajbh, xxid));
                    //获取现场图片
                    sceneBO.setLsImage(sceneImageCidService.getSceneImage(xxid));
                }
                result.add(sceneBO);
            }
        }

        return result;
    }

    /**
     * 通过案件编号获取SceneVO对象内容
     *
     * @param ajbh 案件编号
     * @return SceneVO
     */
    private List<SceneVO> getSceneVOListByCaseNO(String ajbh) {
        List<SceneVO> result = Lists.newArrayList();
        if (StringUtils.isBlank(ajbh))
            return result;

        result = listSceneVO(ajbh);

        return result;
    }

    /**
     * 通过案件编号获取CasesVO对象内容
     *
     * @param ajbh 案件编号
     * @return CasesVO
     */
    private CasesVO getCasesVOByCaseNO(String ajbh) {
        Cases casesParam = new Cases();
        casesParam.setAjbh(ajbh);
        Cases cases = this.getCaseByAjbh(ajbh);
        CasesVO casesVO = new CasesVO();
        BeanUtils.copyProperties(cases, casesVO);

        try {
            // 受理接收单位不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getSljsdw())) {
                Dept deptSljsdw = userCenterService.getDeptByCode(cases.getSljsdw());
                casesVO.setSljsdwCn(deptSljsdw == null ? "" : deptSljsdw.getFullname());
            }
            // 立案单位不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getLadw())) {
                Dept deptLadw = userCenterService.getDeptByCode(cases.getLadw());
                casesVO.setLadwCn(deptLadw == null ? "" : deptLadw.getFullname());
            }
            // 主办单位不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getZbdw())) {
                Dept deptZbdw = userCenterService.getDeptByCode(cases.getZbdw());
                casesVO.setZbdwCn(deptZbdw == null ? "" : deptZbdw.getFullname());
            }
            // 主办人员不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getAjzbry())) {
                User userAjzbry = userCenterService.getUserByUserName(cases.getAjzbry());
                casesVO.setAjzbryCn(userAjzbry == null ? "" : userAjzbry.getName());
            }
            // 协办人员不为空，从用户中心获取
            if (StringUtils.isNotEmpty(cases.getAjxbry())) {
                StringBuilder stringBuilder = new StringBuilder();
                String[] ajxbry = cases.getAjxbry().split(",");
                for (String item : ajxbry) {
                    User userAjxbry = userCenterService.getUserByUserName(item);
                    if (userAjxbry != null) {
                        stringBuilder.append(userAjxbry.getName()).append(",");
                    }
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                casesVO.setAjxbryCn(stringBuilder.toString());
            }
        } catch (Exception e) {
            throw new ServiceException("无法获取用户中心的用户!");
        }

        StringBuilder stringBuilder = new StringBuilder();
        //案别
        if (StringUtils.isNotEmpty(casesVO.getAb())) {
            casesVO.setAbCn(dicService.getValueByKey("ab", casesVO.getAb()));
        }
        //(本地)案件状态
        if (StringUtils.isNotEmpty(casesVO.getBdajstate())) {
            casesVO.setBdajstateCn(dicService.getValueByKey("bdajstate", casesVO.getBdajstate()));
        }
        //作案状态
        if (StringUtils.isNotEmpty(casesVO.getZazt())) {
            casesVO.setZaztCn(dicService.getValueByKey("zazt", casesVO.getZazt()));
        }
        //接警方式
        if (StringUtils.isNotEmpty(casesVO.getSlJjfs())) {
            casesVO.setSlJjfsCn(dicService.getValueByKey("sljjfs", casesVO.getSlJjfs()));
        }
        //案件类型
        if (StringUtils.isNotEmpty(casesVO.getAjlx())) {
            casesVO.setAjlxCn(dicService.getValueByKey("ajlx", casesVO.getAjlx()));
        }
        //专案标识
        if (StringUtils.isNotEmpty(casesVO.getZabz())) {
            casesVO.setZabzCn(dicService.getValueByKey("zabz", casesVO.getZabz()));
        }
        //发案地点区县
        if (StringUtils.isNotEmpty(casesVO.getFaddQx())) {
            casesVO.setFaddQxCn(dicService.getValueByKey("faddqx", casesVO.getFaddQx()));
        }
        //发案地点街道
        if (StringUtils.isNotEmpty(casesVO.getFaddJd())) {
            casesVO.setFaddJdCn(dicService.getValueByKey("faddjd", casesVO.getFaddJd()));
        }
        //案件所属警区
        if (StringUtils.isNotEmpty(casesVO.getAjssjq())) {
            casesVO.setAjssjqCn(dicService.getValueByKey("ajssjq", casesVO.getAjssjq()));
        }
        //所属社区
        if (StringUtils.isNotEmpty(casesVO.getSssq())) {
            casesVO.setSssqCn(dicService.getValueByKey("sssq", casesVO.getSssq()));
        }
        //发案地域
        if (StringUtils.isNotEmpty(casesVO.getFady())) {
            casesVO.setFadyCn(dicService.getValueByKey("fady", casesVO.getFady()));
        }
        //发现形式
        if (StringUtils.isNotEmpty(casesVO.getFxxs())) {
            casesVO.setFxxsCn(dicService.getValueByKey("fxxs", casesVO.getFxxs()));
        }
        //案件危害程度
        if (StringUtils.isNotEmpty(casesVO.getAjwhcd())) {
            casesVO.setAjwhcdCn(dicService.getValueByKey("ajwhcd", casesVO.getAjwhcd()));
        }
        //选择时机
        if (StringUtils.isNotEmpty(casesVO.getXzsj())) {
            String[] xzsj = casesVO.getXzsj().split(",");
            stringBuilder.setLength(0);
            for (String item : xzsj) {
                String xzsjValue = dicService.getValueByKey("xzsj", item);
                if (StringUtils.isNotEmpty(xzsjValue)) {
                    stringBuilder.append(xzsjValue).append(",");
                }
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                casesVO.setXzsjCn(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
            }
        }
        //选择处所
        if (StringUtils.isNotEmpty(casesVO.getXzcs())) {
            casesVO.setXzcsCn(dicService.getValueByKey("xzcs", casesVO.getXzcs()));
        }
        //选择对象
        if (StringUtils.isNotEmpty(casesVO.getXzdx())) {
            String[] xzdx = casesVO.getXzdx().split(",");
            stringBuilder.setLength(0);
            for (String item : xzdx) {
                String xzdxValue = dicService.getValueByKey("xzdx", item);
                if (StringUtils.isNotEmpty(xzdxValue)) {
                    stringBuilder.append(xzdxValue).append(",");
                }
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                casesVO.setXzdxCn(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
            }
        }
        //选择物品
        if (StringUtils.isNotEmpty(casesVO.getXzwp())) {
            casesVO.setXzwpCn(dicService.getValueByKey("xzwp", casesVO.getXzwp()));
        }
        //作案工具
        if (StringUtils.isNotEmpty(casesVO.getZagj())) {
            String[] zagj = casesVO.getZagj().split(",");
            stringBuilder.setLength(0);
            for (String item : zagj) {
                String zagjValue = dicService.getValueByKey("wpmc", item);
                if (StringUtils.isNotEmpty(zagjValue)) {
                    stringBuilder.append(zagjValue).append(",");
                }
            }
            if (StringUtils.isNotEmpty(stringBuilder.toString())) {
                casesVO.setZagjCn(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
            }
        }
        //选择部位
        if (StringUtils.isNotEmpty(casesVO.getXzbw())) {
            casesVO.setXzbwCn(dicService.getValueByKey("xzbw", casesVO.getXzbw()));
        }
        //手段特点
        if (StringUtils.isNotEmpty(casesVO.getSdtd())) {
            casesVO.setSdtdCn(dicService.getValueByKey("sdtd", casesVO.getSdtd()));
        }
        //涉案主体
        if (StringUtils.isNotEmpty(casesVO.getSazz())) {
            casesVO.setSazzCn(dicService.getValueByKey("sazz", casesVO.getSazz()));
        }
        //督办级别
        if (StringUtils.isNotEmpty(casesVO.getDbjb())) {
            casesVO.setDbjbCn(dicService.getValueByKey("dbjb", casesVO.getDbjb()));
        }
        //犯罪主体类型
        if (StringUtils.isNotEmpty(casesVO.getFzztlx())) {
            casesVO.setFzztlxCn(dicService.getValueByKey("fzztlx", casesVO.getFzztlx()));
        }
        //是否涉外
        if (StringUtils.isNotEmpty(casesVO.getSfsw())) {
            casesVO.setSfswCn(dicService.getValueByKey("sfsw", casesVO.getSfsw()));
        }
        //涉及国家地区
        if (StringUtils.isNotEmpty(casesVO.getSjgjdq())) {
            casesVO.setSjgjdqCn(dicService.getValueByKey("sjgjdq", casesVO.getSjgjdq()));
        }
        //案件来源类型（1、从警综提取，2、手动录入案件）
        if (casesVO.getAjFrom() != null) {
            casesVO.setAjFromCn(dicService.getValueByKey("aj_from", String.valueOf(casesVO.getAjFrom())));
        }
        //保密级别
        if (StringUtils.isNotEmpty(casesVO.getSecuritygrade())) {
            casesVO.setAjFromCn(dicService.getValueByKey("securitygrade", casesVO.getSecuritygrade()));
        }

        return casesVO;
    }

    @Override
    public Integer insert(CasesVO casesVO) {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        Cases cases = new Cases();
        BeanUtils.copyProperties(casesVO, cases);
        casesGroupService.insert(casesVO.getAjbh(), loginUser);
        //casesService.addCoordinator(casesVO.getAjbh(), loginUser.getAccount());
        return casesService.insert(cases);
    }

    @Override
    public Map<String, Object> initData() {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        if (loginUser.getDepartment() == null) {
            throw new ServiceException("无法获取登录用户所在机构");
        }
        Map<String, Object> map = Maps.newHashMap();
        String ajbh = getMaxAjbhByYearMonth(loginUser.getDepartment().getCode());
        map.put("ajbh", ajbh);// 案件编号
        map.put("sljsdw", loginUser.getDepartment());// 受理接收单位
        map.put("zazt", dicService.listByLxbh("zazt"));// 作案状态
        map.put("slJjfs", dicService.listByLxbh("slJjfs"));// 受理接警方式
        //map.put("ajlx", dicService.listByLxbh("ajlx"));// 案件类型【暂无字典项】
        map.put("ab", dicService.listByLxbh("ab", "0"));// 案别
        map.put("zabz", dicService.listByLxbh("zabz"));// 专案标识
        // 这里让前端ajax请求加载
		/*map.put("ajssjq", dicService.listByLxbh("ajssjq"));// 所属警区
		map.put("sssq", dicService.listByLxbh("sssq"));// 所属社区*/
        map.put("ajstate", dicService.listByLxbh("ajstate"));//案件状态（警综）
        map.put("fady", dicService.listByLxbh("fady"));// 发现形式
        map.put("fxxs", dicService.listByLxbh("fxxs"));// 发案地域
        map.put("ajwhcd", dicService.listByLxbh("ajwhcd"));// 案件危害程度
        map.put("xzsj", dicService.listByLxbh("xzsj"));// 选择时机
        map.put("xzcs", dicService.listByLxbh("xzcs"));// 选择处所
        map.put("xzdx", dicService.listByLxbh("xzdx"));// 选择对象
        map.put("xzwp", dicService.listByLxbh("xzwp"));// 选择物品
        map.put("zagj", dicService.listByLxbh("zagj"));// 作案工具
        map.put("xzbw", dicService.listByLxbh("xzbw"));// 选择部位
        map.put("sdtd", dicService.listByLxbh("sdtd"));// 手段特点
        map.put("dbjb", dicService.listByLxbh("dbjb"));// 督办级别
        //map.put("jtajly", dicService.listByLxbh("jtajly"));// 具体案件来源【暂无字典项】
        map.put("fzztlx", dicService.listByLxbh("fzztlx"));// 犯罪主体类型
        map.put("sfsw", YesNoEnum.candidates());// 是否涉外
        //map.put("sjgjdq", dicService.listByLxbh("sjgjdq"));// 涉及国家地区【暂无字典项】

        return map;
    }

    @Override
    public List<Map<String, Object>> memberGroup(String ajbh) {
        return casesService.memberGroup(ajbh);
    }

    @Override
    public boolean isFinish(String ajbh) {
        return casesService.isFinish(ajbh);
    }

    @Override
    public void batchPick(MultipartFile file) {
        if (file == null) {
            throw new ServiceException("文件为空");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.indexOf(".") + 1);
        if (!suffixName.equals("xls") && !suffixName.equals("xlsx")) {
            throw new ServiceException("文件格式错误");
        }
        try {
            String filePath = FileUtil.upload(file);
            File saveFile = new File(Constant.SERVER_UPLOAD_PATH + filePath);
            Map<Integer, List<String>> mapExcel = ExcelHelper.getInstance().readExcelContent(saveFile, 0, 2);
            List<String> lsAjbh = Lists.newArrayList();
            for (List<String> item : mapExcel.values()) {
                lsAjbh.add(item.get(0));
            }
            // 拼接好的案件编号
            String ajbhs = "";
            if (CollectionUtils.isNotEmpty(lsAjbh)) {
                ajbhs = StringUtils.join(lsAjbh.toArray(), ",");
                getCaseByAjbh(ajbhs);
            } else {
                throw new ServiceException("案件编号为空");
            }
        } catch (Exception e) {
            throw new ServiceException("导入失败：" + e.getMessage());
        }
    }

    /**
     * 用户是否在当前机构中并拥有某个角色
     *
     * @param userCode 当前用户账号
     * @param deptCode 机构编号
     * @param roleCode 角色编号
     * @return boolean
     */
    private boolean isExistsDepartmentLeader(String userCode, String deptCode, String roleCode) {
        List<User> lsUser = Lists.newArrayList();
        try {
            lsUser = userCenterService.getUsersByDepartmentCode(deptCode);
        } catch (Exception e) {
            throw new ServiceException("无法获取当前登录用户所在机构的用户!");
        }
        for (User user : lsUser) {
            if (user.getAccount().equals(userCode)) {
                for (Role role : user.getRoles()) {
                    if (role.getTips().equals(roleCode)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*@Override
    public PageInfo<PersonalCasesVO> listCasesByMonth(Page page) {
        return casesService.listCasesByMonth(page, DateUtil.getMonthStartTime(), DateUtil.getMonthEndTime());
    }*/

    @Override
    public PageInfo<PersonalCasesVO> listCasesPartner(Page page, String beginTime, String endTime) {
        return casesService.listCasesByMonth(page, beginTime, endTime);
    }

    @Override
    public String getMaxAjbhByYearMonth(String deptCode) {
        if (StringUtils.isBlank(deptCode)) {
            throw new ServiceException("查询警综案件转换对象失败!");
        }
        String prifix = "Z" + deptCode + LocalDate.now().getYear() + StringUtils.leftPad(String.valueOf(LocalDate.now().getMonth().getValue()), 2, '0');
        String maxCode = casesMapper.getMaxAjbhByYearMonth(prifix);
        if (StringUtils.isBlank(maxCode)) {
            return prifix + "0001";
        } else {
            String suffix = maxCode.substring(19);
            Integer newValue = Integer.parseInt(suffix) + 1;
            suffix = StringUtils.leftPad(String.valueOf(newValue), 4, '0');
            return prifix + suffix;
        }
    }

    @Override
    public List<SceneFingerPrintVO> listFingerPrints(String ajbh, String xxid) {
        List<SceneFingerPrint> lsSceneFingerPrint = sceneFingerPrintService.listFingerPrints(ajbh, xxid);
        List<SceneFingerPrintVO> lsSceneFingerPrintVO = Lists.newArrayList();
        if (CollectionUtils.isEmpty(lsSceneFingerPrint)) {
            return lsSceneFingerPrintVO;
        } else {
            for (SceneFingerPrint item : lsSceneFingerPrint) {
                SceneFingerPrintVO vo = new SceneFingerPrintVO();
                BeanUtils.copyProperties(item, vo);
                vo.setImage(sceneImageCidService.getImage(item.getZpid()));
                lsSceneFingerPrintVO.add(vo);
            }
        }
        return lsSceneFingerPrintVO;
    }

    @Override
    public List<SceneBiologyPrintVO> listBiologyPrints(String ajbh, String xxid) {
        List<SceneBiologyPrint> lsSceneBiologyPrint = sceneBiologyPrintService.listBiologyPrints(ajbh, xxid);
        List<SceneBiologyPrintVO> lsSceneBiologyPrintVO = Lists.newArrayList();
        if (CollectionUtils.isEmpty(lsSceneBiologyPrint)) {
            return lsSceneBiologyPrintVO;
        } else {
            for (SceneBiologyPrint item : lsSceneBiologyPrint) {
                SceneBiologyPrintVO vo = new SceneBiologyPrintVO();
                BeanUtils.copyProperties(item, vo);
                vo.setImage(sceneImageCidService.getImage(item.getZpid()));
                lsSceneBiologyPrintVO.add(vo);
            }
        }
        return lsSceneBiologyPrintVO;
    }

    @Override
    public List<SceneFootPrintVO> listFootPrints(String ajbh, String xxid) {
        List<SceneFootPrint> lsSceneFootPrint = sceneFootPrintService.listFootPrints(ajbh, xxid);
        List<SceneFootPrintVO> lsSceneFootPrintVO = Lists.newArrayList();
        if (CollectionUtils.isEmpty(lsSceneFootPrint)) {
            return lsSceneFootPrintVO;
        } else {
            for (SceneFootPrint item : lsSceneFootPrint) {
                SceneFootPrintVO vo = new SceneFootPrintVO();
                BeanUtils.copyProperties(item, vo);
                vo.setImage(sceneImageCidService.getImage(item.getZpid()));
                lsSceneFootPrintVO.add(vo);
            }
        }
        return lsSceneFootPrintVO;
    }

    @Override
    public List<SceneVO> listSceneVO(String ajbh) {
        List<SceneVO> result = Lists.newArrayList();
        Scene scene = new Scene();
        scene.setAjbh(ajbh);
        List<Scene> lsScene = sceneMapper.select(scene);

        if (lsScene == null || lsScene.isEmpty())
            return result;

        for (Scene sceneRecord : lsScene) {
            SceneVO sceneVO = new SceneVO();
            BeanUtils.copyProperties(sceneRecord, sceneVO);
            result.add(sceneVO);
        }
        return result;
    }

    public List<SceneImageCidVO> listSceneImage(String sceneId) {
        List<SceneImageCidVO> lsSceneImageCidVO = Lists.newArrayList();
        if (StringUtils.isBlank(sceneId)) {
            return lsSceneImageCidVO;
        }

		SceneImageCid param = new SceneImageCid();
		param.setInvestigationId(sceneId);

		Example dynamicQuery = new Example(SceneImageCid.class);
        dynamicQuery.createCriteria()
                .andEqualTo("investigationId", sceneId)
                .andEqualTo("lb", SceneImageTypeEnum.SCENE_IMG.code());

        dynamicQuery.or(dynamicQuery.createCriteria()
                .andEqualTo("investigationId", sceneId)
                .andEqualTo("lb", SceneImageTypeEnum.SCENE_PIC.code()));


		List<SceneImageCid> lsSceneImageCid = sceneImageCidMapper.select(param);

		if(CollectionUtils.isNotEmpty(lsSceneImageCid)){
			for(SceneImageCid sceneImageCid : lsSceneImageCid){
				SceneImageCidVO sceneImageCidVO = new SceneImageCidVO();
				BeanUtils.copyProperties(sceneImageCid, sceneImageCidVO);
				if(StringUtils.isNotEmpty(sceneImageCid.getLb())){
					sceneImageCidVO.setLbCn(SceneImageTypeEnum.ofCode(sceneImageCid.getLb()) == null ? "" : SceneImageTypeEnum.ofCode(sceneImageCid.getLb()).descp());
				}
				lsSceneImageCidVO.add(sceneImageCidVO);
			}
		}
        return lsSceneImageCidVO;
    }

    @Override
    public List<CasesGoodsVO> listCasesGoodsVO(String ajbh) {
        List<CasesGoods> lsCasesGoods = casesGoodsService.list(ajbh);
        List<CasesGoodsVO> lsCasesGoodsVO = Lists.newArrayList();
        if (lsCasesGoods != null && lsCasesGoods.size() > 0) {
            for (CasesGoods casesGoods : lsCasesGoods) {
                CasesGoodsVO casesGoodsVO = new CasesGoodsVO();
                BeanUtils.copyProperties(casesGoods, casesGoodsVO);
                casesGoodsVO.setWpmcCn(dicService.getValueByKey("wpmc", casesGoodsVO.getWpmc()));//物品名称
                if (StringUtils.isNotEmpty(casesGoodsVO.getWpmcCn())) {
                    casesGoodsVO.setWpxzCn(dicService.getValueByKey("wpmc", casesGoodsVO.getWpxz()));//物品性质
                    casesGoodsVO.setWpztCn(dicService.getValueByKey("wpmc", casesGoodsVO.getWpzt()));//物品状态
                    casesGoodsVO.setWplbCn(dicService.getValueByKey("wpmc", casesGoodsVO.getWplb()));//物品类别
                    casesGoodsVO.setWptzCn(dicService.getValueByKey("wpmc", casesGoodsVO.getWptz()));//物品特征
                    casesGoodsVO.setSslxCn(dicService.getValueByKey("wpmc", casesGoodsVO.getSslx()));//损失类型
                    lsCasesGoodsVO.add(casesGoodsVO);
                }
            }
        }
        return lsCasesGoodsVO;
    }

    @Override
    public List<CasesInformantVO> listCasesInformantVO(String ajbh) {
        List<CasesInformant> lsCasesInformant = casesInformantService.list(ajbh);
        List<CasesInformantVO> lsCasesInformantVO = Lists.newArrayList();
        if (lsCasesInformant != null && lsCasesInformant.size() > 0) {
            for (CasesInformant casesInformant : lsCasesInformant) {
                CasesInformantVO casesInformantVO = new CasesInformantVO();
                BeanUtils.copyProperties(casesInformant, casesInformantVO);
                casesInformantVO.setXbCn(dicService.getValueByKey("xb", casesInformant.getXb()));//性别
                casesInformantVO.setZjzlCn(dicService.getValueByKey("zjzl", casesInformant.getXb()));//证件种类
                casesInformantVO.setRywhcdCn(dicService.getValueByKey("xl", casesInformant.getXb()));//文化程度
                casesInformantVO.setRylxCn(dicService.getValueByKey("baszqt_rylx", casesInformant.getRylx()));//人员类型
                lsCasesInformantVO.add(casesInformantVO);
            }
        }
        return lsCasesInformantVO;
    }

    @Override
    public List<CasesRecordVO> listCasesRecordVO(String ajbh) {
        List<CasesRecord> lsCasesRecord = casesRecordService.list(ajbh);
        List<CasesRecordVO> lsCasesRecordVO = Lists.newArrayList();
        if (lsCasesRecord != null && lsCasesRecord.size() > 0) {
            for (CasesRecord casesRecord : lsCasesRecord) {
                CasesRecordVO casesRecordVO = new CasesRecordVO();
                BeanUtils.copyProperties(casesRecord, casesRecordVO);
                casesRecordVO.setTargetXbCn(dicService.getValueByKey("xb", casesRecord.getTargetXb()));//对象性别
                lsCasesRecordVO.add(casesRecordVO);
            }
        }
        return lsCasesRecordVO;
    }

    @Override
    public List<CasesSuspectVO> listCasesSuspectVO(String ajbh) {
        List<CasesSuspect> lsCasesSuspect = casesSuspectService.list(ajbh);
        List<CasesSuspectVO> lsCasesSuspectVO = Lists.newArrayList();
        if (lsCasesSuspect != null && lsCasesSuspect.size() > 0) {
            for (CasesSuspect casesSuspect : lsCasesSuspect) {
                CasesSuspectVO casesSuspectVO = new CasesSuspectVO();
                BeanUtils.copyProperties(casesSuspect, casesSuspectVO);
                casesSuspectVO.setXbCn(dicService.getValueByKey("xb", casesSuspect.getXb()));//性别
                casesSuspectVO.setZjzlCn(dicService.getValueByKey("zjzl", casesSuspect.getXb()));//证件种类
                casesSuspectVO.setRywhcdCn(dicService.getValueByKey("xl", casesSuspect.getXb()));//文化程度
                casesSuspectVO.setSfCn(dicService.getValueByKey("sf", casesSuspect.getSf()));//身份
                casesSuspectVO.setLxCn(dicService.getValueByKey("lx", casesSuspect.getLx()));//脸型
                casesSuspectVO.setXxCn(dicService.getValueByKey("xx", casesSuspect.getXx()));//血型
                casesSuspectVO.setZyCn(dicService.getValueByKey("zy", casesSuspect.getZy()));//职业
                casesSuspectVO.setTmtzCn(dicService.getValueByKey("tmtz", casesSuspect.getTmtz()));//体貌特征
                casesSuspectVO.setZagjCn(dicService.getValueByKey("zagj", casesSuspect.getZagj()));//作案工具
                casesSuspectVO.setSdtdCn(dicService.getValueByKey("sdtd", casesSuspect.getSdtd()));//手段特点
                casesSuspectVO.setXzsjCn(dicService.getValueByKey("xzsj", casesSuspect.getXzsj()));//选择时间
                casesSuspectVO.setXzcsCn(dicService.getValueByKey("xzcs", casesSuspect.getXzcs()));//选择处所
                casesSuspectVO.setXzdxCn(dicService.getValueByKey("xzdx", casesSuspect.getXzdx()));//选择对象
                lsCasesSuspectVO.add(casesSuspectVO);
            }
        }
        return lsCasesSuspectVO;
    }

    @Override
    public List<SceneBiologyPrintVO> listSceneBiologyPrintVO(String ajbh) {
        List<SceneBiologyPrint> lsSceneBiologyPrint = sceneBiologyPrintService.listBiologyPrintByAjbh(ajbh);
        List<SceneBiologyPrintVO> lsSceneBiologyPrintVO = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(lsSceneBiologyPrint)) {
            for (SceneBiologyPrint sceneBiologyPrint : lsSceneBiologyPrint) {
                SceneBiologyPrintVO sceneBiologyPrintVO = new SceneBiologyPrintVO();
                BeanUtils.copyProperties(sceneBiologyPrint, sceneBiologyPrintVO);
                lsSceneBiologyPrintVO.add(sceneBiologyPrintVO);
            }
        }
        return lsSceneBiologyPrintVO;
    }

    @Override
    public List<SceneFingerPrintVO> listSceneFingerPrintVO(String ajbh) {
        List<SceneFingerPrint> lsSceneFingerPrint = sceneFingerPrintService.listFingerPrintByAjbh(ajbh);
        List<SceneFingerPrintVO> lsSceneFingerPrintVO = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(lsSceneFingerPrint)) {
            for (SceneFingerPrint sceneFingerPrint : lsSceneFingerPrint) {
                SceneFingerPrintVO sceneFingerPrintVO = new SceneFingerPrintVO();
                BeanUtils.copyProperties(sceneFingerPrint, sceneFingerPrintVO);
                lsSceneFingerPrintVO.add(sceneFingerPrintVO);
            }
        }
        return lsSceneFingerPrintVO;
    }

    @Override
    public List<SceneFootPrintVO> listSceneFootPrintVO(String ajbh) {
        List<SceneFootPrint> lsSceneFootPrint = sceneFootPrintService.listFootPrintByAjbh(ajbh);
        List<SceneFootPrintVO> lsSceneFootPrintVO = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(lsSceneFootPrint)) {
            for (SceneFootPrint sceneFootPrint : lsSceneFootPrint) {
                SceneFootPrintVO sceneFootPrintVO = new SceneFootPrintVO();
                BeanUtils.copyProperties(sceneFootPrint, sceneFootPrintVO);
                lsSceneFootPrintVO.add(sceneFootPrintVO);
            }
        }
        return lsSceneFootPrintVO;
    }

    @Override
    public List<CasesRecordVO> listCasesRecordKLParam(List<CasesRecordKLParam> casesRecordKLParam) {
        List<CasesRecord> lsCasesRecord = casesRecordService.listRecordKL(casesRecordKLParam);
        List<CasesRecordVO> lsCasesRecordVO = Lists.newArrayList();
        for (CasesRecord casesRecord : lsCasesRecord) {
            CasesRecordVO casesRecordVO = new CasesRecordVO();
            BeanUtils.copyProperties(casesRecord, casesRecordVO);
            casesRecordVO.setTargetXbCn(dicService.getValueByKey("xb", casesRecord.getTargetXb()));//对象性别
            lsCasesRecordVO.add(casesRecordVO);
        }
        return lsCasesRecordVO;
    }

    @Override
    public PageInfo<CasesVO> listPickCases(CasesVO casesVO, Page page) {
        /*try {
            AbstractSearchService servicess = casesQueryAccelerator;
            if(StringUtils.isNotEmpty(casesVO.getAjbh())){
                servicess.and("ajbh", casesVO.getAjbh());
            }
            if(StringUtils.isNotEmpty(casesVO.getAjmc())){
                servicess.and("ajmc", casesVO.getAjmc());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        PageInfo<Cases> pageInfo = casesCidService.listPackCases(casesVO, page);
        List<CasesVO> lsCasesVO = Lists.newArrayList();
        List<Cases> lsCases = pageInfo.getList();
        if (lsCases != null && lsCases.size() > 0) {
            for (Cases cases : lsCases) {
                CasesVO vo = new CasesVO();
                //BeanUtils.copyProperties(cases, vo);
                vo.setAjbh(cases.getAjbh());
                vo.setAjmc(cases.getAjmc());
                vo.setSljsdw(cases.getSljsdw());
                vo.setAb(cases.getAb());
                vo.setFasjcz(DateUtil.formatDate(cases.getFasjcz(), DateUtil.Y_M_D_H_M_S));
                vo.setAjzbry(cases.getAjzbry());
                vo.setAjxbry(cases.getAjxbry());
                try {
                    try {
                        // 受理接收单位不为空，从用户中心获取
                        if (StringUtils.isNotEmpty(cases.getSljsdw())) {
                            Dept deptSljsdw = userCenterService.getDeptByCode(cases.getSljsdw());
                            vo.setSljsdwCn(deptSljsdw == null ? "" : deptSljsdw.getFullname());
                        }
                        // 主办单位不为空，从用户中心获取
                        if (StringUtils.isNotEmpty(cases.getZbdw())) {
                            Dept deptZbdw = userCenterService.getDeptByCode(cases.getZbdw());
                            vo.setZbdwCn(deptZbdw == null ? "" : deptZbdw.getFullname());
                        }
                        // 主办人员不为空，从用户中心获取
                        if (StringUtils.isNotEmpty(cases.getAjzbry())) {
                            User userAjzbry = userCenterService.getUserByUserName(cases.getAjzbry());
                            vo.setAjzbryCn(userAjzbry == null ? "" : userAjzbry.getName());
                        }
                        //案别
                        if (StringUtils.isNotEmpty(cases.getAb())) {
                            String abCn = dicService.getValueByKey("ab", cases.getAb());
                            vo.setAbCn(abCn);
                        }
                        Date lasj = cases.getLasj();
                        if (lasj != null) {
                            vo.setLasj(DateUtil.formatDate(lasj, DateUtil.Y_M_D));
                        }
                    } catch (Exception e) {
                        throw new ServiceException("无法获取用户中心的用户!");
                    }
                    lsCasesVO.add(vo);
                } catch (Exception e) {
                    throw new ServiceException("查询警综案件转换对象失败!");
                }
            }
        }
        PageInfo<CasesVO> pageResult = new PageInfo<CasesVO>();
        pageResult.setList(lsCasesVO);
        pageResult.setTotal(pageInfo.getTotal());
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        return pageResult;
    }

    @Override
    public PageInfo<CasesVO> listPickDeptCases(CasesVO casesVO, Page page) {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        if (StringUtils.isNotEmpty(casesVO.getSljsdw())) {
            try {
                Map<String, Object> map = userCenterService.isChildren(loginUser.getDeptid(), casesVO.getSljsdw());
                // 如果选择的受理接收单位是登录人机构的子集
                if (Boolean.valueOf(map.get("result").toString())) {
                    casesVO.setSljsdw(casesVO.getSljsdw());
                } else {
                    casesVO.setSljsdw(loginUser.getDeptid());
                }
            } catch (Exception e) {
                throw new ServiceException("请求失败");
            }
        } else {
            String sectionDeptCode = DepartmentUtil.getSectionDeptCode(loginUser.getDepartment().getPolityLevel(),
                    loginUser.getDepartment().getCode());
            casesVO.setSljsdw(sectionDeptCode);
        }

        // 限制按照部分机构编号查询
			/*String sectionDeptCode = DepartmentUtil.getSectionDeptCode(loginUser.getDepartment().getPolityLevel(),
					loginUser.getDepartment().getCode());*/
			/*if(StringUtils.isNotEmpty(sectionDeptCode)){
				// 长度为6代表分局机构
				if(sectionDeptCode.length() == 6){
					casesParam.setSljsdw(sectionDeptCode);
					// 如果分局的人的单位不为空，并且不包含分局前缀6位编号，则代表查询条件的主办单位不属于分局下的
					if(StringUtils.isNotEmpty(casesParam.getZbdw()) && casesParam.getZbdw().indexOf(sectionDeptCode) < 0){
						casesParam.setSljsdw(null);
					}
				}else{
					casesParam.setSljsdw(sectionDeptCode);
				}
			}*/
        return listPickCases(casesVO, page);
    }

    @Override
    public Map<String, Object> finishTotal() {
        return casesService.finishTotal();
    }

    @Override
    public Map<String, Object> addCoordinator(CasesVO casesVO) {
        Map<String, Object> mapResult = Maps.newHashMap();
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        if (casesVO == null || StringUtils.isEmpty(casesVO.getAjbh()) || StringUtils.isEmpty(casesVO.getAjxbry())) {
            throw new ServiceException("无法获取案件编号或协办人员!");
        }
        Cases cases = casesService.getCaseByAjbh(casesVO.getAjbh());
        if (StringUtils.isNotEmpty(cases.getLrry()) && cases.getLrry().equals(loginUser.getAccount())) {

            //获取现有的合成作战小组的对象
            List<CasesGroup> lsCasesGroup = casesGroupMapper.selectCaseGroupsByAjbh(casesVO.getAjbh());

            //保存数据库的原警号
            List<String> lsOriginAccount = Lists.newArrayList();
            for (CasesGroup casesGroup : lsCasesGroup) {
                lsOriginAccount.add(casesGroup.getJybh());
            }

            //保存前端新增的警号
            List<String> lsParamAccount = Lists.newArrayList();
            lsParamAccount.addAll(Stream.of(casesVO.getAjxbry().split(",")).collect(Collectors.toList()));

            //过滤新增的警号集合在原警号集合已存在的警号（去重）
            List<String> lsFilterAccount = lsParamAccount.stream().filter(item -> !lsOriginAccount.contains(item)).collect(Collectors.toList());

            //保存过滤后的警号的CasesPartner对象集合
            List<CasesGroup> lsResultGroup = Lists.newArrayList();

            //根据过滤后的警号得到一个CasesPartner对象
            for (String filterAccount : lsFilterAccount) {
                lsResultGroup.add(casesService.getCasesGroupByUserName(filterAccount, casesVO.getAjbh()));
            }
            if (CollectionUtils.isNotEmpty(lsResultGroup)) {
                casesGroupMapper.batchInsertCaseGroups(lsResultGroup);
            }
            mapResult.put("result", true);
            return mapResult;
        } else {
            throw new ServiceException("无法添加协办人员!");
        }
    }

    @Override
    public Map<String, Object> isMemberGroup(String ajbh, String account) {
        return casesService.isMemberGroup(ajbh, account);
    }

    @Override
    public Map<String, Object> isMyPick(String ajbh) {
        Map<String, Object> map = Maps.newHashMap();
        if (StringUtils.isEmpty(ajbh)) {
            throw new ServiceException("无法获取案件编号");
        }
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        Cases cases = casesService.getCaseByAjbh(ajbh);
        map.put("isMyPick", cases.getLrry().equals(loginUser.getAccount()));
        return map;
    }

    @Override
    public Map<String, Object> allowReadSeries(String ajbh) {
        return casesService.allowReadSeries(ajbh);
    }

    @Override
    public List<Map<String, Object>> branchInvestigate(String kssj, String jssj) {
        if (StringUtils.isEmpty(kssj) || StringUtils.isEmpty(jssj)) {
            return null;
        }
        List<Dept> lsDept = null;
        try {
            lsDept = userCenterService.getBranchDept();
            if (CollectionUtils.isEmpty(lsDept)) {
                return null;
            }
        } catch (Exception e) {
            throw new ServiceException("无法获取机构数据");
        }
        List<Map<String, Object>> lsGroup = casesMapper.branchInvestigate(kssj, jssj);
        Map<String, String> deptMap = Maps.newHashMap();

        for (Dept dept : lsDept) {
            if (StringUtils.isNotEmpty(dept.getCode())) {
                deptMap.put(dept.getCode().substring(0, 6), dept.getFullname());
            }
        }
        List<Map<String, Object>> lsResult = Lists.newArrayList();
        String deptCode = "";
        String deptName = "";
        for (Map.Entry<String, String> deptMapEntry : deptMap.entrySet()) {
            //判断数据库返回的是否存在该机构的结果
            boolean isExists = false;
            deptCode = deptMapEntry.getKey();
            deptName = deptMapEntry.getValue();
            for (Map<String, Object> resultMap : lsGroup) {
                if (deptCode.equals(resultMap.get("prefix_code"))) {
                    resultMap.put("name", deptName);
                    lsResult.add(resultMap);
                    isExists = true;
                    break;
                }
            }
            //不存在，则添加一个对象
            if (!isExists) {
                Map<String, Object> insertMap = Maps.newHashMap();
                insertMap.put("prefix_code", deptCode);
                insertMap.put("name", deptName);
                insertMap.put("pick_case", 0);
                insertMap.put("finish_case", 0);
                insertMap.put("investigate", 0);
                lsResult.add(insertMap);
            }
        }
        return lsResult;
    }

    @Override
    public List<CasesComparison> listCasesComparison(String ajbh) {
        CasesComparison casesComparison = new CasesComparison();
        casesComparison.setAjbh(ajbh);
        List<CasesComparison> lsCasesComparison = casesComparisonMapper.select(casesComparison);
        return lsCasesComparison;
    }

    @Override
    @Scheduled(cron="0 0 2 * * ?")
    public void autoExtractCasesOtherInfo() {
        List<String> lsAjbh = casesService.listLocalAjbh();
        for(String ajbh : lsAjbh){
            casesService.extractCasesOtherInfo(ajbh);
        }
    }

    @Override
    public void extractCasesOtherInfo() {
        List<String> lsAjbh = casesService.listLocalAjbh();
        for(String ajbh : lsAjbh){
            casesService.extractCasesOtherInfo(ajbh);
        }
    }

    @Override
    public void ajbhExtractCasesOtherInfo(String ajbh) {
        casesService.extractCasesOtherInfo(ajbh);
    }

    @Override
    public PageInfo<Map<String, Object>> listXK(String kssj, String jssj, Page page) {
        return casesService.listXK(kssj, jssj, page);
    }

    /**
     * 根据研判线索编号获取串并的案件数量
     *
     * @param xsbh
     * @return
     */
    @Override
    public Integer countMergeCaseByXsbh(String xsbh) {
        return casesMergeHisService.countMergeCase(xsbh);
    }

    /**
     * 根据研判线索编号获取串并案
     *
     * @param xsbh
     * @return
     */
    @Override
    public List<String> listMergeCaseCodeByXsbh(String xsbh) {
        return casesService.listMergeCaseCodeByXsbh(xsbh);
    }

    /**
     * 根据研判线索编号获取案件信息
     *
     * @param xsbh
     * @return
     */
    @Override
    public List<Cases> listMergeCaseInfoByXsbh(String xsbh) {
        return casesService.listMergeCaseInfoByXsbh(xsbh);
    }

    /**
     * 根据案件编号获取案件的串并案
     *
     * @param ajbh@return
     */
    @Override
    public List<Map<String, String>> listMergeCaseInfoByAjbh(String ajbh) {
        List<Cases> lsCases = casesService.listMergeCaseInfoByAjbh(ajbh);
        List<Map<String, String>> lsResult = Lists.transform(lsCases, input -> {
            Map<String, String> itemMap = Maps.newHashMap();
            itemMap.put("ajbh", input.getAjbh());
            itemMap.put("ajmc", input.getAjmc());
            return itemMap;
        });
        return lsResult;
    }

    /**
     * 根据线索编号获取串并案对象
     *
     * @param xsbh
     * @return
     */
    @Override
    public CasesMerge getCasesMergeByXsbh(String xsbh) {
        return casesService.getCasesMergeByXsbh(xsbh);
    }

    /**
     * 根据案件编号、证件号码获取嫌疑人
     *
     * @param ajbh
     * @param zjhm
     * @return
     */
    @Override
    public CasesSuspect getCasesSuspect(String ajbh, String zjhm) {
        return casesSuspectService.getCasesSuspect(ajbh, zjhm);
    }

    /**
     * 保存嫌疑人
     *
     * @param casesSuspect
     */
    @Override
    public void saveCasesSuspect(CasesSuspect casesSuspect) {
        casesSuspectService.save(casesSuspect);
    }

    @Override
    public List<CasesVO> listCasesVOByAjbhs(List<String> lsAjbh) {
        List<CasesVO> lsResult = Lists.newArrayList();
        lsAjbh.forEach(i -> {
            CasesVO casesVO = new CasesVO();
            Cases cases = casesService.getCaseByAjbh(i);
            commonCasesConvertSectionCasesVO(casesVO, cases);
            lsResult.add(casesVO);
        });
        return lsResult;
    }
}
