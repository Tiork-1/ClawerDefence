package com.example.clawerdefence.config;

import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //登录成功之后，应该有用户的session
        System.out.println("启动拦截器");
        if(StpUtil.isLogin()){
            return true;
        }
        System.out.println("拦截成功");
        response.sendRedirect("/");
        return false;
    }

}
