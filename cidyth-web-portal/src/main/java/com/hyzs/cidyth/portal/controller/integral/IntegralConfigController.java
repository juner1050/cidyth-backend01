package com.hyzs.cidyth.portal.controller.integral;

import com.enterprisedt.util.debug.Logger;
import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.enums.IntegralTypeEnum;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.integral.service.IntegralConfigService;
import com.hyzs.cidyth.module.integral.vo.IntegralConfigVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"积分配置接口"})
@RestController
@RequestMapping("integralConfig")
public class IntegralConfigController {
	
    private static final Logger logger = Logger.getLogger(IntegralConfigController.class);

    @Autowired
    private IntegralConfigService integralConfigService;

    /**
     * 积分配置查询
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralConfigVO 积分配置实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralConfigVO>
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "积分配置查询", httpMethod = "GET", response = IntegralConfigVO.class, notes = "积分配置查询")
    public PageInfo<IntegralConfigVO> list(IntegralConfigVO integralConfigVO, Page page){
        return integralConfigService.list(integralConfigVO, page);
    }

    /**
     * 积分配置新增
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralConfigVO 积分配置实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralConfigVO>
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "积分配置新增", httpMethod = "POST", response = Map.class, notes = "积分配置新增")
    public Map<String, Object> insert(@RequestBody IntegralConfigVO integralConfigVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralConfigService.insert(integralConfigVO));
        return map;
    }

    /**
     * 积分配置编辑
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralConfigVO 积分配置实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralConfigVO>
     */
    @PostMapping(value = "update")
    @ApiOperation(value = "积分配置编辑", httpMethod = "POST", response = Map.class, notes = "积分配置编辑")
    public Map<String, Object> update(@RequestBody IntegralConfigVO integralConfigVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralConfigService.update(integralConfigVO));
        return map;
    }

    /**
     * 积分配置删除
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralConfigVO 积分配置实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralConfigVO>
     */
    @PostMapping(value = "delete")
    @ApiOperation(value = "积分配置删除", httpMethod = "POST", response = Map.class, notes = "积分配置删除")
    public Map<String, Object> delete(@RequestBody IntegralConfigVO integralConfigVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralConfigService.delete(integralConfigVO.getId()));
        return map;
    }

    /**
     * 积分配置初始化数据
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralConfigVO>
     */
    @GetMapping(value = "initData")
    @ApiOperation(value = "积分配置初始化数据", httpMethod = "GET", response = Map.class, notes = "积分配置初始化数据")
    public Map<String, Object> initData(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("initData", integralConfigService.initData());
        map.put("integralType", IntegralTypeEnum.codes());
        return integralConfigService.initData();
    }

}
