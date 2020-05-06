package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.annotations.LoginRequire;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    IUserService userService;

    @ApiOperation("分页查询所有用户接口")
    @RequestMapping("/list/{currPage}/{size}")
    public AjaxVoResult list(@PathVariable int currPage, @PathVariable int size, User user) {
        final Page<User> page = new Page<User>(currPage, size);
        Wrapper<User> qw = new QueryWrapper<>(user);
        Page users = null;
        if (qw == null){
            users = userService.page(page);
        }else {
            users = userService.page(page,qw);
        }
        if (users.getRecords().size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), users);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @ApiOperation("新增用户接口")
    @PostMapping("/add")
    public AjaxVoResult add(@RequestBody User user) {
        /**
         *
         * @description: 新增用户
         * @param user
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        boolean b = userService.save(user);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @ApiOperation("删除用户接口")
    @PostMapping("/delete")
    public AjaxVoResult delete(int userId) {
        /**
         *
         * @description: 通过userId删除用户
         * @param userId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        User user = new User();
        user.setUserId(userId);
        boolean b = userService.removeById(userId);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @ApiOperation("更新用户接口")
    @PostMapping("/update")
    public AjaxVoResult update(User user) {
        /**
         *
         * @description: 通过userId更新用户信息
         * @param user
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = userService.updateById(user);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }
    @ApiOperation("获取单个用户信息接口")
    @PostMapping("/get")
    @LoginRequire(role = 2)
    public AjaxVoResult get(Page page,User user){
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param user
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<User> qw = new QueryWrapper<>(user);
        if (page == null){
            Page<User> userPage = new Page<>(1,5);
        }
        Page page1 = userService.page(page, qw);
        if (page1.getTotal() > 0){
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public AjaxVoResult register(@RequestBody User user){
        QueryWrapper<User> qw = new QueryWrapper<>();
//        学号唯一，作为匹配
        qw.eq("user_no",user.getUserNo());
        return userService.register(qw,user);
    }

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public AjaxVoResult login(User user, HttpServletRequest request, HttpServletResponse response){
        return userService.login(user,request,response);
    }

    @ApiOperation("用户注销接口")
    @RequestMapping("/logout")
    public AjaxVoResult logout(HttpServletRequest request){
        return userService.logout(request);
    }
}
