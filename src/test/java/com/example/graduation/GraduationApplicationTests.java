package com.example.graduation;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.sys.dto.ProjectDTO;
import com.example.graduation.sys.dto.ProjectMemberDTO;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.mapper.ProjectMemberMapper;
import com.example.graduation.sys.mapper.UserMapper;
import com.example.graduation.sys.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;

@SpringBootTest
class GraduationApplicationTests {

    @Autowired
    UserMapper userMapper;
    @Autowired
    IUserService userService;
    @Autowired
    ProjectMemberMapper projectMemberMapper;

    @Test
    void contextLoads() {
        List<ProjectMemberDTO> projectByUserNo = projectMemberMapper.getProjectByUserNo("2015211802");
        projectByUserNo.forEach(System.out::println);
    }

}
