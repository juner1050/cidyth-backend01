package com.hyzs.cidyth.portal.controller.integral;

import com.enterprisedt.util.debug.Logger;
import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.integral.service.IntegralRuleService;
import com.hyzs.cidyth.module.integral.vo.IntegralRuleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"积分规则接口"})
@RestController
@RequestMapping("integralRule")
public class IntegralRuleController {
	
    private static final Logger logger = Logger.getLogger(IntegralRuleController.class);

    @Autowired
    private IntegralRuleService integralRuleService;

    /**
     * 积分规则查询
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralRuleVO 积分规则实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralRuleVO>
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "积分规则查询", httpMethod = "GET", response = IntegralRuleVO.class, notes = "积分规则查询")
    public PageInfo<IntegralRuleVO> list(IntegralRuleVO integralRuleVO, Page page){
        return integralRuleService.list(integralRuleVO, page);
    }

    /**
     * 积分规则新增
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralRuleVO 积分规则实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralRuleVO>
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "积分规则新增", httpMethod = "POST", response = Map.class, notes = "积分规则新增")
    public Map<String, Object> insert(@RequestBody IntegralRuleVO integralRuleVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralRuleService.insert(integralRuleVO));
        return map;
    }

    /**
     * 积分规则编辑
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralRuleVO 积分规则实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralRuleVO>
     */
    @PostMapping(value = "update")
    @ApiOperation(value = "积分规则编辑", httpMethod = "POST", response = Map.class, notes = "积分规则编辑")
    public Map<String, Object> update(@RequestBody IntegralRuleVO integralRuleVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralRuleService.update(integralRuleVO));
        return map;
    }

    /**
     * 积分规则删除
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralRuleVO 积分规则实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralRuleVO>
     */
    @PostMapping(value = "delete")
    @ApiOperation(value = "积分规则删除", httpMethod = "POST", response = Map.class, notes = "积分规则删除")
    public Map<String, Object> delete(@RequestBody IntegralRuleVO integralRuleVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralRuleService.delete(integralRuleVO.getId()));
        return map;
    }

}
