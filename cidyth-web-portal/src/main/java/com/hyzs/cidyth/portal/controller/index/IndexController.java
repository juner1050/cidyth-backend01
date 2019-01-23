package com.hyzs.cidyth.portal.controller.index;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.cases.service.PcCasesService;
import com.hyzs.cidyth.module.demand.service.DemandFlowService;
import com.hyzs.cidyth.module.demand.service.DemandService;
import com.hyzs.cidyth.module.demand.vo.DemandIndexVO;
import com.hyzs.cidyth.module.demand.vo.DemandVO;
import com.hyzs.cidyth.module.integral.dao.IntegralTitleMapper;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.integral.vo.IntegralHisRank;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2018/9/20.
 */
@Api(tags = {"首页接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private PcCasesService casesService;
    @Autowired
    private DemandService demandService;
    @Autowired
    private DemandFlowService demandFlowService;
    @Autowired
    private IntegralHisService integralHisService;
    @Autowired
    private IntegralTitleMapper integralTitleMapper;

    /**
     * 首页：分局侦查情况
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @return
     */
    @GetMapping(value = "branchInvestigate")
    @ApiOperation(value = "首页：分局侦查情况", httpMethod = "GET", response = Map.class, notes = "首页：分局侦查情况")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kssj", value = "开始时间", required = true, paramType = "query"),
            @ApiImplicitParam(name = "jssj", value = "结束时间", required = true, paramType = "query")
    })
    public List<Map<String, Object>> branchInvestigate(String kssj, String jssj) {
        return casesService.branchInvestigate(kssj, jssj);
    }

    /**
     * 首页：超期反馈
     * @return
     */
    @GetMapping(value = "overdueFeedback")
    @ApiOperation(value = "首页：超期反馈", httpMethod = "GET", response = Map.class, notes = "首页：超期反馈")
    public List<Map<String, Object>> overdueFeedback() {
        return demandFlowService.overdueFeedback();
    }

    /**
     * 首页：超期反馈详情
     * @return
     */
    @GetMapping(value = "overdueFeedbackDetail")
    @ApiOperation(value = "首页：超期反馈详情", httpMethod = "GET", response = Map.class, notes = "首页：超期反馈详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prefixCode", value = "code前缀", required = true, paramType = "query")
    })
    public PageInfo<Map<String, Object>> overdueFeedbackDetail(String prefixCode, Page page) {
        return demandFlowService.overdueFeedbackDetail(prefixCode, page);
    }

    /**
     * 首页：待办事项
     * @return
     */
    @GetMapping(value = "waitHandler")
    @ApiOperation(value = "首页：待办事项", httpMethod = "GET", response = Map.class, notes = "首页：待办事项")
    public Map<String, Object> waitHandler() {
        return demandService.waitHandler();
    }

    /**
     * 首页：待办事项详情（待指派）
     * @param qszt
     * @param page
     * @return
     */
    @GetMapping(value = "waitAllocate")
    @ApiOperation(value = "首页：待办事项详情（待指派）", httpMethod = "GET", response = Map.class, notes = "首页：待办事项详情（待指派）")
    public PageInfo<DemandIndexVO> waitAllocate(Page page) {
        return demandService.waitAllocate(page);
    }

    /**
     * 首页：待办事项详情（待签收）
     * @param qszt
     * @param page
     * @return
     */
    @GetMapping(value = "waitSign")
    @ApiOperation(value = "首页：待办事项详情（待签收）", httpMethod = "GET", response = Map.class, notes = "首页：待办事项详情（待签收）")
    public	PageInfo<DemandIndexVO> waitSign(Page page) {
        return demandFlowService.waitSign(page);
    }

    /**
     * 首页：待办事项详情（待反馈）
     * @param qszt
     * @param page
     * @return
     */
    @GetMapping(value = "waitFeedBack")
    @ApiOperation(value = "首页：待办事项详情（待反馈）", httpMethod = "GET", response = Map.class, notes = "首页：待办事项详情（待反馈）")
    public	PageInfo<DemandIndexVO> waitFeedBack(Page page) {
        return demandFlowService.waitFeedBack(page);
    }

    /**
     * 首页：需求-线索
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @return
     */
    @GetMapping(value = "demandClue")
    @ApiOperation(value = "首页：需求-线索", httpMethod = "GET", response = Map.class, notes = "首页：需求-线索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kssj", value = "开始时间", required = true, paramType = "query"),
            @ApiImplicitParam(name = "jssj", value = "结束时间", required = true, paramType = "query")
    })
    public Map<String, Object> demandClue(String kssj, String jssj) {
        return demandService.demandClue(kssj, jssj);
    }

    /**
     * 首页：需求-线索详情
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @return
     */
    @GetMapping(value = "demandClueDetail")
    @ApiOperation(value = "首页：需求-线索详情", httpMethod = "GET", response = Map.class, notes = "首页：需求-线索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "kssj", value = "开始时间", required = true, paramType = "query"),
            @ApiImplicitParam(name = "jssj", value = "结束时间", required = true, paramType = "query"),
            @ApiImplicitParam(name = "policeType", value = "警种类别", required = true, paramType = "query"),
            @ApiImplicitParam(name = "page", value = "结束时间", required = true, paramType = "body")
    })
    public PageInfo<DemandVO> demandClueDetail(String kssj, String jssj, String policeType, Page page) {
        return demandService.demandClueDetail(kssj, jssj, policeType, page);
    }

    @GetMapping(value = "honorWall")
    @ApiOperation(value = "首页：荣誉墙", httpMethod = "GET", response = Map.class, notes = "首页：荣誉墙")
    public List<IntegralHisRank> test() {
        return integralHisService.honorWall();
    }

}
