package com.hyzs.cidyth.app.controller.dic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hyzs.cidyth.module.dic.entity.Dictionary;
import com.hyzs.cidyth.module.dic.service.DicService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"字典接口"})
@RestController
@RequestMapping("dic")
public class DicController {
	
	private static final Logger logger = LoggerFactory.getLogger(DicController.class);

	@Autowired
	private DicService dicService;

    /**
     * 获取需求类型
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @return
     */
    @GetMapping(value = "listByDemandType")
    @ApiOperation(value = "获取需求类型", httpMethod = "GET", response = Dictionary.class, notes = "获取需求类型")
    public List<Dictionary> listByDemandType(){
    	//根据【需求类型】获取字典的数据
        return dicService.listByLxbh("xqlx");
    }

    /**
     * 根据类型编号获取字典
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @return
     */
    @GetMapping(value = "listByLxbh")
    @ApiOperation(value = "根据类型编号获取字典", httpMethod = "GET", response = Dictionary.class, notes = "根据类型编号获取字典")
    public List<Dictionary> listByLxbh(String lxbh){
        return dicService.listByLxbh(lxbh);
    }
}
