package com.hyzs.cidyth.app.controller.cases;

import com.hyzs.cidyth.module.base.vo.PersonalCasesVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.cases.service.AppCaseService;
import com.hyzs.cidyth.module.cases.service.PcCasesService;
import com.hyzs.cidyth.module.cases.vo.AppCaseList;
import com.hyzs.cidyth.module.cases.vo.CasesBO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Map;

@Api(tags = { "案件接口" })
@RestController
@RequestMapping("cases")
public class CaseController {
	private static final Logger logger = LoggerFactory.getLogger(CaseController.class);

	@Autowired
	private AppCaseService appCaseService;
    @Autowired
    private PcCasesService pcCasesService;
    
	@GetMapping(value = "list")
	@ApiOperation(value = "案件列表", httpMethod = "GET", response = AppCaseList.class, notes = "案件列表")
	public PageInfo<AppCaseList> list(Page page) {
		return appCaseService.pageCaseList(page);
	}

	/**
	 * 本月合成作战案件情况
	 * @author 陈铭
	 * @date 2018-04-20 14:08:03
	 * @param page 分页对象
	 * @return 返回成功提取的笔录
	 */
	@GetMapping(value = "listCasesPartner")
	@ApiOperation(value = "本月合成作战案件情况", httpMethod = "GET", response = Map.class, notes = "本月合成作战案件情况")
	@ApiImplicitParams({
			@ApiImplicitParam(name="page", value="分页对象", required=true, paramType="body"),
			@ApiImplicitParam(name="beginTime", value="开始时间", required=true, paramType="query"),
			@ApiImplicitParam(name="endTime", value="结束时间", required=true, paramType="query")
	})
	public PageInfo<PersonalCasesVO> listCasesPartner(Page page, String beginTime, String endTime) {
		return appCaseService.listCasesPartner(page, beginTime, endTime);
	}

	/**
	 * 个人中心-破案统计
	 * @author 陈铭
	 * @date 2018-04-20 14:08:03
	 * @return
	 */
	@GetMapping(value = "finishTotal")
	@ApiOperation(value = "个人中心-破案统计", httpMethod = "GET", response = Map.class, notes = "个人中心-破案统计")
	public Map<String, Object> finishTotal() {
		return appCaseService.finishTotal();
	}

    /**
     * 案件详情
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     * @param ajbh 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     */
    @GetMapping(value = "detail")
    @ApiOperation(value = "案件详情", httpMethod = "GET", response = CasesBO.class, notes = "案件详情")
    public CasesBO detail(@RequestParam String ajbh){
        return pcCasesService.detail(ajbh);
    }
}
