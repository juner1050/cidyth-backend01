package com.hyzs.cidyth.app.controller.clue;

import com.enterprisedt.util.debug.Logger;
import com.hyzs.cidyth.module.clue.service.ClueService;
import com.hyzs.cidyth.module.clue.vo.ClueVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("id", clueService.insert(clueVO));
        return map;
    }

}
