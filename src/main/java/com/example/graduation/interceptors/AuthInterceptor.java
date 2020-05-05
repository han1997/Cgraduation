package com.example.graduation.interceptors;

import com.example.graduation.annotations.LoginRequire;
import com.example.graduation.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandle;

/**
 * @author: huiyuan
 * @date: 2020/5/5 21:56
 * @desc: 这是描述
 **/
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    IUserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = null;
        if (handler instanceof HandlerMethod){
            handlerMethod = (HandlerMethod) handler;
//            获取方法注解
            LoginRequire loginRequire = handlerMethod.getMethodAnnotation(LoginRequire.class);
            if (loginRequire.required()){
//                方法需要登录

            }
//            不需要登录
        }
        return true;
    }

}
