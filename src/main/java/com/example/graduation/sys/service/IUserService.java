package com.example.graduation.sys.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
public interface IUserService extends IService<User> {
    AjaxVoResult register(QueryWrapper<User> queryWrapper, User user);
    AjaxVoResult login(User user, HttpServletRequest request, HttpServletResponse response);

    AjaxVoResult logout(HttpServletRequest request);

    AjaxVoResult addUser(User user);
    void psdEnc(User user);
}
