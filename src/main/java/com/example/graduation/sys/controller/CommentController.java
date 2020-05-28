package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.Comment;
import com.example.graduation.sys.service.ICommentService;
import com.example.graduation.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
 * 评论/回复 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-23
 */
@RestController
@Api(tags = "评论回复相关接口")
@RequestMapping("/sys/comment")
public class CommentController {
    @Autowired
    ICommentService commentService;

    @GetMapping("/list")
    @ApiOperation(value = "获取评论接口")
    public AjaxVoResult list(Comment comment) {
        QueryWrapper<Comment> qw = new QueryWrapper<>(comment);
        qw.orderByDesc("publish_time");
        List<Comment> comments = commentService.list(qw);
        if (comments.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), comments);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增评论接口")
    public AjaxVoResult add(Comment comment) {
        /**
         *
         * @description: 新增用户
         * @param comment
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        if (StringUtils.isBlank(comment.getPublishTime())){
            comment.setPublishTime(TimeUtils.getDateTime());
        }
        boolean save = commentService.save(comment);
        if (save){
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), comment);
        }else {
            return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
        }
    }

    @Transactional
    @PostMapping("/delete")
    @ApiOperation(value = "删除评论接口")
    public AjaxVoResult delete(int[] commentIds) {
        /**
         *
         * @description: 通过commentId删除用户
         * @param commentIds
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        ArrayList<AjaxVoResult> ajaxVoResults = new ArrayList<>();
        for (int i = 0; i < commentIds.length; i++) {
            int commentId = commentIds[i];
            boolean b = commentService.removeById(commentId);
            if (b) {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "评论Id：" + commentId + "已被删除"));
            } else {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "评论Id：" + commentId + "删除出错"));
            }
        }
        return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), ajaxVoResults);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新评论接口")
    public AjaxVoResult update(Comment comment) {
        /**
         *
         * @description: 通过commentId更新用户信息
         * @param comment
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = commentService.updateById(comment);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/get")
    @ApiOperation(value = "获取前11个评论接口")
    public AjaxVoResult get(Page page, Comment comment) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param comment
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<Comment> qw = new QueryWrapper<>(comment);
        qw.orderByDesc("publish_time");
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = commentService.page(page, qw);
        if (page1.getTotal() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
