package com.hyzs.cidyth.portal.controller.appraise;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyzs.cidyth.module.appraise.service.AppraiseService;
import com.hyzs.cidyth.module.appraise.vo.Appraise;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "评价接口" })
@RestController
@RequestMapping("appraise")
public class AppraiseController {

	@Autowired
	@Qualifier("clueAppraiseService")
	private AppraiseService clueAppraiseService;

	/**
	 * 保存线索评价
	 * 
	 * @author jidw
	 * @date 2018-07-19 16:05
	 * @param clueAppraise 线索评价 
	 *            
	 */

	@PostMapping(value = "clue")
	@ApiOperation(value = "评价线索", httpMethod = "POST", response = Map.class, notes = "评价线索")
	public void saveClueAppraise(@RequestBody Appraise clueAppraise) {
		clueAppraiseService.saveAppraise(clueAppraise);
	}

}
