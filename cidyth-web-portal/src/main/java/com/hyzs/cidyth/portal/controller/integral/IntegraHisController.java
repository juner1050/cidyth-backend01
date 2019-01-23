package com.hyzs.cidyth.portal.controller.integral;

import com.enterprisedt.util.debug.Logger;
import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.integral.entity.IntegralHis;
import com.hyzs.cidyth.module.integral.service.IntegralHisService;
import com.hyzs.cidyth.module.integral.vo.CasesJoinRankVO;
import com.hyzs.cidyth.module.integral.vo.IntegralHisVO;
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
@Api(tags={"积分历史接口"})
@RestController
@RequestMapping("integralHis")
public class IntegraHisController {
	
    private static final Logger logger = Logger.getLogger(IntegraHisController.class);

    @Autowired
    private IntegralHisService integralHisService;

    /**
     * 积分历史查询
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralHisVO 积分历史实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralHisVO>
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "积分历史查询", httpMethod = "GET", response = IntegralHisVO.class, notes = "积分历史查询")
    public PageInfo<IntegralHisVO> list(IntegralHisVO integralHisVO, Page page){
        return integralHisService.list(integralHisVO, page);
    }

    /**
     * 积分历史新增
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralHisVO 积分历史实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralHisVO>
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "积分历史新增", httpMethod = "POST", response = Map.class, notes = "积分历史新增")
    public Map<String, Object> insert(@RequestBody IntegralHisVO integralHisVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralHisService.insert(integralHisVO));
        return map;
    }

    /**
     * 积分历史编辑
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralHisVO 积分历史实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralHisVO>
     */
    @PostMapping(value = "update")
    @ApiOperation(value = "积分历史编辑", httpMethod = "POST", response = Map.class, notes = "积分历史编辑")
    public Map<String, Object> update(@RequestBody IntegralHisVO integralHisVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralHisService.update(integralHisVO));
        return map;
    }

    /**
     * 积分历史删除
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralHisVO 积分历史实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralHisVO>
     */
    @PostMapping(value = "delete")
    @ApiOperation(value = "积分历史删除", httpMethod = "POST", response = Map.class, notes = "积分历史删除")
    public Map<String, Object> delete(@RequestBody IntegralHisVO integralHisVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralHisService.delete(integralHisVO.getId()));
        return map;
    }

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
