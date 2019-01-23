package com.hyzs.cidyth.portal.controller.integral;

import com.enterprisedt.util.debug.Logger;
import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.common.page.Page;
import com.hyzs.cidyth.module.integral.service.IntegralTitleService;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleAvatarDTO;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleSaveDTO;
import com.hyzs.cidyth.module.integral.vo.IntegralTitleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"积分头衔接口"})
@RestController
@RequestMapping("integralTitle")
public class IntegralTitleController {
	
    private static final Logger logger = Logger.getLogger(IntegralTitleController.class);

    @Autowired
    private IntegralTitleService integralTitleService;

    /**
     * 查询
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralTitleVO 实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralTitleVO>
     */
    @GetMapping(value = "list")
    @ApiOperation(value = "查询", httpMethod = "GET", response = IntegralTitleVO.class, notes = "查询")
    public PageInfo<IntegralTitleVO> list(IntegralTitleVO integralTitleVO, Page page){
        return integralTitleService.list(integralTitleVO, page);
    }

    /**
     * 新增
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralTitleVO 实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralTitleVO>
     */
    @PostMapping(value = "insert")
    @ApiOperation(value = "新增", httpMethod = "POST", response = Map.class, notes = "新增")
    public Map<String, Object> insert(IntegralTitleSaveDTO integralTitleSaveDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralTitleService.insert(integralTitleSaveDTO));
        return map;
    }

    /**
     * 编辑
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralTitleVO 实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralTitleVO>
     */
    @PostMapping(value = "update")
    @ApiOperation(value = "编辑", httpMethod = "POST", response = Map.class, notes = "编辑")
    public Map<String, Object> update(IntegralTitleSaveDTO integralTitleSaveDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralTitleService.update(integralTitleSaveDTO));
        return map;
    }

    /**
     * 删除
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralTitleVO 实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralTitleVO>
     */
    @PostMapping(value = "delete")
    @ApiOperation(value = "删除", httpMethod = "POST", response = Map.class, notes = "删除")
    public Map<String, Object> delete(@RequestBody IntegralTitleVO integralTitleVO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", integralTitleService.delete(integralTitleVO.getId()));
        return map;
    }

    /**
     * 上传头像
     * @author 陈铭
     * @date 2018-04-16 19:15:12
     * @param integralTitleVO 实体
     * @return java.util.List<com.hyzs.cidyth.module.clue.vo.IntegralTitleVO>
     */
    @PostMapping(value = "avatar")
    @ApiOperation(value = "上传头像", httpMethod = "POST", response = Map.class, notes = "上传头像")
    public Map<String, Object> avatar(IntegralTitleAvatarDTO integralTitleAvatarDTO){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", integralTitleService.uploadAvatar(integralTitleAvatarDTO));
        return map;
    }
}
