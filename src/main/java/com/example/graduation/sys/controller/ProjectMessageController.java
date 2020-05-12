package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.ProjectMessage;
import com.example.graduation.sys.service.IProjectMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 项目消息 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-07
 */
@RestController
@RequestMapping("/sys/project-message")
public class ProjectMessageController {
    @Autowired
    IProjectMessageService projectMessageService;

    @GetMapping("/list")
    public AjaxVoResult list(ProjectMessage projectMessage) {
        QueryWrapper<ProjectMessage> qw = new QueryWrapper<>(projectMessage);
        List<ProjectMessage> projectMessages = projectMessageService.list(qw);
        if (projectMessages.size() > 0) {
            projectMessages.forEach(projectMessage1 -> projectMessage1.setPublishTime(projectMessage1.getPublishTime().substring(0,10)));
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), projectMessages);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    public AjaxVoResult add(ProjectMessage projectMessage) {
        /**
         *
         * @description: 新增用户
         * @param projectMessage
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        boolean b = projectMessageService.add(projectMessage);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/delete")
    @Transactional
    public AjaxVoResult delete(int projectMessageId) {
        /**
         *
         * @description: 通过projectMessageId删除用户
         * @param projectMessageId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        ProjectMessage projectMessage = new ProjectMessage();
        projectMessage.setPmId(projectMessageId);
        boolean b = projectMessageService.removeById(projectMessageId);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/update")
    public AjaxVoResult update(ProjectMessage projectMessage) {
        /**
         *
         * @description: 通过projectMessageId更新用户信息
         * @param projectMessage
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = projectMessageService.updateById(projectMessage);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/get")
    public AjaxVoResult get(Page page, ProjectMessage projectMessage) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param projectMessage
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<ProjectMessage> qw = new QueryWrapper<>(projectMessage);
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = projectMessageService.page(page, qw);
        if (page1.getTotal() > 0) {
            List<ProjectMessage> records = page1.getRecords();
            records.forEach(projectMessage1 -> projectMessage1.setPublishTime(projectMessage1.getPublishTime().substring(0,10)));
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
