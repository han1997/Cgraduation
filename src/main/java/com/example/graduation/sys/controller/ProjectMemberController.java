package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.dto.ProjectMemberDTO;
import com.example.graduation.sys.entity.Project;
import com.example.graduation.sys.entity.ProjectMember;
import com.example.graduation.sys.service.IProjectMemberService;
import com.example.graduation.sys.service.IProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 项目成员表 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@RestController
@Api(tags = "项目成员相关接口", description = "提供用户相关的 Rest API")
@RequestMapping("/sys/project-member")
public class ProjectMemberController {
    @Autowired
    IProjectService projectService;
    @Autowired
    IProjectMemberService projectMemberService;

    @GetMapping("/list")
    @ApiOperation("查询所有项目成员接口")
    public AjaxVoResult list(ProjectMember projectMember) {
        QueryWrapper<ProjectMember> qw = new QueryWrapper<>(projectMember);
        List<ProjectMember> projectMembers = projectMemberService.list(qw);
        if (projectMembers.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), projectMembers);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    @ApiOperation("新增项目成员信息")
    public AjaxVoResult add(ProjectMember projectMember, String projectPsd) {
        /**
         *
         * @description: 新增用户
         * @param projectMember
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        String projectId = projectMember.getProjectId();
        boolean b = false;
        if (StringUtils.isNotBlank(projectId)) {
//            获取项目密码
            Project byId = projectService.getById(projectId);
            if (null != byId) {
//                    项目密码验证
                if (projectPsd.equals(byId.getProjectPsd())) {
//                    添加验证是否已存在
                    QueryWrapper<ProjectMember> queryWrapper = new QueryWrapper<>();
                    queryWrapper.eq("project_id",projectMember.getProjectId());
                    queryWrapper.eq("project_member_id",projectMember.getProjectMemberId());
                    ProjectMember one = null;
                    try {
                        one = projectMemberService.getOne(queryWrapper);
                    } catch (Exception e) {
                        e.printStackTrace();
                        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "数据库查询到多条数据--已重复添加该学号");
                    }
                    if (null != one){
                        return new AjaxVoResult(StatusCode.USERNO_EXISTS.getCode(), StatusCode.USERNO_EXISTS.getMessage(), null);
                    }
                    b = projectMemberService.save(projectMember);
                }else {
                    return new AjaxVoResult(StatusCode.ADMIN_USER_WRONG_PASSWORD.getCode(), StatusCode.ADMIN_USER_WRONG_PASSWORD.getMessage(), null);
                }
            }
        }
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @Transactional
    @PostMapping("/delete")
    @ApiOperation("删除项目成员信息")
    public AjaxVoResult delete(int[] projectMemberIds) {
        /**
         *
         * @description: 通过projectMemberId删除用户
         * @param projectMemberId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        ArrayList<AjaxVoResult> ajaxVoResults = new ArrayList<>();
        for (int i = 0; i < projectMemberIds.length; i++) {
            int projectMemberId = projectMemberIds[i];
            boolean b = projectMemberService.removeById(projectMemberId);
            if (b) {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "项目成员Id：" + projectMemberId + "已被删除"));
            } else {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "项目成员Id：" + projectMemberId + "删除出错"));
            }
        }
        return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), ajaxVoResults);
    }

    @PostMapping("/update")
    @ApiOperation("更新项目成员信息")
    public AjaxVoResult update(ProjectMember projectMember) {
        /**
         *
         * @description: 通过projectMemberId更新用户信息
         * @param projectMember
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = projectMemberService.updateById(projectMember);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/get")
    @ApiOperation("获取前11条项目成员信息")
    public AjaxVoResult get(Page page, ProjectMember projectMember) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param projectMember
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<ProjectMember> qw = new QueryWrapper<>(projectMember);
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = projectMemberService.page(page, qw);
        if (page1.getTotal() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @ApiOperation("从学生学号查询项目信息，项目发布人姓名")
    @GetMapping("/getProject")
    public AjaxVoResult getProject(String projectMemberId) {
        if (StringUtils.isNotBlank(projectMemberId)) {
            return projectMemberService.getProject(projectMemberId);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "没有参数");
    }

}
