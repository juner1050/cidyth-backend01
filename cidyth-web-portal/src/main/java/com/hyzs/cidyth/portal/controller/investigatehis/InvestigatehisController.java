package com.hyzs.cidyth.portal.controller.investigatehis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hyzs.cidyth.module.investigatehis.service.InvestigateHisService;
import com.hyzs.cidyth.module.investigatehis.vo.InvestigateHistory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "侦查记录接口" })
@RestController
@RequestMapping("investigatehis")
public class InvestigatehisController {
	@Autowired
	private InvestigateHisService investigateHisService;

	@PostMapping(value = "save")
	@ApiOperation(value = "保存侦查记录", httpMethod = "POST", response = Void.class, notes = "保存侦查记录")
	public void saveInvestigate( @RequestBody InvestigateHistory history) {
		investigateHisService.saveInvestigateHistory(history);
	}

	@GetMapping(value = "remarkList")
	@ApiOperation(value = "查询备注信息", httpMethod = "GET", response = List.class, notes = "查询备注信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "caseNo", value = "案件编号", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "suspectId", value = "嫌疑人身份标识(身份证)", required = true, dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "suspectInfoType", value = "嫌疑人信息分类", required = true, dataType = "string", paramType = "query") })
	public List loadRemarkList(@RequestParam String caseNo, @RequestParam String suspectId,
			@RequestParam String suspectInfoType) {
		return investigateHisService.loadRemarkList(caseNo, suspectId, suspectInfoType);
	}
}
