package com.hyzs.cidyth.module.base.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.*;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.DateUtil;
import com.hyzs.cidyth.common.utils.DepartmentUtil;
import com.hyzs.cidyth.common.utils.GenericBeanUtils;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.dao.CasesCidMapper;
import com.hyzs.cidyth.module.base.dao.CasesGroupMapper;
import com.hyzs.cidyth.module.base.dao.CasesMapper;
import com.hyzs.cidyth.module.base.dao.CasesMergeMapper;
import com.hyzs.cidyth.module.base.dto.CasesAbortDTO;
import com.hyzs.cidyth.module.base.dto.CasesFinishDTO;
import com.hyzs.cidyth.module.base.entity.*;
import com.hyzs.cidyth.module.base.service.*;
import com.hyzs.cidyth.module.base.vo.CasesMergeVO;
import com.hyzs.cidyth.module.base.vo.CasesRecordKLParam;
import com.hyzs.cidyth.module.base.vo.CasesVO;
import com.hyzs.cidyth.module.base.vo.PersonalCasesVO;
import com.hyzs.cidyth.module.clue.service.ClueService;
import com.hyzs.cidyth.module.dic.service.DicService;
import com.hyzs.cidyth.module.integral.entity.IntegralHis;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.time.service.TimeNodeService;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

@Service("CasesService")
public class CasesServiceImpl implements CasesService {

    private static final Logger logger = LoggerFactory.getLogger(CasesServiceImpl.class);

    @Autowired
    private CasesCidMapper casesCidMapper;//警踪数据访问接口
    @Autowired
    private CasesMapper casesMapper;//本地数据访问层接口
    @Autowired
    private ClueService clueService;
    @Autowired
    private DicService dicService;
    @Autowired
    private UserCenterService userCenterService;
    @Autowired
    private CasesSuspectService casesSuspectService;
    @Autowired
    private CasesRecordService casesRecordService;
    @Autowired
    private CasesInformantService casesInformantService;
    @Autowired
    private CasesLawBookService casesLawBookService;
    @Autowired
    private SceneService sceneService;
    @Autowired
    private TimeNodeService timeNodeService;
    @Autowired
    private CasesMergeMapper casesMergeMapper;
    @Autowired
    private IntegralHisService integralHisService;
    @Autowired
    private CasesGroupService casesGroupService;
    @Autowired
    private CasesService casesService;
    @Autowired
    private CasesGoodsService casesGoodsService;
    @Autowired
    private CasesGroupMapper casesGroupMapper;
    @Autowired
    private CasesComparisonService casesComparisonService;
    @Autowired
    private SceneFingerPrintCidService sceneFingerPrintCidService;
    @Autowired
    private SceneFootPrintCidService sceneFootPrintCidService;
    @Autowired
    private SceneBiologyPrintCidService sceneBiologyPrintCidService;
    @Autowired
    private CasesMergeHisService casesMergeHisService;

    @Override
    @Transactional(value = "masterTransactionManager", rollbackFor = {Exception.class, RuntimeException.class})
    public Cases getCaseByAjbh(String ajbhs) {
        // 获取用户
        User loginUser = UserUtil.getUser();
        if (loginUser.getDepartment() == null) {
            throw new ServiceException("无法获取当前登录用户机构!");
        }
        Cases result = null;
        if (StringUtils.isBlank(ajbhs)) {
            throw new IllegalArgumentException("案件编号不能为空!");
        }
        String[] arrAjbh = ajbhs.split(",");
        for (String ajbh : arrAjbh) {
            Cases param = new Cases();
            param.setAjbh(ajbh);
            Cases cases = casesMapper.selectOne(param);//从本地库查询
            logger.debug("case {} from local.", ajbh);
            if (cases != null) {
                result = new Cases();
                BeanUtils.copyProperties(cases, result);
            } else {
                //从警踪查询
                Cases cidCase = casesCidMapper.selectCaseByAjbh(ajbh);
                logger.debug("case {} from remote.", ajbh);
                if (cidCase != null) {
                    BeanUtils.copyProperties(cidCase, param);
                    // 设置案件来源标记为警综提取
                    param.setAjFrom(Integer.parseInt(CaseFromEnum.PACK.code()));
                    param.setSfcba(Integer.parseInt(CaseSeriesEnum.NOT_SERIES.code()));
                    param.setBdajstate(CaseStateEnum.EXECUTE.code());
                    param.setLrsj(new Date());
                    param.setMindLeft("0");
                    param.setMindTop("0");
                    param.setCheckStatus(0);
                    if (loginUser != null) {
                        param.setLrry(loginUser.getAccount());
                        param.setLrrymc(loginUser.getName());
                    }
                    List<CasesGroup> lsCasesGroup = Lists.newArrayList();
                    // 如果相等则代表是同级部门提取的案件，需要把案件的主办人员、协办人员加入到合成作战小组
                    // 否则为上级提取下级部门的案件，不需要把案件的主办人员、协办人员加入到合成作战小组
                    List<String> lsAccount = Lists.newArrayList();
                    // 用户部门code
                    String userDeptCode = loginUser.getDeptid();
                    // 受理接收单位code
                    String sljsdw = param.getSljsdw();
                    // 如果是同级机构
                    if (userDeptCode.equals(sljsdw)) {
                        if (StringUtils.isNotEmpty(param.getAjzbry())) {
                            lsAccount.add(param.getAjzbry());
                        }
                        if (StringUtils.isNotEmpty(param.getAjxbry())) {
                            for (String item : param.getAjxbry().split(",")) {
                                if (StringUtils.isNotEmpty(item)) {
                                    lsAccount.add(item);
                                }
                            }
                        }
                    }
                    if (!lsAccount.contains(loginUser.getAccount())) {
                        lsAccount.add(loginUser.getAccount());
                    }
                    for (String account : lsAccount) {
                        lsCasesGroup.add(getCasesGroupByUserName(account, param.getAjbh()));
                    }
                    casesGoodsService.insert(ajbh);
                    /*// 保存嫌疑人到本地库
                    casesSuspectService.insert(ajbh);
                    // 保存物品到本地库
                    casesGoodsService.insert(ajbh);
                    // 保存笔录到本地库
                    casesRecordService.insert(ajbh);
                    // 保存报案人、受害人到本地库
                    casesInformantService.insert(ajbh);
                    // 保存法律文书到本地库
                    casesLawBookService.insert(ajbh);
                    // 保存现场基本信息到本地库
                    sceneService.insert(ajbh);*/

                    // 保存主办、协办人员到合成作战小组
                    if (CollectionUtils.isNotEmpty(lsCasesGroup)) {
                        casesGroupMapper.batchInsertCaseGroups(lsCasesGroup);
                    }
                    // 保存案件到本地库
                    int effectCount = casesMapper.insert(param);
                    logger.debug("saved {} case into local db.", effectCount);
                    // 保存时间轴
                    timeNodeService.insert(param.getAjbh(), param.getId(), "CASE_PICK", cidCase.getAjmc(), cidCase.getZyaq());
                    // 保存积分历史
                    integralHisService.insert(param.getAjbh(), loginUser, RuleTypeEnum.CASES_PICK.name());
                    // 提取案件其他信息：笔录、物品、涉案人员、文书、现勘数据
                    extractCasesOtherInfo(ajbh);

                    if (effectCount > 0) {
                        //保存成功.
                        result = param;
                    }
                }
            }
        }
        return result;
    }

    @Override
    @Transactional(value = "masterTransactionManager", rollbackFor = {Exception.class, RuntimeException.class})
    public Cases casesPick(String ajbh) {
        Cases result = null;
        if (StringUtils.isBlank(ajbh)) {
            throw new IllegalArgumentException("案件编号不能为空!");
        }
        Cases param = new Cases();
        param.setAjbh(ajbh);
        Cases cases = casesMapper.selectOne(param);//从本地库查询
        logger.debug("case {} from local.", ajbh);
        if (cases != null) {
            result = new Cases();
            BeanUtils.copyProperties(cases, result);
        } else {
            //从警踪查询
            Cases cidCase = casesCidMapper.selectCaseByAjbh(ajbh);
            logger.debug("case {} from remote.", ajbh);
            if (cidCase != null) {
                BeanUtils.copyProperties(cidCase, param);
                // 获取用户
                User user = UserUtil.getUser();
                if (user == null) {
                    throw new ServiceException("无法获取登录用户");
                }
                // 设置案件来源标记为警综提取
                param.setAjFrom(Integer.parseInt(CaseFromEnum.PACK.code()));
                param.setSfcba(Integer.parseInt(CaseSeriesEnum.NOT_SERIES.code()));
                param.setBdajstate(CaseStateEnum.EXECUTE.code());
                if (user != null) {
                    param.setLrry(user.getAccount());
                    param.setLrrymc(user.getName());
                }
                /*// 保存嫌疑人到本地库
                casesSuspectService.insert(ajbh);
                // 保存物品到本地库
                casesGoodsService.insert(ajbh);
                // 保存笔录到本地库
                casesRecordService.insert(ajbh);
                // 保存报案人、受害人到本地库
                casesInformantService.insert(ajbh);
                // 保存法律文书到本地库
                casesLawBookService.insert(ajbh);
                // 保存现场基本信息到本地库
                sceneService.insert(ajbh);*/

                extractCasesOtherInfo(ajbh);

                // 保存案件到本地库
                int effectCount = casesMapper.insert(param);
                logger.debug("saved {} case into local db.", effectCount);
                if (effectCount > 0) {
                    //保存成功.
                    result = cidCase;
                }
            }
        }
        return result;
    }

    /**
     * 根据警号和案件编号生成合成作战小组对象
     *
     * @param account
     * @param ajbh
     * @return
     */
    public CasesPartner getCasesPartnerByUserName(String account, String ajbh) {
        try {
            User user = userCenterService.getUserByUserName(account);
            CasesPartner casesPartner = new CasesPartner();
            casesPartner.setAjbh(ajbh);
            casesPartner.setJgdm(user.getDeptid());
            casesPartner.setJgmc(user.getDepartment() == null ? "" : user.getDepartment().getSimplename());
            casesPartner.setJybh(user.getAccount());
            casesPartner.setJyxm(user.getName());
            casesPartner.setSjhm(user.getPhone());
            casesPartner.setLrryjgdm(user.getDeptid());
            casesPartner.setLrryjgmc(user.getDepartment() == null ? "" : user.getDepartment().getSimplename());
            casesPartner.setLrrybh(user.getAccount());
            casesPartner.setLrrymc(user.getName());
            return casesPartner;
        } catch (Exception e) {
            throw new ServiceException("无法获取用户信息");
        }
    }

    /**
     * 根据警号和案件编号生成合成作战小组对象
     *
     * @param account
     * @param ajbh
     * @return
     */
    @Override
    public CasesGroup getCasesGroupByUserName(String account, String ajbh) {
        try {
            User user = userCenterService.getUserByUserName(account);
            CasesGroup casesGroup = new CasesGroup();
            casesGroup.setAjbh(ajbh);
            casesGroup.setJgdm(user.getDeptid());
            casesGroup.setJgmc(user.getDepartment() == null ? "" : user.getDepartment().getSimplename());
            casesGroup.setJybh(user.getAccount());
            casesGroup.setJyxm(user.getName());
            casesGroup.setSjhm(user.getPhone());
            casesGroup.setLrryjgdm(user.getDeptid());
            casesGroup.setLrryjgmc(user.getDepartment() == null ? "" : user.getDepartment().getSimplename());
            casesGroup.setLrrybh(user.getAccount());
            casesGroup.setLrrymc(user.getName());
            return casesGroup;
        } catch (Exception e) {
            throw new ServiceException("无法获取用户信息");
        }
    }

    @Override
    public PageInfo<Cases> listCaseLocal(CasesVO casesParam, Page page) {
        // 获取用户
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        if (page == null) {
            throw new ServiceException("分页参数不可空!");
        }
        if (casesParam == null) {
            casesParam = new CasesVO();
        }
        // 限制按照部分机构编号查询
        String sectionDeptCode = DepartmentUtil.getSectionDeptCode(loginUser.getDepartment().getPolityLevel(),
                loginUser.getDepartment().getCode());
        if (StringUtils.isNotEmpty(sectionDeptCode)) {
            // 长度为6代表分局机构
            if (sectionDeptCode.length() == 6) {
                casesParam.setSljsdw(sectionDeptCode);
                /*
					如果分局的人的单位不为空，并且不包含分局前缀6位编号，则代表查询条件的受理单位不属于分局下的
					比如查询了分局的上级机构，就设置受理单位为null
				 */
                if (StringUtils.isNotEmpty(casesParam.getZbdw()) && casesParam.getZbdw().indexOf(sectionDeptCode) < 0) {
                    casesParam.setSljsdw(null);
                }
            } else {
                casesParam.setSljsdw(sectionDeptCode);
            }
        }

        if(AuthorityUtil.check(loginUser.getAccount())){
            casesParam.setSljsdw(null);
        }

        casesParam.setJybh(loginUser.getAccount());

        Map<String, Object> param = GenericBeanUtils.convertSimpleBean2Map(casesParam);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        if (StringUtils.isNotBlank(page.getOrderBy())) {
            PageHelper.orderBy(page.getOrderBy());
        }
        PageInfo<Cases> pageCases = new PageInfo<Cases>(casesMapper.listCaseLocal(param));
        return pageCases;
    }

    @Override
    public PageInfo<Cases> listCaseLocalAll(CasesVO casesParam, Page page) {
        // 获取用户
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        if (page == null) {
            throw new ServiceException("分页参数不可空!");
        }
        Map<String, Object> param = GenericBeanUtils.convertSimpleBean2Map(casesParam);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        if (StringUtils.isNotBlank(page.getOrderBy())) {
            PageHelper.orderBy(page.getOrderBy());
        }
        PageInfo<Cases> pageCases = new PageInfo<Cases>(casesMapper.listCaseLocal(param));
        return pageCases;
    }

    @Override
    public List<Cases> listCaseLocal(Cases casesParam) {
        List<Cases> lsCases = casesMapper.listCaseLocal(GenericBeanUtils.convertSimpleBean2Map(casesParam));
        return lsCases;
    }

    @Override
    public Map<String, String> countGroupByBdajstate() {
        Map<String, String> mapResult = null;
        List<String> lsCountGroup = casesMapper.countGroupByBdajstate();
        if (lsCountGroup != null && lsCountGroup.size() > 0) {
            mapResult = new HashMap<String, String>();
            String[] itemResult = null;
            for (String item : lsCountGroup) {
                itemResult = item.split(",");
                mapResult.put(CaseStateEnum.ofCode(itemResult[0]).name(), itemResult[1]);
            }
        }
        return mapResult;
    }

    @Override
    public Cases detail(String ajbh) {
        Cases casesParam = new Cases();
        casesParam.setAjbh(ajbh);
        Cases cases = casesMapper.selectOne(casesParam);
        return cases;
    }

    @Override
    public void casesFinish(CasesFinishDTO casesFinishDTO) {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取当前登录用户!");
        }
        if (casesFinishDTO == null) {
            throw new ServiceException("无法获取参数信息!");
        }
        Cases cases = new Cases();
        cases.setAjbh(casesFinishDTO.getAjbh());
        cases = casesMapper.selectOne(cases);
        if (cases == null) {
            throw new ServiceException("无法获取案件信息!");
        }
        if (isFinish(casesFinishDTO.getAjbh())) {
            throw new ServiceException("该案件已侦结!");
        }
        if (loginUser.getAccount().equals(cases.getLrry()) || isExistsDepartmentLeader(loginUser.getAccount(), cases.getSljsdw(), RoleEnum.CID_LEADER_PERSON.code())) {
            // 保存时间轴
            timeNodeService.insert(casesFinishDTO.getAjbh(), cases.getId(), "CASE_FINISH", cases.getAjmc(), cases.getZyaq());
            // 保存积分历史
            integralHisService.insert(casesFinishDTO.getAjbh(), loginUser, RuleTypeEnum.CASES_FINISH.name());
            // 保存案件
            casesMapper.updateBdajstateByAjbh(casesFinishDTO.getAjbh(), Integer.parseInt(CaseStateEnum.FINISH.code()), casesFinishDTO.getBdajstatebz());
            // 保存关键线索评分
            clueService.saveClueEvaluate(casesFinishDTO.getAjbh(), casesFinishDTO.getLsClueAppraiseSave());
        } else {
            throw new ServiceException("没有当前操作权限!");
        }
    }

    @Override
    public void casesAbort(CasesAbortDTO casesAbortDTO) {
        if (casesAbortDTO == null) {
            throw new ServiceException("无法获取参数信息!");
        }
        if (StringUtils.isBlank(casesAbortDTO.getAjbh())) {
            throw new ServiceException("案件编号为空!");
        }
        if (isFinish(casesAbortDTO.getAjbh())) {
            throw new ServiceException("该案件已侦结!");
        }
        Cases cases = new Cases();
        cases.setAjbh(casesAbortDTO.getAjbh());
        cases = casesMapper.selectOne(cases);
        if (cases != null) {
            // 时间轴新增
            timeNodeService.insert(casesAbortDTO.getAjbh(), cases.getId(), "CASE_ABORT", cases.getAjmc(), cases.getZyaq());
        }
        casesMapper.updateBdajstateByAjbh(casesAbortDTO.getAjbh(), Integer.parseInt(CaseStateEnum.ABORT.code()), casesAbortDTO.getBdajstatebz());
    }

    @Override
    public void casesCheck(String ajbh, Integer checkStatus, String checkResult) {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        casesMapper.casesCheck(ajbh, loginUser.getAccount(), checkStatus, checkResult);
    }

    @Override
    @Transactional(value = "masterTransactionManager", rollbackFor = {RuntimeException.class, Exception.class})
    public void seriesAjByAjbh(CasesMergeVO casesMergeVO) {
        seriesAjByAjbh(casesMergeVO, null);
    }

    /**
     * 根据案件编号串并案
     *
     * @param casesMergeVO
     * @param xsbh
     * @return
     */
    @Override
    @Transactional(value = "masterTransactionManager", rollbackFor = {RuntimeException.class, Exception.class})
    public void seriesAjByAjbh(CasesMergeVO casesMergeVO, String xsbh) {
        // 获取用户
        User loginUser = UserUtil.getUser();
        if (StringUtils.isBlank(casesMergeVO.getoAjbh())) {
            throw new ServiceException("无法获取原案件编号!");
        }
        if (StringUtils.isBlank(casesMergeVO.getnAjbh())) {
            throw new ServiceException("无法获取串并的案件编号!");
        }
        //未串并的案件编号和ID
        Map<String, Integer> mapNotMergeCaseCodeId = Maps.newHashMap();
        //原案件编号所串并的所有案件编号集合
        List<String> lsOldAjbh = Lists.newArrayList();
        //新案件编号集合
        List<String> lsNewAjbh = Lists.newArrayList();
        //所有案件所关联的串并案编号集合
        List<String> lsAllAjbh = Lists.newArrayList();

        Cases oldCases = getCaseByAjbh(casesMergeVO.getoAjbh());
        //如果是串并案，则获取案件所在的同一批案件编号
        if(oldCases.getSfcba().equals(Integer.valueOf(CaseSeriesEnum.SERIES.code()))){
            lsOldAjbh = casesMergeHisService.listMergeCaseCodeByCaseCode(casesMergeVO.getoAjbh());
        }
        if(CollectionUtils.isEmpty(lsOldAjbh)){
            lsOldAjbh.add(casesMergeVO.getoAjbh());
            mapNotMergeCaseCodeId.put(oldCases.getAjbh(), oldCases.getId());
        }

        //添加到所有案件所串并的案件编号
        lsAllAjbh.addAll(lsOldAjbh);

        //不可变对象
        final List<String> lsOldAjbhFinal = lsOldAjbh;

        lsNewAjbh = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .splitToList(casesMergeVO.getnAjbh()).stream().distinct().collect(Collectors.toList());


        //排除已存在lsOldAjbh的案件编号
        lsNewAjbh = lsNewAjbh.stream().filter(i -> !lsOldAjbhFinal.contains(i)).collect(Collectors.toList());
        if(CollectionUtils.isEmpty(lsNewAjbh)){
            throw new ServiceException("原案件和目标案件已经串并");
        }

        Cases newCases = null;
        //根据lsNewAjbh获取每个案件编号所在的串并案的所有案件编号
        for (String newAjbh : lsNewAjbh) {
            //还未出现在所有案件的串并案的编号集合里，说明没有重复的案件
            if(!lsAllAjbh.contains(newAjbh)){
                newCases = getCaseByAjbh(newAjbh);
                lsAllAjbh.add(newAjbh);
                List<String> lsBatchCode = Lists.newArrayList();
                //如果是串并案，则获取案件所在的同一批案件编号，为了兼容以前没有在历史表的逻辑，这里
                //判断如果是串并了，但是历史串并记录找不到，则用一个集合接收，下面判断如果集合是空，
                // 则添加每个newAjbh（之前的业务，串并的案件不在串并历史表里，所以这里要判断是否找到）
                if(newCases != null && newCases.getSfcba().equals(Integer.valueOf(CaseSeriesEnum.SERIES.code()))){
                    lsBatchCode = casesMergeHisService.listMergeCaseCodeByCaseCode(newAjbh);
                    lsAllAjbh.addAll(lsBatchCode);
                }
                if(CollectionUtils.isEmpty(lsBatchCode)){
                    mapNotMergeCaseCodeId.put(newCases.getAjbh(), newCases.getId());
                }
            }
        }

        //去重
        lsAllAjbh = lsAllAjbh.stream().distinct().collect(Collectors.toList());

        // 生成串并案编号
        String cbabh = UUID.randomUUID().toString().replace("-", "");
        CasesMerge casesMerge = new CasesMerge();
        BeanUtils.copyProperties(casesMergeVO, casesMerge);
        casesMerge.setCbabh(cbabh);
        casesMerge.setXsbh(xsbh);
        if (loginUser != null) {
            casesMerge.setCbary(loginUser.getAccount());
            casesMerge.setLrry(loginUser.getAccount());
            casesMerge.setLrrymc(loginUser.getName());
            casesMerge.setCbajg(loginUser.getDeptid());
        }
        casesMerge.setSpld("");
        casesMerge.setSpyj("否");
        casesMergeMapper.insertSelective(casesMerge);

        //串并了未串并的编号则得到积分
        mapNotMergeCaseCodeId.forEach((k, v) -> integralHisService.insert(k, loginUser, RuleTypeEnum.CASES_MERGE.name()));

        //更新一批案件为新的串并案编号和标识
        updateList(Integer.parseInt(CaseSeriesEnum.SERIES.code()), lsAllAjbh);

        //批量保存本次串并的记录到串并历史
        casesMergeHisService.insertList(lsAllAjbh, cbabh, xsbh);
    }

    /**
     * 通过案件编号获取SceneVO对象内容
     *
     * @param ajbh 案件编号
     * @return SceneVO
     */
	/*private List<SceneVO> getSceneVOListByCaseNO(String ajbh){
		List<SceneVO> result = Lists.newArrayList();
		if(StringUtils.isBlank(ajbh))
			return result;

		result = sceneService.list(ajbh);

		return result;
	}*/

    /**
     * 通过案件编号获取CasesVO对象内容
     *
     * @param ajbh 案件编号
     * @return CasesVO
     */
	/*private CasesVO getCasesVOByCaseNO(String ajbh) {
		Cases casesParam = new Cases();
		casesParam.setAjbh(ajbh);
		Cases cases = this.getCaseByAjbh(ajbh);
		CasesVO casesVO = new CasesVO();
		BeanUtils.copyProperties(cases, casesVO);

		try {
			// 受理接收单位不为空，从用户中心获取
			if(StringUtils.isNotEmpty(cases.getSljsdw())){
				Dept deptSljsdw = userCenterService.getDeptByCode(cases.getSljsdw());
				casesVO.setSljsdwCn(deptSljsdw == null ? "" : deptSljsdw.getFullname());
			}
			// 立案单位不为空，从用户中心获取
			if(StringUtils.isNotEmpty(cases.getLadw())){
				Dept deptLadw = userCenterService.getDeptByCode(cases.getLadw());
				casesVO.setLadwCn(deptLadw == null ? "" : deptLadw.getFullname());
			}
			// 主办单位不为空，从用户中心获取
			if(StringUtils.isNotEmpty(cases.getZbdw())){
				Dept deptZbdw = userCenterService.getDeptByCode(cases.getZbdw());
				casesVO.setZbdwCn(deptZbdw == null ? "" : deptZbdw.getFullname());
			}
			// 主办人员不为空，从用户中心获取
			if(StringUtils.isNotEmpty(cases.getAjzbry())){
				User userAjzbry = userCenterService.getUserByUserName(cases.getAjzbry());
				casesVO.setAjzbryCn(userAjzbry == null ? "" : userAjzbry.getName());
			}
			// 协办人员不为空，从用户中心获取
			if(StringUtils.isNotEmpty(cases.getAjxbry())){
				StringBuilder stringBuilder = new StringBuilder();
				String[] ajxbry = cases.getAjxbry().split(",");
				for(String item : ajxbry){
					User userAjxbry = userCenterService.getUserByUserName(item);
					if(userAjxbry != null){
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
		if(StringUtils.isNotEmpty(casesVO.getAb())){
			casesVO.setAbCn(dicService.getValueByKey("ab", casesVO.getAb()));
		}
		//(本地)案件状态
		if(StringUtils.isNotEmpty(casesVO.getBdajstate())){
			casesVO.setBdajstateCn(dicService.getValueByKey("bdajstate", casesVO.getBdajstate()));
		}
		//作案状态
		if(StringUtils.isNotEmpty(casesVO.getZazt())){
			casesVO.setZaztCn(dicService.getValueByKey("zazt", casesVO.getZazt()));
		}
		//接警方式
		if(StringUtils.isNotEmpty(casesVO.getSlJjfs())){
			casesVO.setSlJjfsCn(dicService.getValueByKey("sljjfs", casesVO.getSlJjfs()));
		}
		//案件类型
		if(StringUtils.isNotEmpty(casesVO.getAjlx())){
			casesVO.setAjlxCn(dicService.getValueByKey("ajlx", casesVO.getAjlx()));
		}
		//专案标识
		if(StringUtils.isNotEmpty(casesVO.getZabz())){
			casesVO.setZabzCn(dicService.getValueByKey("zabz", casesVO.getZabz()));
		}
		//发案地点区县
		if(StringUtils.isNotEmpty(casesVO.getFaddQx())){
			casesVO.setFaddQxCn(dicService.getValueByKey("faddqx", casesVO.getFaddQx()));
		}
		//发案地点街道
		if(StringUtils.isNotEmpty(casesVO.getFaddJd())){
			casesVO.setFaddJdCn(dicService.getValueByKey("faddjd", casesVO.getFaddJd()));
		}
		//案件所属警区
		if(StringUtils.isNotEmpty(casesVO.getAjssjq())){
			casesVO.setAjssjqCn(dicService.getValueByKey("ajssjq", casesVO.getAjssjq()));
		}
		//所属社区
		if(StringUtils.isNotEmpty(casesVO.getSssq())){
			casesVO.setSssqCn(dicService.getValueByKey("sssq", casesVO.getSssq()));
		}
		//发案地域
		if(StringUtils.isNotEmpty(casesVO.getFady())){
			casesVO.setFadyCn(dicService.getValueByKey("fady", casesVO.getFady()));
		}
		//发现形式
		if(StringUtils.isNotEmpty(casesVO.getFxxs())){
			casesVO.setFxxsCn(dicService.getValueByKey("fxxs", casesVO.getFxxs()));
		}
		//案件危害程度
		if(StringUtils.isNotEmpty(casesVO.getAjwhcd())){
			casesVO.setAjwhcdCn(dicService.getValueByKey("ajwhcd", casesVO.getAjwhcd()));
		}
		//选择时机
		if(StringUtils.isNotEmpty(casesVO.getXzsj())){
			String[] xzsj = casesVO.getXzsj().split(",");
			stringBuilder.setLength(0);
			for(String item : xzsj){
				String xzsjValue = dicService.getValueByKey("xzsj", item);
				if(StringUtils.isNotEmpty(xzsjValue)){
					stringBuilder.append(xzsjValue).append(",");
				}
			}
			if(StringUtils.isNotEmpty(stringBuilder.toString())){
				casesVO.setXzsjCn(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
			}
		}
		//选择处所
		if(StringUtils.isNotEmpty(casesVO.getXzcs())){
			casesVO.setXzcsCn(dicService.getValueByKey("xzcs", casesVO.getXzcs()));
		}
		//选择对象
		if(StringUtils.isNotEmpty(casesVO.getXzdx())){
			String[] xzdx = casesVO.getXzdx().split(",");
			stringBuilder.setLength(0);
			for(String item : xzdx){
				String xzdxValue = dicService.getValueByKey("xzdx", item);
				if(StringUtils.isNotEmpty(xzdxValue)){
					stringBuilder.append(xzdxValue).append(",");
				}
			}
			if(StringUtils.isNotEmpty(stringBuilder.toString())){
				casesVO.setXzdxCn(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
			}
		}
		//选择物品
		if(StringUtils.isNotEmpty(casesVO.getXzwp())){
			casesVO.setXzwpCn(dicService.getValueByKey("xzwp", casesVO.getXzwp()));
		}
		//作案工具
		if(StringUtils.isNotEmpty(casesVO.getZagj())){
			String[] zagj = casesVO.getZagj().split(",");
			stringBuilder.setLength(0);
			for(String item : zagj){
				String zagjValue = dicService.getValueByKey("wpmc", item);
				if(StringUtils.isNotEmpty(zagjValue)){
					stringBuilder.append(zagjValue).append(",");
				}
			}
			if(StringUtils.isNotEmpty(stringBuilder.toString())){
				casesVO.setZagjCn(stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
			}
		}
		//选择部位
		if(StringUtils.isNotEmpty(casesVO.getXzbw())){
			casesVO.setXzbwCn(dicService.getValueByKey("xzbw", casesVO.getXzbw()));
		}
		//手段特点
		if(StringUtils.isNotEmpty(casesVO.getSdtd())){
			casesVO.setSdtdCn(dicService.getValueByKey("sdtd", casesVO.getSdtd()));
		}
		//涉案主体
		if(StringUtils.isNotEmpty(casesVO.getSazz())){
			casesVO.setSazzCn(dicService.getValueByKey("sazz", casesVO.getSazz()));
		}
		//督办级别
		if(StringUtils.isNotEmpty(casesVO.getDbjb())){
			casesVO.setDbjbCn(dicService.getValueByKey("dbjb", casesVO.getDbjb()));
		}
		//犯罪主体类型
		if(StringUtils.isNotEmpty(casesVO.getFzztlx())){
			casesVO.setFzztlxCn(dicService.getValueByKey("fzztlx", casesVO.getFzztlx()));
		}
		//是否涉外
		if(StringUtils.isNotEmpty(casesVO.getSfsw())){
			casesVO.setSfswCn(dicService.getValueByKey("sfsw", casesVO.getSfsw()));
		}
		//涉及国家地区
		if(StringUtils.isNotEmpty(casesVO.getSjgjdq())){
			casesVO.setSjgjdqCn(dicService.getValueByKey("sjgjdq", casesVO.getSjgjdq()));
		}
		//案件来源类型（1、从警综提取，2、手动录入案件）
		if(casesVO.getAjFrom() != null){
			casesVO.setAjFromCn(dicService.getValueByKey("aj_from", String.valueOf(casesVO.getAjFrom())));
		}
		//保密级别
		if(StringUtils.isNotEmpty(casesVO.getSecuritygrade())){
			casesVO.setAjFromCn(dicService.getValueByKey("securitygrade", casesVO.getSecuritygrade()));
		}

		return casesVO;
	}*/
    @Override
    public Integer insert(Cases cases) {
        // 获取用户
        User loginUser = UserUtil.getUser();
        ;
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        cases.setBdajstate(CaseStateEnum.EXECUTE.code());
        cases.setLrry(loginUser.getAccount());
        cases.setLrrymc(loginUser.getName());
        cases.setAjFrom(Integer.parseInt(CaseFromEnum.CREATE.code()));
        casesMapper.insertSelective(cases);

        // 时间轴新增
        timeNodeService.insert(cases.getAjbh(), cases.getId(), "CASE_CREATE", cases.getAjmc(), cases.getZyaq());
        // 保存积分历史
        integralHisService.insert(cases.getAjbh(), loginUser, RuleTypeEnum.CASES_CREATE.name());
        return cases.getId();
    }

    @Override
    public List<Map<String, Object>> memberGroup(String ajbh) {

        // 保存参与案件的所有人员编号
        CasesGroup param = new CasesGroup();
        param.setAjbh(ajbh);
        List<CasesGroup> lsCasesPartner = casesGroupMapper.select(param);
		/*
		// 【需求】获取参与案件的需求人员
		Demand demand = new Demand();
		demand.setAjbh(ajbh);
		List<Demand> lsDemand = demandMapper.select(demand);
		for(Demand item : lsDemand){
			lsMemberGroup.add(item.getLrry());
			// 获取指派领导人员
			lsMemberGroup.addAll(Stream.of(item.getZpld().split(",")).collect(Collectors.toList()));
			// 如果需求状态为【已签收】或【已指派】，则查找需求节点表的人员加入到合成作战小组
			if(item.getQszt().equals(DemandStatus.SIGNED.name()) || item.getQszt().equals(DemandStatus.FEEDBACKED.name())){
				DemandFlowHis demandFlowHis = new DemandFlowHis();
				demandFlowHis.setXqid(item.getId());
				List<DemandFlowHis> lsDemandFlowHis = demandFlowHisMapper.select(demandFlowHis);
				for(DemandFlowHis flow : lsDemandFlowHis){
					// 不是等待签收的，都加入到合成作战小组
					if(!flow.getQszt().equals(DemandFlowStepStatus.WAIT_FOR_SIGN.name())){
						lsMemberGroup.add(flow.getJsrybh());
					}
				}
			}
		}

		// 【信息】获取参与案件的人员
		Info info = new Info();
		info.setAjbh(ajbh);
		List<Info> lsInfo = infoMapper.select(info);
		for(Info item : lsInfo){
			lsMemberGroup.add(item.getLrry());
		}

		// 【反馈线索】获取参与案件的人员
		Clue clue = new Clue();
		clue.setAjbh(ajbh);
		List<Clue> lsClue = clueMapper.select(clue);
		for(Clue item : lsClue){
			lsMemberGroup.add(item.getLrry());
		}

		// 去重复
		List<String> lsUniqueMember = lsMemberGroup.stream().distinct().collect(Collectors.toList());

*/
        // 存储结构
        List<Map<String, Object>> lsResult = new ArrayList<>();
        for (CasesGroup casesGroup : lsCasesPartner) {
            Map<String, Object> map = new HashMap<>();
            String account = casesGroup.getJybh();
            User user = null;
            try {
                if (StringUtils.isNotEmpty(account)) {
                    user = userCenterService.getUserByUserName(account);
                }
            } catch (Exception e) {
                throw new ServiceException("无法获取用户");
            }
            if (user != null) {
                map.put("account", user.getAccount());
                map.put("name", user.getName());
                map.put("phone", user.getPhone());
                String roleNames = "";
                for (Role role : user.getRoles()) {
                    if (role != null && StringUtils.isNotEmpty(role.getName())) {
                        roleNames = role.getName() + ",";
                    }
                }
                map.put("roleName", StringUtils.isNotEmpty(roleNames) ? roleNames.subSequence(0, roleNames.length() - 1) : "");
                Dept dept = user.getDepartment();
                if (dept != null) {
                    map.put("deptName", dept.getFullname());
                }
                map.put("score", 0);
                lsResult.add(map);
            }
        }

        return lsResult;
    }

    @Override
    public boolean isFinish(String ajbh) {
        if (StringUtils.isBlank(ajbh)) {
            throw new ServiceException("无法获取案件编号");
        }
        Cases cases = new Cases();
        cases.setAjbh(ajbh);
        cases = casesMapper.selectOne(cases);
        if (cases == null) {
            throw new ServiceException("无法获取案件");
        }
        if (StringUtils.isEmpty(cases.getBdajstate())) {
            throw new ServiceException("案件本地状态为空");
        }
        if (cases.getBdajstate().equals(CaseStateEnum.FINISH.code())) {
            return true;
        } else {
            return false;
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

    @Override
    public PageInfo<PersonalCasesVO> listCasesByMonth(Page page, String beginTime, String endTime) {
        User loginUser = UserUtil.getUser();
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        PageInfo<Cases> pageInfo = new PageInfo<>(casesMapper.listCasesByMonth(loginUser.getAccount(), beginTime, endTime));
        PageInfo<PersonalCasesVO> pageResult = new PageInfo<>();
        BeanUtils.copyProperties(pageInfo, pageResult);
        List<PersonalCasesVO> lsPersonalCasesVO = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(pageInfo.getList())) {
            for (Cases cases : pageInfo.getList()) {
                PersonalCasesVO personalCasesVO = new PersonalCasesVO();
                BeanUtils.copyProperties(cases, personalCasesVO);
                IntegralHis integralHis = new IntegralHis();
                integralHis.setAjbh(personalCasesVO.getAjbh());
                personalCasesVO.setScore(integralHisService.countScore(integralHis));// 本案我的积分
                personalCasesVO.setScoreRank(integralHisService.scoreRank(personalCasesVO.getAjbh(), loginUser.getAccount()));// 本案积分排名
                personalCasesVO.setJoinPersonNum(casesGroupService.countCasesJoinPerson(personalCasesVO.getAjbh()));// 本案参与人数
                personalCasesVO.setBdajstateCn(dicService.getValueByKey("bdajstate", cases.getBdajstate()));
                if (StringUtils.isNotBlank(cases.getAjzbry())) {
                    try {
                        User user = userCenterService.getUserLazyByUserName(cases.getAjzbry());
                        personalCasesVO.setAjzbryCn(user == null ? "" : user.getName());
                    } catch (Exception e) {
                        throw new ServiceException("无法获取案件主办人员!");
                    }
                }
                if (StringUtils.isNotBlank(cases.getZbdw())) {
                    try {
                        Dept dept = userCenterService.getDeptByCode(cases.getZbdw());
                        personalCasesVO.setZbdwCn(dept == null ? "" : dept.getFullname());
                    } catch (Exception e) {
                        throw new ServiceException("无法获取主办单位!");
                    }
                }
                lsPersonalCasesVO.add(personalCasesVO);
            }
        }
        pageResult.setList(lsPersonalCasesVO);
        return pageResult;
    }

    @Override
    public Map<String, Object> finishTotal() {
        User loginUser = UserUtil.getUser();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        Cases casesParam = new Cases();
        casesParam.setLrry(loginUser.getAccount());
        casesParam.setBdajstate(CaseStateEnum.FINISH.code());

        Map<String, Object> mapResult = Maps.newHashMap();
        mapResult.put("myFinish", casesMapper.selectCount(casesParam));
        mapResult.put("helpFinish", casesMapper.helpFinish(loginUser.getAccount()));

        return mapResult;
    }

    @Override
    public void addCoordinator(String ajbh, String ajxbry) {
        casesMapper.addCoordinator(ajbh, ajxbry);
    }

    @Override
    public Map<String, Object> isMemberGroup(String ajbh, String account) {
        Map<String, Object> map = Maps.newHashMap();
        int result = casesGroupMapper.isExist(ajbh, account);
        if (result > 0) {
            map.put("isExists", true);
        } else {
            map.put("isExists", false);
        }
        return map;
    }

    @Override
    public Map<String, Object> allowReadSeries(String ajbh) {
        User loginUser = UserUtil.getUser();
        Dept loginUserDept = loginUser.getDepartment();
        if (loginUser == null) {
            throw new ServiceException("无法获取登录用户");
        }
        if (StringUtils.isEmpty(ajbh)) {
            throw new ServiceException("无法获取案件编号");
        }
        Cases cases = casesService.getCaseByAjbh(ajbh);
        String sljsdw = cases.getSljsdw();
        Map<String, Object> result = Maps.newHashMap();
        // 受理接收单位为空，提前返回
        if (StringUtils.isEmpty(sljsdw)) {
            result.put("result", false);
            return result;
        }
        // 如果是市局用户，则不限制查看串并案
        if(loginUserDept != null && Integer.valueOf(PolityLevelEnum.CITY.code()).equals(loginUserDept.getPolityLevel())){
            result.put("result", true);
        }
        try {
            result =  userCenterService.isChildren(loginUser.getDeptid(), sljsdw);
        } catch (Exception e) {
            throw new ServiceException("无法获取机构");
        }
        return result;
    }

    @Override
    public List<String> listLocalAjbh() {
        return casesMapper.listLocalAjbh();
    }

    @Override
    public void extractCasesOtherInfo(String ajbh) {
        Cases paramCases = new Cases();
        paramCases.setAjbh(ajbh);
        Cases cases = casesMapper.selectOne(paramCases);
        String account = cases.getLrry();
        User receiveUser = null;
        Dept receiveUserDept = null;
        Dept sendUserDept = null;
        try {
            sendUserDept = userCenterService.getDeptByCode("440300190201");
            receiveUser = userCenterService.getUserByUserName(account);
            if(receiveUser != null){
                receiveUserDept = receiveUser.getDepartment();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存嫌疑人到本地库
        casesSuspectService.insert(ajbh, sendUserDept, receiveUserDept);
        // 保存物品到本地库
        // 暂时注释掉，查询太久
        //casesGoodsService.insert(ajbh);
        // 保存笔录到本地库
        casesRecordService.insert(ajbh);
        // 保存报案人、受害人到本地库
        casesInformantService.insert(ajbh);
        // 保存法律文书到本地库
        casesLawBookService.insert(ajbh);
        // 保存现场基本信息到本地库
        sceneService.insert(ajbh, sendUserDept, receiveUserDept);
        // 保存比中信息到本地库
        casesComparisonService.insert(cases.getId(), ajbh, sendUserDept, receiveUserDept, receiveUser);
        // 保存考拉的笔录到本地库（在保存嫌疑人、报案人受害人之后执行）
        // 1、查询得到嫌疑人（案件编号、人员编号、证件号码）
        // 2、查询得到受害人报案人（案件编号、人员编号、证件号码）
        // 3、合并上面2个数据源到一个CasesRecordKLParam集合里批量执行
        List<CasesSuspect> lsCasesSuspect = casesSuspectService.list(ajbh);
        List<CasesInformant> lsCasesInformant = casesInformantService.list(ajbh);
        List<CasesRecordKLParam> lsCasesRecordKLParam = Lists.newArrayList();
        lsCasesSuspect.stream().forEach(i ->
                {
                    CasesRecordKLParam param = new CasesRecordKLParam();
                    param.setAjbh(i.getAjbh());
                    param.setRybh(i.getRybh());
                    param.setSfzh(i.getZjhm());
                    lsCasesRecordKLParam.add(param);
                }
        );
        lsCasesInformant.stream().forEach(i ->
                {
                    CasesRecordKLParam param = new CasesRecordKLParam();
                    param.setAjbh(i.getAjbh());
                    param.setRybh(i.getRybh());
                    param.setSfzh(i.getZjhm());
                    lsCasesRecordKLParam.add(param);
                }
        );
        if(CollectionUtils.isNotEmpty(lsCasesRecordKLParam)){
            casesRecordService.insertRecordKL(lsCasesRecordKLParam, sendUserDept, receiveUserDept);
        }
    }

    @Override
    public PageInfo<Map<String, Object>> listXK(String kssj, String jssj, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(casesMapper.listXK(kssj, jssj));
        for (Map<String, Object> item : pageInfo.getList()) {
            item.put("zj_count_remote", sceneFootPrintCidService.countByAjbh(item.get("ajbh").toString()));
            item.put("sy_count_remote", sceneFingerPrintCidService.countByAjbh(item.get("ajbh").toString()));
            item.put("sw_count_remote", sceneBiologyPrintCidService.countByAjbh(item.get("ajbh").toString()));
        }
        return pageInfo;
    }

    /**
     * 根据研判线索编号获取串并案
     *
     * @param xsbh
     * @return
     */
    @Override
    public List<String> listMergeCaseCodeByXsbh(String xsbh) {
        List<String> lsResult = Lists.newArrayList();
        List<Cases> lsCases = listMergeCaseInfoByXsbh(xsbh);
        lsCases.stream().forEach(i -> lsResult.add(i.getAjbh()));
        return lsResult;
    }

    /**
     * 获取该案件编号的串并案的所有案件编号
     * @param ajbh
     * @return
     */
    public List<Cases> listMergeCaseInfoByXsbh(String xsbh) {
        List<Cases> lsResult = Lists.newArrayList();
        if(StringUtils.isBlank(xsbh)){
            return lsResult;
        }
        CasesMerge casesMerge = getCasesMergeByXsbh(xsbh);
        if(casesMerge != null && StringUtils.isNotBlank(casesMerge.getCbabh())){
            List<String> lsAjbh = casesMergeHisService.listCaseCodeByCbabh(casesMerge.getCbabh());
            Example example = new Example(Cases.class);
            example.createCriteria().andIn("ajbh", lsAjbh);
            lsResult = casesMapper.selectByExample(example);
        }
        return lsResult;
    }

    /**
     * 根据案件编号获取串并案
     *
     * @param ajbh
     * @return
     */
    @Override
    public List<Cases> listMergeCaseInfoByAjbh(String ajbh) {
        List<String> lsAjbh = casesMergeHisService.listMergeCaseCodeByCaseCode(ajbh);
        List<Cases> lsResult = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(lsAjbh)){
            Example example = new Example(Cases.class);
            example.createCriteria().andIn("ajbh", lsAjbh);
            lsResult = casesMapper.selectByExample(example);
        }
        return lsResult;
    }

    /**
     * 线索编号
     *
     * @param xsbh
     * @return
     */
    @Override
    public CasesMerge getCasesMergeByXsbh(String xsbh) {
        CasesMerge queryParam = new CasesMerge();
        queryParam.setXsbh(xsbh);
        return casesMergeMapper.selectOne(queryParam);
    }

    /**
     * 批量更新串并案标识
     * @param sfcba
     * @param lsAjbh
     */
    public void updateList(Integer sfcba, List<String> lsAjbh){
        Example example = new Example(Cases.class);
        example.createCriteria().andIn("ajbh", lsAjbh);
        Cases updateObject = new Cases();
        updateObject.setSfcba(sfcba);
        casesMapper.updateByExampleSelective(updateObject, example);
    }

}
