package com.hyzs.cidyth.module.personal.service;

import java.util.List;

import com.hyzs.cidyth.module.personal.vo.PersonalBO;
import com.hyzs.cidyth.module.personal.vo.PersonalDemandVO;

/**
 * Created by Administrator on 2018/4/10 0010.
 */
public interface PersonalService {

    /**
     * 我的请求
     * @author 陈铭
     * @date 2018-05-14 16:58:06
     * @param lrry 录入人员
     * @return
     */
    List<PersonalDemandVO> listPersonalMyDemand();

    /**
     * 待办任务
     * @author 陈铭
     * @date 2018-05-14 16:58:06
     * @return
     */
    List<PersonalDemandVO> listPersonalHandleDemand();

    /**
     * 本月用户点评情况
     * @author 陈铭
     * @date 2018-05-14 16:58:06
     * @return
     */
    List<PersonalDemandVO> listPersonalMonthFeedBacked();
    
    /**
     * 工作台详情
     * @return
     */
    PersonalBO detail();

}
