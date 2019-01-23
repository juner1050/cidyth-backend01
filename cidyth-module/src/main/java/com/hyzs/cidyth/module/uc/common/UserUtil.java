package com.hyzs.cidyth.module.uc.common;

import com.hyzs.cidyth.common.enums.SystemExceptionEnum;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import com.hyzs.cidyth.core.exception.ServiceException;
import com.hyzs.cidyth.module.uc.vo.User;

/**
 * Created by 1 on 2018/10/30.
 */
public class UserUtil {

    public static User getUser() {
        User loginUser = (User)ContextUserHelper.getLoginUser();
        if(loginUser == null){
            throw new ServiceException(SystemExceptionEnum.GET_USER_FAILED.code(), SystemExceptionEnum.GET_USER_FAILED.descp());
        }
        if(loginUser.getDepartment() == null){
            throw new ServiceException(SystemExceptionEnum.GET_DEPTFAILED.code(), SystemExceptionEnum.GET_DEPTFAILED.descp());
        }
        return loginUser;
    }

}
