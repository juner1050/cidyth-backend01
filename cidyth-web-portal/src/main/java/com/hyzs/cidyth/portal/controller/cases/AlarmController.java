package com.hyzs.cidyth.portal.controller.cases;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.base.entity.AlarmSituation;
import com.hyzs.cidyth.module.base.service.AlarmSituationCidService;
import com.hyzs.cidyth.module.base.service.AlarmSituationService;
import com.hyzs.cidyth.module.base.vo.CasesVO;
import com.hyzs.cidyth.module.cases.vo.*;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"警情接口"})
@RestController
@RequestMapping("alarm")
public class AlarmController {
	private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);

    @Autowired
    private AlarmSituationService alarmSituationService;
    @Autowired
    private AlarmSituationCidService alarmSituationCidService;


    /**
     * 警情查询
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param alarmSituationVO 案件视图对象
     * @param page 分页对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.AlarmSituationVO>
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "警情查询", httpMethod = "GET", response = AlarmSituationVO.class, notes = "警情查询")
    public PageInfo<AlarmSituationVO> list(AlarmSituationVO alarmSituationVO, Page page){
        return null;//alarmSituationService.list(alarmSituationVO, page);
    }

    /**
     * 警情待提取列表（查询警综数据库获取数据）
     * @author 陈铭
     * @date 2018-04-20 14:08:03
     * @param alarmSituationVO 案件视图对象
     * @param page 分页对象
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     */
    @GetMapping(value = "listPickAlarm")
    @ApiOperation(value = "警情待提取列表", httpMethod = "GET", response = AlarmSituationVO.class, notes = "警情待提取列表")
    public PageInfo<AlarmSituationVO> listPickAlarm(AlarmSituationVO alarmSituationVO, Page page){
        return null;//alarmSituationCidService.listPickAlarm(alarmSituationVO, page);
    }

    /**
     * 警情提取
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param ajbhs 案件编号
     * @return java.util.List<com.hyzs.cidyth.module.cases.vo.CasesVO>
     */
    @GetMapping(value = "remotePickAlarm")
    @ApiOperation(value = "警情提取", httpMethod = "GET", response = CasesVO.class, notes = "警情提取")
    @ApiImplicitParams({
            @ApiImplicitParam(name="ajbhs", value="警情编号（多个编号逗号分隔）", required=true, dataType="String", paramType="query")
    })
    public Map<String, Object> remotePickAlarm(String ajbhs){
        Map<String, Object> map = new HashMap<>();
        AlarmSituation alarm = alarmSituationService.getAlarmByAjbh(ajbhs);
        if(alarm == null){
            map.put("ajbh", null);
        }else{
            map.put("ajbh", alarm.getAjbh());
        }
        return map;
    }


}
