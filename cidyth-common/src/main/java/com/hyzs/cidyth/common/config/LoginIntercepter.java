package com.hyzs.cidyth.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.hyzs.cidyth.common.enums.SystemExceptionEnum;
import com.hyzs.cidyth.common.helper.ContextUserHelper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by 1 on 2018/9/13.
 */
public class LoginIntercepter implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute(ContextUserHelper.CURRENT_LOGIN_USER) == null){
            ObjectMapper om = new ObjectMapper();
            Map<String, String> map = Maps.newHashMap();
            map.put("code", SystemExceptionEnum.LOGIN_TIMEOUT.code());
            map.put("message", SystemExceptionEnum.LOGIN_TIMEOUT.name());
            response.getWriter().append(om.writeValueAsString(map));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
