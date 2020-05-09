package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.Project;
import com.example.graduation.sys.service.IProjectService;
import com.example.graduation.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 项目信息表 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Slf4j
@RestController
@Api(tags = "项目相关接口", description = "提供用户相关的 Rest API")
@RequestMapping("/sys/project")
public class ProjectController {
    @Autowired
    IUserService userService;
    @Autowired
    IProjectService projectService;

    @ApiOperation("管理员分页查询所有项目接口")
    @RequestMapping("/list")
    public AjaxVoResult list(Project project) {
        Wrapper<Project> qw = new QueryWrapper<>(project);
        List<Project> projects = projectService.list();
        if (projects.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), projects);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    public AjaxVoResult add(Project project) {
        /**
         *
         * @description: 新增项目
         * @param project
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        boolean b = projectService.saveProject(project);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/delete")
    public AjaxVoResult delete(int projectId) {
        /**
         *
         * @description: 通过projectId删除项目
         * @param projectId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        Project user = new Project();
        user.setProjectId(projectId);
        boolean b = projectService.removeById(projectId);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/update")
    public AjaxVoResult update(Project project) {
        /**
         *
         * @description: 通过userId更新用户信息
         * @param project
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = projectService.updateById(project);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/get")
    public AjaxVoResult get(Page page, Project project) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param project
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<Project> qw = new QueryWrapper<>(project);
        if (page == null) {
            page = new Page<>(1, 11);
        }
        Page page1 = projectService.page(page, qw);
        if (page1.getTotal() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
