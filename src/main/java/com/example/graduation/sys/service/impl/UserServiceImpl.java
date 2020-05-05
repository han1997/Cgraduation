package com.example.graduation.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.mapper.UserMapper;
import com.example.graduation.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listByPage(IPage<User> page, Wrapper<User> qw) {
        IPage<User> userPage = null;
        if (qw.equals(null)){
            userPage = page(page);
        }
        else {
            userPage = page(page,qw);
        }
        return userPage.getRecords();
    }
}
