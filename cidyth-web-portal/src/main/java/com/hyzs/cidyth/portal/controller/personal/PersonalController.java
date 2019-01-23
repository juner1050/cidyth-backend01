package com.hyzs.cidyth.portal.controller.personal;

import com.github.pagehelper.PageInfo;
import com.hyzs.cidyth.module.personal.vo.PersonalCasesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enterprisedt.util.debug.Logger;
import com.hyzs.cidyth.module.personal.service.PersonalService;
import com.hyzs.cidyth.module.personal.vo.PersonalBO;
import com.hyzs.cidyth.module.uc.service.UserCenterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
@Api(tags={"个人工作台接口"})
@RestController
@RequestMapping("personal")
public class PersonalController {
	
    private static final Logger logger = Logger.getLogger(PersonalController.class);

    @Autowired
    private PersonalService personalService;
    @Autowired
    private UserCenterService userCenterService;

    /**
     * 工作台页面
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @return java.util.List<com.hyzs.cidyth.module.msg.vo.InfoVO>
     */
    @GetMapping(value = "detail")
    @ApiOperation(value = "工作台数据", httpMethod = "GET", response = PersonalBO.class, notes = "工作台数据")
    public PersonalBO detail(){
        return personalService.detail();
    }

    /**
     * 测试接口
     * @author 陈铭
     * @date 2018-04-10 17:31:23
     * @param infoVO 信息视图对象
     * @return java.util.List<com.hyzs.cidyth.module.msg.vo.InfoVO>
     */
    /*@GetMapping(value = "test")
    @ApiOperation(value = "测试接口", httpMethod = "GET", response = PersonalBO.class, notes = "测试接口")
    public void test(){
    	try {
			List<User> lsAllocateUsers = userCenterService.getUsersByDepartmentCodeAndPremission("440303510000",Lists.newArrayList(DemandProcessAction.ALLOCATE.getPremissionCode()));
			for(User user : lsAllocateUsers){
				logger.info(user.getName());
			}
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
    
}
