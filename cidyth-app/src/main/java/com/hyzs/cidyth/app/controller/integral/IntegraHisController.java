package com.hyzs.cidyth.app.controller.integral;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisedt.util.debug.Logger;
import com.hyzs.cidyth.module.integral.entity.IntegralHis;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.integral.vo.CasesJoinRankVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"积分历史接口"})
@RestController
@RequestMapping("integralHis")
public class IntegraHisController {
	
    private static final Logger logger = Logger.getLogger(IntegraHisController.class);

    @Autowired
    private IntegralHisService integralHisService;

    /**
     * 参与人数积分排名
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param ajbh 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralHisVO>
     */
    @GetMapping(value = "casesScoreRank")
    @ApiOperation(value = "参与人数积分排名", httpMethod = "GET", response = Map.class, notes = "参与人数积分排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ajbh", value="案件编号", required=true, dataType="string", paramType="query")
    })
    public List<CasesJoinRankVO> casesScoreRank(String ajbh){
        return integralHisService.listCasesScore(ajbh);
    }

    /**
     * 积分统计
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param ajbh 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralHisVO>
     */
    @PostMapping(value = "countScore")
    @ApiOperation(value = "积分统计", httpMethod = "POST", response = Float.class, notes = "积分统计")
    public Float countScore(@RequestBody IntegralHis integralHis){
        return integralHisService.countScore(integralHis);
    }

    /**
     * 个人中心-业务积分
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param ajbh 案件编号
     * @return
     */
    @GetMapping(value = "total")
    @ApiOperation(value = "个人中心-业务积分", httpMethod = "GET", response = Map.class, notes = "个人中心-业务积分")
    public Map<String, Object> total(){
        return integralHisService.nodeScore();
    }



}
