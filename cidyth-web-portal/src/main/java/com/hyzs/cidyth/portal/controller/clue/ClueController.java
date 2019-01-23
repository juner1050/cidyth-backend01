package com.hyzs.cidyth.portal.controller.clue;

import com.enterprisedt.util.debug.Logger;
import com.hyzs.cidyth.module.clue.service.ClueService;
import com.hyzs.cidyth.module.clue.vo.ClueAppraiseSave;
import com.hyzs.cidyth.module.clue.vo.ClueEvaluation;
import com.hyzs.cidyth.module.clue.vo.ClueVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"线索接口"})
@RestController
@RequestMapping("clue")
public class ClueController {
	
    private static final Logger logger = Logger.getLogger(ClueController.class);

    @Autowired
    private ClueService clueService;

    /**
     * 线索反馈
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param clueVO 线索视图对象
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.ClueVO>
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "线索反馈", httpMethod = "POST", response = ClueVO.class, notes = "线索反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name="clueVO", value="线索反馈", required=false, dataType="ClueVO", paramType="body")
    })
    public Map<String, Object> insert(ClueVO clueVO){
        return clueService.insert(clueVO);
    }

    /**
     * 线索上传
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param clueVO 线索视图对象
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.ClueVO>
     */
    @PostMapping(value = "uploadClue")
    @ApiOperation(value = "线索上传", httpMethod = "POST", response = ClueVO.class, notes = "线索上传")
    public void upload(@RequestBody ClueVO clueVO){
    	clueService.uploadClue(clueVO);
    }

    /**
     * 线索详情
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param id 线索id
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.ClueVO>
     */
    @GetMapping(value = "detail")
    @ApiOperation(value = "线索详情", httpMethod = "GET", response = ClueVO.class, notes = "线索详情")
    public ClueVO detail(Integer id){
        return clueService.detail(id);
    }

    /**
     * 获取破案线索评价
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param ajbh 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.ClueVO>
     */
    @GetMapping(value = "listEvaluate")
    @ApiOperation(value = "获取破案线索评价", httpMethod = "GET", response = ClueEvaluation.class, notes = "获取破案线索评价")
    public ClueEvaluation listEvaluate(@RequestParam String ajbh){
        return clueService.listClueEvaluateByCases(ajbh);
    }

    /**
     * 保存线索评价
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param ClueAppraiseSave 线索评价对象
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.ClueVO>
     */
    /*@PostMapping(value = "saveEvaluate")
    @ApiOperation(value = "保存线索评价", httpMethod = "POST", response = Map.class, notes = "保存线索评价")
    public Map<String, Object> saveEvaluate(@RequestBody ClueAppraiseSave ClueAppraiseSave){
        Map<String, Object> map = new HashMap<String, Object>();

        return map;
    }*/



}
