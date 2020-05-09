package com.example.graduation.interceptors;

import com.example.graduation.annotations.LoginRequire;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * @author: huiyuan
 * @date: 2020/5/5 21:56
 * @desc: 这是描述
 **/
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = null;
        if (handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) handler;
//            获取方法注解
            LoginRequire loginRequire = handlerMethod.getMethodAnnotation(LoginRequire.class);
            if (loginRequire != null && loginRequire.required()) {
//                方法需要登录
                HttpSession session = request.getSession();
                User user = (User) session.getAttribute("user");
                log.info("拦截器--sessionid-->" + session.getId());
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                PrintWriter pw = response.getWriter();
                ObjectMapper mapper = new ObjectMapper();

                if (user == null) {
//                    session中没有user信息，重定向到login页面
                    String respStr = mapper.writeValueAsString(new AjaxVoResult(StatusCode.TOKEN_NOT_AVAILABLE.getCode(), StatusCode.TOKEN_NOT_AVAILABLE.getMessage(), null));
                    pw.write(respStr);
                    pw.flush();
                    pw.close();
                    return false;
                }

//                user不为空,判断浏览网页需要的角色权限
                int rolePerm = loginRequire.role();
                if (Integer.parseInt(user.getUserRole()) > rolePerm) {
//                    浏览权限不够，重定向到错误页面
                    String respStr = mapper.writeValueAsString(new AjaxVoResult(StatusCode.FORBIDDEN.getCode(), StatusCode.FORBIDDEN.getMessage(), null));
                    pw.write(respStr);
                    pw.flush();
                    pw.close();
                    return false;
                }
            }
//            不需要登录
        }
        return true;
    }

}
