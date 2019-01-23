package com.hyzs.cidyth.portal.controller.approve;

import com.enterprisedt.util.debug.Logger;
import com.hyzs.cidyth.module.approve.entity.Approve;
import com.hyzs.cidyth.module.approve.service.ApproveService;
import com.hyzs.cidyth.module.clue.vo.ClueVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"点赞接口"})
@RestController
@RequestMapping("approve")
public class ApproveController {
	
    private static final Logger logger = Logger.getLogger(ApproveController.class);

    @Autowired
    private ApproveService approveService;

    @PostMapping(value = "insert")
    @ApiOperation(value = "点赞", httpMethod = "POST", response = ClueVO.class, notes = "点赞")
    @ApiImplicitParams({
            @ApiImplicitParam(name="approve", value="点赞", required=false, dataType="Approve", paramType="body")
    })
    public Map<String, Object> insert(@RequestBody Approve approve){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", approveService.insert(approve));
        return map;
    }

}
