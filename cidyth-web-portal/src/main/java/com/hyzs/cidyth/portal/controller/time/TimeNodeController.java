package com.hyzs.cidyth.portal.controller.time;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.TimeNodeEnum;
import com.hyzs.cidyth.module.time.vo.TimeNodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hyzs.cidyth.module.time.entity.TimeNode;
import com.hyzs.cidyth.module.time.service.TimeNodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"时间轴接口"})
@RestController
@RequestMapping("timeNode")
public class TimeNodeController {
	
	private static final Logger logger = LoggerFactory.getLogger(TimeNodeController.class);

	@Autowired
	private TimeNodeService timeNodeService;

    /**
     * 根据案件编号获取时间轴
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @return
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "根据案件编号获取时间轴", httpMethod = "GET", response = TimeNode.class, notes = "根据案件编号获取时间轴")
    public List<TimeNodeVO> list(@RequestParam String ajbh){
        return timeNodeService.listByAjbh(ajbh);
    }

    /**
     * 时间轴修改
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @return
     */
    @PostMapping(value = "update")
    @ApiOperation(value = "时间轴修改", httpMethod = "POST", response = TimeNode.class, notes = "时间轴修改")
    public int update(@RequestBody TimeNode timeNode){
        return timeNodeService.update(timeNode);
    }

    /**
     * 获取时间轴节点类型
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @return
     */
    @GetMapping(value = "getNodeType")
    @ApiOperation(value = "获取时间轴节点类型", httpMethod = "GET", response = TimeNode.class, notes = "获取时间轴节点类型")
    public Map<String, Object> getNodeType(){
        return TimeNodeEnum.toMap();
    }



}
