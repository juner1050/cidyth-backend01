package com.hyzs.cidyth.app.controller.demand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.hyzs.cidyth.common.enums.DemandFlowStepStatus;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.demand.entity.DemandFlowHis;
import com.hyzs.cidyth.module.demand.service.DemandFlowService;
import com.hyzs.cidyth.module.demand.service.DemandService;
import com.hyzs.cidyth.module.demand.vo.DemandBatchVO;
import com.hyzs.cidyth.module.demand.vo.DemandFlowHisVO;
import com.hyzs.cidyth.module.demand.vo.DemandVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags = { "需求接口" })
@RestController
@RequestMapping("demand")
public class DemandController {

	private static final Logger logger = LoggerFactory.getLogger(DemandController.class);

	@Autowired
	private DemandService demandService;
	@Autowired
	private DemandFlowService demandFlowService;

	@PostMapping(value = "insert")
	@ApiOperation(value = "新增需求", httpMethod = "POST", response = Map.class, notes = "新增需求")
	public Map<String, Object> insert(DemandBatchVO demandBatchVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", demandService.insert(demandBatchVO));
		return map;
	}

	@PostMapping(value = "insertOld")
	@ApiOperation(value = "新增需求", httpMethod = "POST", response = DemandVO.class, notes = "新增需求")
	public Map<String, Object> insertOld(DemandVO demandVO) {
		return demandService.insert(demandVO);
	}

	@PostMapping(value = "allocate")
	@ApiOperation(value = "需求指派", httpMethod = "POST", response = DemandVO.class, notes = "需求指派")
	public Map<String, Object> allocate(@RequestBody DemandFlowHisVO demandFlowHisVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		demandFlowService.insert(demandFlowHisVO);
		map.put("id", demandFlowHisVO.getXqid());
		return map;
	}

	@PostMapping(value = "sign")
	@ApiOperation(value = "需求签收", httpMethod = "POST", response = DemandVO.class, notes = "需求签收")
	public Map<String, Object> sign(@RequestBody DemandFlowHisVO demandFlowHisVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		demandFlowService.sign(demandFlowHisVO);
		map.put("id", demandFlowHisVO.getXqid());
		return map;
	}

	@PostMapping(value = "retreat")
	@ApiOperation(value = "需求退回", httpMethod = "POST", response = DemandFlowHisVO.class, notes = "需求退回")
	public Map<String, Object> retreat(@RequestBody DemandFlowHisVO demandFlowHisVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		demandFlowService.retreat(demandFlowHisVO);
		map.put("id", demandFlowHisVO.getId());
		return map;
	}

	@PostMapping(value = "delayApply")
	@ApiOperation(value = "需求延期申请", httpMethod = "POST", response = DemandFlowHisVO.class, notes = "需求延期申请")
	public Map<String, Object> delayApply(@RequestBody DemandFlowHisVO demandFlowHisVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		demandFlowService.delayApply(demandFlowHisVO);
		map.put("id", demandFlowHisVO.getId());
		return map;
	}

	/**
	 * 需求列表
	 * 
	 * @param demandVO
	 *            需求视图对象
	 * @return java.util.List<com.hyzs.cidyth.module.demand.vo.DemandVO>
	 */
	@GetMapping(value = "list")
	@ApiOperation(value = "获取需求列表", httpMethod = "GET", response = DemandVO.class, notes = "获取需求列表")
	public PageInfo<DemandVO> list(Page page) {
		return demandService.list(null, page);
	}

	/**
	 * 获取需求的待签收人/回退操作人
	 * 
	 * @param xqid
	 *            需求id
	 * @return
	 */
	@GetMapping(value = "signers")
	@ApiOperation(value = "获取需求的待签收人/回退操作人", httpMethod = "GET", response = DemandVO.class, notes = "获取需求的待签收人/回退操作人")
	public Map<String, String> signers(
			@ApiParam(required = true, name = "xqid", value = "需求id") @RequestParam(value = "xqid", required = true) String xqid) {
		Map<String, String> result = new HashMap<String, String>() {
			{
				put(DemandFlowStepStatus.WAIT_FOR_SIGN.name(), "");
				put(DemandFlowStepStatus.RETREAT.name(), "");
			}
		};
		if (StringUtils.isNotBlank(xqid) && StringUtils.isNumeric(xqid)) {
			List<DemandFlowHis> hists = demandFlowService.getDemandFlowHis(Integer.parseInt(xqid),
					new DemandFlowStepStatus[] { DemandFlowStepStatus.WAIT_FOR_SIGN, DemandFlowStepStatus.RETREAT });
			if (CollectionUtils.isNotEmpty(hists)) {
				for (DemandFlowHis h : hists) {
					result.put(h.getQszt(), h.getJsrymc());
				}
			}
		}
		return result;
	}
}
