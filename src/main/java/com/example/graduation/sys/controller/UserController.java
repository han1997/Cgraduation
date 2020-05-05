package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    IUserService userService;

    @RequestMapping("/list/{currPage}/{size}")
    public AjaxVoResult list(@PathVariable int currPage, @PathVariable int size, QueryWrapper qw) {
        final Page<User> page = new Page<User>(currPage, size);
        List<User> users = userService.listByPage(page, qw);
        if (!CollectionUtils.isEmpty(users)) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), users);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(),StatusCode.ERROR.getMessage(),null);
    }

    @RequestMapping("/add")
    public AjaxVoResult add(){
        userService.saveOrUpdate()
        return new AjaxVoResult(StatusCode.ERROR.getCode(),StatusCode.ERROR.getMessage(),null);
    }
}
