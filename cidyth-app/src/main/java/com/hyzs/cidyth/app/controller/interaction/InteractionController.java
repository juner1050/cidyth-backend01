package com.hyzs.cidyth.app.controller.interaction;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyzs.cidyth.module.interaction.service.InteractionService;
import com.hyzs.cidyth.module.interaction.vo.AbstractDemandDetail;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = { "融合作战接口" })
@RestController
@RequestMapping("interaction")
public class InteractionController {
	private static final Logger logger = LoggerFactory.getLogger(InteractionController.class);

	@Autowired
	private InteractionService interactionService;// 互动服务

	@GetMapping(value = "getInteractionListByCase")
	@ApiOperation(value = "根据案件获取需求/信息", httpMethod = "GET", response = List.class, notes = "根据案件获取需求/信息")
	public List<AbstractDemandDetail> getInteractionListByCase(
			@ApiParam(required = true, name = "ajbh", value = "案件编号") @RequestParam(required = true) String ajbh) {
		List<AbstractDemandDetail> result = interactionService.loadInteractionContentList(ajbh, true);
		if (logger.isDebugEnabled() && CollectionUtils.isNotEmpty(result)) {
			logger.debug(result.get(0).getAjbh());
		}
		return result;
	}

	@GetMapping(value = "getInteractionItem")
	@ApiOperation(value = "查询单个信息/需求及其相关的互动内容", httpMethod = "GET", response = List.class, notes = "查询单个信息/需求及其相关的互动内容")
	public AbstractDemandDetail getInteractionItem(
			@ApiParam(required = true, name = "referenceId", value = "目标id") @RequestParam("referenceId") String referenceId,
			@ApiParam(required = true, name = "type", value = "目标类型") @RequestParam("type") String type) {
		return interactionService.loadInteractionContentItem(referenceId, type);
	}
}
