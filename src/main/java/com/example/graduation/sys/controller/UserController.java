package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/get")
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
}
