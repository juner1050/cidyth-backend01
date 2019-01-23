package com.hyzs.cidyth.app.controller.msg;

import com.enterprisedt.util.debug.Logger;
import com.hyzs.cidyth.module.msg.service.InfoService;
import com.hyzs.cidyth.module.msg.vo.InfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping(value = "insert")
    @ApiOperation(value = "信息新增", httpMethod = "POST", response = InfoVO.class, notes = "信息新增")
    public Map<String, Object> insert(InfoVO infoVO){
        return infoService.insert(infoVO);
    }

}
