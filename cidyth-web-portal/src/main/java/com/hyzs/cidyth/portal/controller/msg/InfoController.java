package com.hyzs.cidyth.portal.controller.msg;

import com.enterprisedt.util.debug.Logger;
import com.hyzs.cidyth.module.msg.service.InfoService;
import com.hyzs.cidyth.module.msg.vo.InfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"信息接口"})
@RestController
@RequestMapping("info")
public class InfoController {
	
    private static final Logger logger = Logger.getLogger(InfoController.class);

    @Autowired
    private InfoService infoService;


    /**
     * 信息新增
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param infoVO 信息视图对象
     * @return java.util.List<com.hyzs.cidyth.module.msg.vo.InfoVO>
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "信息新增", httpMethod = "POST", response = InfoVO.class, notes = "信息新增")
    public Map<String, Object> insert(InfoVO infoVO){
        return infoService.insert(infoVO);
    }
    /**
     * 信息详情
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param id 信息id
     * @return java.util.List<com.hyzs.cidyth.module.msg.vo.InfoVO>
     */
    @GetMapping(value = "detail")
    @ApiOperation(value = "信息详情", httpMethod = "GET", response = InfoVO.class, notes = "信息详情")
    public InfoVO detail(Integer id){
        return infoService.detail(id);
    }
    /**
     * 信息列表
     * @author 陈铭
     * @date 2018-04-19 11:00:17
     * @param infoVO 信息视图对象
     * @return java.util.List<com.hyzs.cidyth.module.msg.vo.InfoVO>
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "信息列表", httpMethod = "GET", response = InfoVO.class, notes = "信息列表")
    public List<InfoVO> list(@RequestBody InfoVO infoVO){
        return infoService.list(infoVO);
    }
    
    


}
