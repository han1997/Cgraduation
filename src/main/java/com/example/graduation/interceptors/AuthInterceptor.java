package com.example.graduation.interceptors;

import com.example.graduation.annotations.LoginRequire;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author: huiyuan
 * @date: 2020/5/5 21:56
 * @desc: 这是描述
 **/

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    IUserService userService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = null;
        if (handler instanceof HandlerMethod){
            handlerMethod = (HandlerMethod) handler;
//            获取方法注解
            LoginRequire loginRequire = handlerMethod.getMethodAnnotation(LoginRequire.class);
            if (loginRequire != null && loginRequire.required()){
//                方法需要登录
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");

                if (user == null){
//                    session中没有user信息，重定向到login页面
                    response.sendRedirect("https://www.baidu.com");
                    return false;
                }

/*//                写jsessionid到cookie
                String sessionId = session.getId();
                CookieUtil.setCookie(request,response,"sessionId",sessionId,60*15,true);*/

//                判断浏览网页需要的角色权限
                int rolePerm = loginRequire.role();
                if (Integer.parseInt(user.getUserRole()) > rolePerm){
//                    浏览权限不够，重定向到错误页面
                    response.sendRedirect("https://www.dogedoge.com/");
                    return false;
                }
            }
//            不需要登录
        }
        return true;
    }

}
