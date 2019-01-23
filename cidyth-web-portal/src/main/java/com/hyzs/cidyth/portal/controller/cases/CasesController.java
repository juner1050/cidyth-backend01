package com.hyzs.cidyth.portal.controller.cases;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.enums.RuleTypeEnum;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.common.utils.FileUtil;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.base.dao.SceneImageCidMapper;
import com.hyzs.cidyth.module.base.dto.CasesAbortDTO;
import com.hyzs.cidyth.module.base.dto.CasesFinishDTO;
import com.hyzs.cidyth.module.base.entity.Cases;
import com.hyzs.cidyth.module.base.entity.SceneImageCid;
import com.hyzs.cidyth.module.base.service.TechnologyCompareCidService;
import com.hyzs.cidyth.module.base.vo.*;
import com.hyzs.cidyth.module.cases.service.PcCasesService;
import com.hyzs.cidyth.module.cases.vo.CasesBO;
import com.hyzs.cidyth.module.cases.vo.CasesRecordVO;
import com.hyzs.cidyth.module.cases.vo.SceneBO;
import com.hyzs.cidyth.module.integral.service.IntegralConfigService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags = {"案件接口"})
@RestController
@RequestMapping("cases")
public class CasesController {
    private static final Logger logger = LoggerFactory.getLogger(CasesController.class);

    @Autowired
    private PcCasesService casesService;
    @Autowired
    private TechnologyCompareCidService technologyCompareCidService;
    @Autowired
    private SceneImageCidMapper sceneImageCidMapper;
    @Autowired
    private IntegralConfigService integralConfigService;

    /**
     * 案件列表
     *
     * @param casesVO 案件视图对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "案件列表", httpMethod = "GET", response = CasesVO.class, notes = "案件列表")
    public PageInfo<CasesVO> list(CasesVO casesVO, Page page) {
        return casesService.listCaseLocal(casesVO, page);
    }

    /**
     * 提取警综案件（串并案接口）
     *
     * @param casesVO 案件视图对象
     * @param page    分页对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "listPickCases")
    @ApiOperation(value = "提取警综案件（串并案接口）", httpMethod = "GET", response = CasesVO.class, notes = "提取警综案件（串并案接口）")
    public PageInfo<CasesVO> listPickCases(CasesVO casesVO, Page page) {
        return casesService.listPickCases(casesVO, page);
    }

    /**
     * 提取警综案件（提取案件接口，数据只限本部门或所有下级部门）
     *
     * @param casesVO 案件视图对象
     * @param page    分页对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "listPickDeptCases")
    @ApiOperation(value = "提取警综案件（提取案件接口，数据只限本部门或所有下级部门）", httpMethod = "GET", response = CasesVO.class, notes = "提取警综案件（提取案件接口，数据只限本部门或所有下级部门）")
    public PageInfo<CasesVO> listPickDeptCases(CasesVO casesVO, Page page) {
        return casesService.listPickDeptCases(casesVO, page);
    }

    /**
     * 案件列表
     *
     * @param casesVO 案件视图对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     */
    @GetMapping(value = "listPickLocal")
    @ApiOperation(value = "提取本地案件", httpMethod = "GET", response = CasesVO.class, notes = "提取本地案件")
    public PageInfo<CasesVO> listPickLocal(CasesVO casesVO, Page page) {
        return casesService.listCaseLocalAll(casesVO, page);
    }

    /**
     * 提取案件
     *
     * @param ajbhs 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     */
    @GetMapping(value = "remotePickCase")
    @ApiOperation(value = "提取案件", httpMethod = "GET", response = CasesVO.class, notes = "提取案件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ajbhs", value = "案件编号（多个编号逗号分隔）", required = true, dataType = "String", paramType = "query")
    })
    public Map<String, Object> remotePickCase(String ajbhs) {
        Map<String, Object> map = new HashMap<>();
        Cases cases = casesService.getCaseByAjbh(ajbhs);
        if (cases == null) {
            map.put("ajbh", null);
        } else {
            map.put("ajbh", cases.getAjbh());
        }
        map.put("score", integralConfigService.getScoreByRuleType(RuleTypeEnum.CASES_PICK.name()));
        return map;
    }

    /**
     * 获取[侦办中数量]、[已侦结数量]、[挂起数量]
     *
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-19 19:05:56
     */
    @GetMapping(value = "countGroup")
    @ApiOperation(value = "获取[侦办中数量]、[已侦结数量]、[挂起数量]", httpMethod = "GET", response = CasesVO.class, notes = "获取[侦办中数量]、[已侦结数量]、[挂起数量]")
    public Map<String, String> countGroup() {
        return casesService.countGroupByBdajstate();
    }

    /**
     * 案件详情
     *
     * @param ajbh 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "detail")
    @ApiOperation(value = "案件详情", httpMethod = "GET", response = CasesBO.class, notes = "案件详情")
    public CasesBO detail(@RequestParam String ajbh) {
        return casesService.detail(ajbh);
    }

    /**
     * 获取详情数据
     *
     * @param ajbh 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.SceneBO>
     * @author 陈海胜
     * @date 2018-05-10 16:30:07
     */
    @GetMapping(value = "getSurveyedSceneInfo")
    @ApiOperation(value = "现场勘查信息", httpMethod = "GET", response = SceneBO.class, notes = "获取案件的现场勘查信息(指纹，DNA,足迹等)")
    public List<SceneBO> getSurveyedSceneInfo(
            @ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required = true) String ajbh) {
        return casesService.getSurveyedSceneInfo(ajbh);
    }

    /**
     * 案件新增
     *
     * @param casesVO 案件对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "案件新增", httpMethod = "POST", response = CasesVO.class, notes = "案件新增")
    public Map<String, Object> insert(@RequestBody CasesVO casesVO) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", casesService.insert(casesVO));
        map.put("score", integralConfigService.getScoreByRuleType(RuleTypeEnum.CASES_CREATE.name()));
        return map;
    }

    /**
     * 案件新增的数据源
     *
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "initData")
    @ApiOperation(value = "案件新增的数据源", httpMethod = "GET", response = CasesVO.class, notes = "案件新增的数据源")
    public Map<String, Object> initData() {
        return casesService.initData();
    }

    /**
     * 案件侦结
     *
     * @param casesFinishVO
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @PostMapping(value = "finish")
    @ApiOperation(value = "案件侦结", httpMethod = "POST", response = CasesVO.class, notes = "案件侦结")
    public Map<String, Object> finish(@RequestBody CasesFinishDTO casesFinishDTO) {
        Map<String, Object> map = new HashMap<>();
        casesService.casesFinish(casesFinishDTO);
        map.put("id", casesFinishDTO.getAjbh());
        map.put("score", integralConfigService.getScoreByRuleType(RuleTypeEnum.CASES_FINISH.name()));
        return map;
    }

    /**
     * 案件挂起
     *
     * @param ajbh 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @PostMapping(value = "abort")
    @ApiOperation(value = "案件挂起", httpMethod = "POST", response = CasesVO.class, notes = "案件挂起")
    public Map<String, Object> abort(@RequestBody CasesAbortDTO casesAbortDTO) {
        Map<String, Object> map = new HashMap<>();
        casesService.casesAbort(casesAbortDTO);
        map.put("id", casesAbortDTO.getAjbh());
        return map;
    }

    /**
     * 案件审核
     *
     * @param ajbh        案件编号
     * @param checkStatus 审核状态
     * @param checkResult 审核结果
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "check")
    @ApiOperation(value = "案件审核", httpMethod = "GET", response = CasesVO.class, notes = "案件审核")
    public Map<String, Object> check(@RequestParam String ajbh, @RequestParam Integer checkStatus, @RequestParam String checkResult) {
        Map<String, Object> map = new HashMap<>();
        casesService.casesCheck(ajbh, checkStatus, checkResult);
        map.put("id", ajbh);
        return map;
    }

    /**
     * 案件串并
     *
     * @param casesMergeVO 串并案对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @PostMapping(value = "series")
    @ApiOperation(value = "案件串并", httpMethod = "POST", response = CasesVO.class, notes = "案件串并")
    public Map<String, Object> series(@RequestBody CasesMergeVO casesMergeVO) {
        Map<String, Object> map = new HashMap<>();
        casesService.seriesAjByAjbh(casesMergeVO);
        map.put("id", casesMergeVO.getoAjbh());
        map.put("score", integralConfigService.getScoreByRuleType(RuleTypeEnum.CASES_MERGE.name()));
        return map;
    }

    /**
     * 案件导出
     *
     * @param casesVO 案件视图对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "excelExport")
    @ApiOperation(value = "案件导出", httpMethod = "GET", response = CasesVO.class, notes = "案件导出")
    public void excelExport(CasesVO casesVO) {
        casesService.excelExport(casesVO);
    }

    /**
     * 比中信息列表
     *
     * @param param
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "compareInfo")
    @ApiOperation(value = "比中信息列表", httpMethod = "GET", response = CasesVO.class, notes = "比中信息列表")
    public PageInfo<Map<String, Object>> loadCompareInfo(CompareInfoLoadParam param, Page page) {
        return technologyCompareCidService.loadCompareInfo(param, page);
    }

    /**
     * 案件比中信息（刑技库）
     *
     * @param ajbh
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "caseCompareInfo")
    @ApiOperation(value = "案件的比中信息", httpMethod = "GET", response = Map.class, notes = "案件的比中信息")
    public PageInfo<Map<String, Object>> loadCaseCompareInfo(String ajbh, Page page) {
        return technologyCompareCidService.loadCompareInfoByAjbh(ajbh, page);

    }

    /**
     * 案件合成作战小组
     *
     * @param ajbh 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "memberGroup")
    @ApiOperation(value = "合成作战小组", httpMethod = "GET", response = CasesVO.class, notes = "合成作战小组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ajbh", value = "案件编号", required = true, paramType = "query")
    })
    public List<Map<String, Object>> memberGroup(String ajbh) {
        return casesService.memberGroup(ajbh);
    }

    /**
     * 现场勘查图片
     * @param req
     * @param res
     * @param xxid
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
                InputStream is = new ByteArrayInputStream(sceneImageCid.getContent());
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
     * 案件新增的数据源
     *
     * @param id 案件对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "sceneImageTest")
    @ApiOperation(value = "现场照片", httpMethod = "GET", response = SceneImageCid.class, notes = "现场照片")
    public void sceneImageTest(HttpServletRequest req, HttpServletResponse res, String id) {
        try {
            ServletOutputStream os = res.getOutputStream();
            InputStream in=new FileInputStream(ContextUserHelper.APPLICATION_ROOT_REAL_PATH+"\\test.png");
            int len=0;
            byte[] buffer=new byte[1024];
            while((len=in.read(buffer))>0) {
                os.write(buffer,0, len);
            }
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*if (StringUtils.isNotEmpty(id)) {
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
        }*/
    }

    /**
     * 手动提取笔录（考拉系统）
     *
     * @param casesRecordKLParam 案件编号、人员编号、身份证号集合
     * @return 返回成功提取的笔录
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @PostMapping(value = "listRecordKL")
    @ApiOperation(value = "手动提取笔录", httpMethod = "POST", response = CasesRecordVO.class, notes = "手动提取笔录")
    public List<CasesRecordVO> listRecordKL(@RequestBody List<CasesRecordKLParam> casesRecordKLParam) {
        return casesService.listCasesRecordKLParam(casesRecordKLParam);
    }

    /**
     * 批量提取案件
     *
     * @param file Excel文件
     * @return 返回成功提取的笔录
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @PostMapping(value = "batchPick")
    @ApiOperation(value = "批量提取案件", httpMethod = "POST", response = Map.class, notes = "批量提取案件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "Excel文件", required = true, paramType = "form", dataType = "file")
    })
    public void batchPick(MultipartFile file) {
        casesService.batchPick(file);
    }

    /**
     * 下载模板
     *
     * @return 返回成功提取的笔录
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "batchTemplate")
    @ApiOperation(value = "下载模板", httpMethod = "GET", response = Map.class, notes = "下载模板")
    public ResponseEntity<byte[]> batchTemplate() {
        try {
            return FileUtil.download("template/批量提取案件.xls");
        } catch (Exception e) {
            throw new ServiceException("下载失败");
        }
    }

    /**
     * 本月合成作战案件情况
     *
     * @param page 分页对象
     * @return 返回成功提取的笔录
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    /*@GetMapping(value = "listCasesByMonth")
    @ApiOperation(value = "本月合成作战案件情况", httpMethod = "GET", response = Map.class, notes = "本月合成作战案件情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页对象", required = true, paramType = "body")
    })
    public PageInfo<PersonalCasesVO> listCasesByMonth(Page page) {
        return casesService.listCasesByMonth(page);
    }
    */

    /**
     * 本月合成作战案件情况
     *
     * @param page 分页对象
     * @return 返回成功提取的笔录
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "listCasesPartner")
    @ApiOperation(value = "本月合成作战案件情况", httpMethod = "GET", response = Map.class, notes = "本月合成作战案件情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "分页对象", required = true, paramType = "body"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", required = true, paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, paramType = "query")
    })
    public PageInfo<PersonalCasesVO> listCasesPartner(Page page, String beginTime, String endTime) {
        return casesService.listCasesPartner(page, beginTime, endTime);
    }

    /**
     * 个人中心-破案统计
     *
     * @return
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "finishTotal")
    @ApiOperation(value = "个人中心-破案统计", httpMethod = "GET", response = Map.class, notes = "个人中心-破案统计")
    public Map<String, Object> finishTotal() {
        return casesService.finishTotal();
    }

    /**
     * 添加协办人
     *
     * @return
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @PostMapping(value = "addCoordinator")
    @ApiOperation(value = "添加协办人", httpMethod = "POST", response = Map.class, notes = "添加协办人")
    public Map<String, Object> addCoordinator(@RequestBody CasesVO casesVO) {
        return casesService.addCoordinator(casesVO);
    }

    /**
     * 是否是合成小组的人员
     *
     * @return
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "isMemberGroup")
    @ApiOperation(value = "是否是合成小组的人员", httpMethod = "GET", response = Map.class, notes = "是否是合成小组的人员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ajbh", value = "案件编号", required = true, paramType = "query"),
            @ApiImplicitParam(name = "account", value = "警号", required = true, paramType = "query")
    })
    public Map<String, Object> isMemberGroup(String ajbh, String account) {
        return casesService.isMemberGroup(ajbh, account);
    }

    /**
     * 是否为当前人提取的案件
     *
     * @return
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "isMyPick")
    @ApiOperation(value = "是否为当前人提取的案件", httpMethod = "GET", response = Map.class, notes = "是否为当前人提取的案件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ajbh", value = "案件编号", required = true, paramType = "query")
    })
    public Map<String, Object> isMyPick(String ajbh) {
        return casesService.isMyPick(ajbh);
    }

    /**
     * 是否允许查看串并案
     * @return
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     */
    @GetMapping(value = "allowReadSeries")
    @ApiOperation(value = "是否允许查看串并案", httpMethod = "GET", response = Map.class, notes = "是否允许查看串并案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ajbh", value = "案件编号", required = true, paramType = "query")
    })
    public Map<String, Object> allowReadSeries(String ajbh) {
        return casesService.allowReadSeries(ajbh);
    }

    @GetMapping(value = "listXK")
    @ApiOperation(value = "获取统计对比现勘数据", httpMethod = "GET", response = Map.class, notes = "获取统计对比现勘数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kssj", value = "开始时间", required = true, paramType = "query"),
            @ApiImplicitParam(name = "jssj", value = "结束时间", required = true, paramType = "query")
    })
    public PageInfo<Map<String, Object>> listXK(String kssj, String jssj, Page page) {
        return casesService.listXK(kssj, jssj, page);
    }

    @GetMapping(value = "extractCasesOtherInfo")
    @ApiOperation(value = "启动同步数据", httpMethod = "GET", response = Map.class, notes = "启动同步数据")
    public String extractCasesOtherInfo() {
        casesService.extractCasesOtherInfo();
        return "开始运行...";
    }

    @GetMapping(value = "ajbhExtractCasesOtherInfo")
    @ApiOperation(value = "启动同步数据", httpMethod = "GET", response = Map.class, notes = "启动同步数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ajbh", value = "案件编号", required = true, paramType = "query")
    })
    public String ajbhExtractCasesOtherInfo(String ajbh) {
        if(StringUtils.isBlank(ajbh)){
            return "案件编号为空";
        }
        casesService.ajbhExtractCasesOtherInfo(ajbh);
        return "开始运行...";
    }

    @GetMapping(value = "getMergeCase")
    @ApiOperation(value = "获取案件的串并案", httpMethod = "GET", response = Map.class, notes = "获取案件的串并案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ajbh", value = "案件编号", required = true, paramType = "query")
    })
    public List<Map<String, String>> getMergeCase(String ajbh) {
        if(StringUtils.isBlank(ajbh)){
            throw new ServiceException("案件编号为空");
        }
        return casesService.listMergeCaseInfoByAjbh(ajbh);
    }
}
