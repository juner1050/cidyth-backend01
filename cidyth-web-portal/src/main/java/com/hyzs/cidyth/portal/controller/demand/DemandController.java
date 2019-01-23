package com.hyzs.cidyth.portal.controller.demand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.demand.entity.Demand;
import com.hyzs.cidyth.module.demand.service.DemandFlowService;
import com.hyzs.cidyth.module.demand.service.DemandService;
import com.hyzs.cidyth.module.demand.vo.DemandBatchVO;
import com.hyzs.cidyth.module.demand.vo.DemandFlowHisVO;
import com.hyzs.cidyth.module.demand.vo.DemandVO;
import com.hyzs.cidyth.module.interaction.service.InteractionService;
import com.hyzs.cidyth.module.interaction.vo.AbstractDemandDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"需求接口"})
@RestController
@RequestMapping("demand")
public class DemandController {
	
	private static final Logger logger = LoggerFactory.getLogger(DemandController.class);

	@Autowired
	private DemandService demandService;
	@Autowired
	private DemandFlowService demandFlowService;
	
	@Autowired
	private InteractionService interactionService;

    /**
     * 我发起的需求
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "我发起的需求", httpMethod = "GET", response = DemandVO.class, notes = "我发起的需求")
    public PageInfo<DemandVO> list(DemandVO demandVO, Page page){
        return demandService.list(demandVO, page);
    }

    /**
     * 我待办的需求
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @GetMapping(value = "waitHandle")
    @ApiOperation(value = "我待办的需求", httpMethod = "GET", response = DemandVO.class, notes = "我待办的需求")
    public PageInfo<DemandVO> waitHandle(DemandVO demandVO, Page page){
        return demandService.waitHandle(demandVO, page);
    }

    /**
     * 需求新增
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandBatchVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "新增需求", httpMethod = "POST", response = Map.class, notes = "新增需求")
    public Map<String, Object> insert(DemandBatchVO demandBatchVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", demandService.insert(demandBatchVO));
        return map;
    }

    /**
     * 需求新增
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param demandVO 需求视图对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "insertOld")
    @ApiOperation(value = "新增需求", httpMethod = "POST", response = DemandVO.class, notes = "新增需求")
    public Map<String, Object> insertOld(DemandVO demandVO){
        return demandService.insert(demandVO);
    }

    /**
     * 需求详情
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param id 需求id
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @GetMapping(value = "detail")
    @ApiOperation(value = "需求详情", httpMethod = "GET", response = DemandVO.class, notes = "需求详情")
    public DemandVO detail(@RequestParam Integer id){
        return demandService.detail(id);
    }

    /**
     * 需求指派
     * @author 陈铭
     * @date 2018-04-25 17:01:16
     * @param demandFlowHisVO
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "allocate")
    @ApiOperation(value = "需求指派", httpMethod = "POST", response = DemandVO.class, notes = "需求指派")
    public Map<String, Object> allocate(@RequestBody DemandFlowHisVO demandFlowHisVO){
    	Map<String, Object> map = new HashMap<String, Object>();
    	demandFlowService.insert(demandFlowHisVO);
    	map.put("id", demandFlowHisVO.getXqid());
        return map;
    }

    /**
     * 需求签收
     * @author 陈铭
     * @date 2018-04-25 17:01:16
     * @param demandVO 需求对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "sign")
    @ApiOperation(value = "需求签收", httpMethod = "POST", response = DemandVO.class, notes = "需求签收")
    public Map<String, Object> sign(@RequestBody DemandFlowHisVO demandFlowHisVO){
        Map<String, Object> map = new HashMap<String, Object>();
        demandFlowService.sign(demandFlowHisVO);
        map.put("id", demandFlowHisVO.getXqid());
        return map;
    }

    /**
     * 需求退回
     * @author 陈铭
     * @date 2018-04-25 17:01:16
     * @param demandFlowHisVO 需求对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "retreat")
    @ApiOperation(value = "需求退回", httpMethod = "POST", response = DemandFlowHisVO.class, notes = "需求退回")
    public Map<String, Object> retreat(@RequestBody DemandFlowHisVO demandFlowHisVO){
        Map<String, Object> map = new HashMap<String, Object>();
        demandFlowService.retreat(demandFlowHisVO);
        map.put("id", demandFlowHisVO.getId());
        return map;
    }

    /**
     * 需求延期申请
     * @author 陈铭
     * @date 2018-04-25 17:01:16
     * @param demandFlowHisVO 需求对象
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "delayApply")
    @ApiOperation(value = "需求延期申请", httpMethod = "POST", response = DemandFlowHisVO.class, notes = "需求延期申请")
    public Map<String, Object> delayApply(@RequestBody DemandFlowHisVO demandFlowHisVO){
        Map<String, Object> map = new HashMap<String, Object>();
        demandFlowService.delayApply(demandFlowHisVO);
        map.put("id", demandFlowHisVO.getId());
        return map;
    }

    /**
     * 需求催办
     * @author 陈铭
     * @date 2018-04-25 17:01:16
     * @param demandVO 需求
     * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
     */
    @PostMapping(value = "urge")
    @ApiOperation(value = "需求催办", httpMethod = "POST", response = DemandVO.class, notes = "需求催办")
    public Map<String, Object> urge(@RequestBody DemandVO demandVO){
        Map<String, Object> map = new HashMap<String, Object>();
        // TODO 需求不明确，待定...
        /*demandFlowService.sign(demandVO.getId());
        map.put("id", demandVO.getId());*/
        return map;
    }

	@GetMapping(value = "getDemandByCase")
	@ApiOperation(value = "根据案件获取需求详情列表(包含线索已经所有的回复)", httpMethod = "GET", response = List.class, notes = "根据案件获取需求详情列表(包含线索已经所有的回复)")
	public List<AbstractDemandDetail> getDemandByCase(
			@ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required=true) String ajbh) {
		List<AbstractDemandDetail> result = interactionService.loadInteractionContentList(ajbh, true);
		if(logger.isDebugEnabled()&&CollectionUtils.isNotEmpty(result)){
			logger.debug(result.get(0).getAjbh());
		}
		return result;
	}

    @PostMapping(value = "checkDemandInfoExists")
    @ApiOperation(value = "判断需求的类型、内容是否存在", httpMethod = "POST", response = List.class, notes = "判断需求的类型、内容是否存在")
    public List<Demand> checkDemandInfoExists(@RequestBody List<Demand> lsDemand) {
        List<Demand> result = demandService.checkDemandInfoExists(lsDemand);
        return result;
    }

}
