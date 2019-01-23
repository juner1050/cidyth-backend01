package com.hyzs.cidyth.portal.controller.analysis;

import com.enterprisedt.util.debug.Logger;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hyzs.cidyth.common.enums.SystemExceptionEnum;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.analysis.dto.*;
import com.hyzs.cidyth.module.analysis.entity.AnalysisClue;
import com.hyzs.cidyth.module.analysis.service.AnalysisClueService;
import com.hyzs.cidyth.module.analysis.vo.AnalysisCaseDetailVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisCaseVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisClueVO;
import com.hyzs.cidyth.module.analysis.vo.AnalysisSuspectDetailVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"研判接口"})
@RestController
@RequestMapping("analysisClue")
public class AnalysisClueController {
	
    private static final Logger logger = Logger.getLogger(AnalysisClueController.class);

    @Autowired
    private AnalysisClueService analysisClueService;

    /**
     * 研判线索列表
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "研判线索列表", httpMethod = "GET", notes = "研判线索列表")
    public PageInfo<AnalysisClueVO> list(QueryAnalysisParam param, Page page){
        return analysisClueService.list(param, page);
    }

    /**
     * 新增研判线索
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    @PostMapping(value = "save")
    @ApiOperation(value = "新增研判线索", httpMethod = "POST", notes = "新增研判线索")
    public Map<String, Object> save(@Valid SaveAnalysisParam param){
        return analysisClueService.save(param);
    }

    /**
     * 下发
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "sendToUnit")
    @ApiOperation(value = "下发", httpMethod = "POST", notes = "下发")
    public void sendToUnit(@Valid @RequestBody SendDeptCodeParam sendDeptCodeParam){
        analysisClueService.sendToUnit(sendDeptCodeParam);
    }

    /**
     * 指派
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "allocatePersons")
    @ApiOperation(value = "指派", httpMethod = "POST", notes = "指派")
    public void allocatePersons(@Valid @RequestBody AllocateListParam allocateListParam){
        analysisClueService.allocatePersons(allocateListParam);
    }

    /**
     * 签收
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "sign")
    @ApiOperation(value = "签收", httpMethod = "POST", notes = "签收")
    public void sign(@RequestBody AnalysisClue analysisClue){
        if(StringUtils.isBlank(analysisClue.getXsbh())){
            throw new ServiceException(SystemExceptionEnum.NULL_DATA.name(), SystemExceptionEnum.NULL_DATA.descp());
        }
        analysisClueService.sign(analysisClue.getXsbh());
    }

    /**
     * 退回
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "retreat")
    @ApiOperation(value = "退回", httpMethod = "POST", notes = "退回")
    public void retreat(@RequestBody AnalysisClue analysisClue){
        if(StringUtils.isBlank(analysisClue.getXsbh())){
            throw new ServiceException(SystemExceptionEnum.NULL_DATA.name(), SystemExceptionEnum.NULL_DATA.descp());
        }
        analysisClueService.retreat(analysisClue.getXsbh());
    }

    /**
     * 获取研判编号
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    @GetMapping(value = "getCode")
    @ApiOperation(value = "获取研判编号", httpMethod = "GET", notes = "获取研判编号")
    public String getCode(){
        return analysisClueService.getCode();
    }

    /**
     * 获取研判产品的案件信息
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    @GetMapping(value = "listCaseInfo")
    @ApiOperation(value = "获取研判产品的案件信息", httpMethod = "GET", notes = "获取研判产品的案件信息")
    @ApiImplicitParams(
        @ApiImplicitParam(name="xsbh", value="线索编号", required=true, paramType="query")
    )
    public List<AnalysisCaseVO> listCaseInfo(String xsbh){
        List<AnalysisCaseVO> lsAnalysisCaseVO = Lists.newArrayList();
        if(StringUtils.isBlank(xsbh)){
            return lsAnalysisCaseVO;
        }
        lsAnalysisCaseVO = analysisClueService.listCaseInfo(xsbh);
        return lsAnalysisCaseVO;
    }

    /**
     * 上传案件图片
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    @PostMapping(value = "uploadCaseImage")
    @ApiOperation(value = "上传案件图片", httpMethod = "POST", notes = "上传案件图片")
    public void uploadCaseImage(@Valid SaveCaseImageParam param){
        analysisClueService.uploadCaseImage(param);
    }

    /**
     * 批量上传案件图片
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    @PostMapping(value = "uploadCaseImageBatch")
    @ApiOperation(value = "批量上传案件图片", httpMethod = "POST", notes = "批量上传案件图片")
    public void uploadCaseImageBatch(@Valid SaveCaseImageParamBatch param){
        analysisClueService.uploadCaseImage(param);
    }

    /**
     * 上传嫌疑人信息
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    /*@PostMapping(value = "uploadSuspectImage")
    @ApiOperation(value = "上传嫌疑人信息", httpMethod = "POST", notes = "上传嫌疑人信息")
    public void uploadSuspectImage(@Valid SaveSuspectParam param){
        analysisClueService.uploadSuspectImage(param);
    }*/

    /**
     * 批量上传嫌疑人信息
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    @PostMapping(value = "uploadSuspectImageBatch")
    @ApiOperation(value = "批量上传嫌疑人信息", httpMethod = "POST", notes = "批量上传嫌疑人信息")
    public void uploadSuspectImageBatch(@Valid SaveSuspectParamBatch param){
        analysisClueService.uploadSuspectImage(param);
    }

    /**
     * 根据线索编号获取所有案件的嫌疑人
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     */
    @GetMapping(value = "getSuspectByXsbh")
    @ApiOperation(value = "根据线索编号获取所有案件的嫌疑人", httpMethod = "GET", notes = "根据线索编号获取所有案件的嫌疑人")
    public List<AnalysisSuspectDetailVO> getSuspectByXsbh(String xsbh){
        if(StringUtils.isBlank(xsbh)){
            throw new ServiceException("研判编号不能为空");
        }
        return analysisClueService.getSuspectByXsbh(xsbh);
    }

    @GetMapping(value = "listCaseDetail")
    @ApiOperation(value = "获取串并案列表", httpMethod = "GET", notes = "获取串并案列表")
    @ApiImplicitParams(
        @ApiImplicitParam(name = "xsbh", value = "研判编号", required = true, paramType = "query")
    )
    public PageInfo<AnalysisCaseDetailVO> listCaseDetail(String xsbh, QueryCaseParam queryParam, Page page){
        if(StringUtils.isBlank(xsbh)){
            throw new ServiceException("研判编号不能为空");
        }
        return analysisClueService.listCaseDetail(xsbh, queryParam, page);
    }

    @GetMapping(value = "listSuspectDetail")
    @ApiOperation(value = "获取嫌疑人列表", httpMethod = "GET", notes = "获取嫌疑人列表")
    public PageInfo<AnalysisSuspectDetailVO> listSuspectDetail(String xsbh, Page page){
        if(StringUtils.isBlank(xsbh)){
            throw new ServiceException("研判编号不能为空");
        }
        return analysisClueService.listSuspectDetail(xsbh, page);
    }

}