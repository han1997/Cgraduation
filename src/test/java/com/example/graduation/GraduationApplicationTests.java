package com.example.graduation;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.mapper.UserMapper;
import com.example.graduation.sys.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
class GraduationApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    IUserService userService;

    @Test
    void contextLoads() {
//        final List<User> users = userMapper.selectList(null);
        final List<User> list = userService.list(null);
        list.forEach(System.out::println);
        final QueryWrapper<User> qw = new QueryWrapper<>();
        qw.like("user_no","2015211");
        final Page<User> page = new Page<>(0, 10,false);
        final Page<User> page1 = userService.page(page, qw);
        page1.getRecords().forEach(System.out::println);
    }

}
