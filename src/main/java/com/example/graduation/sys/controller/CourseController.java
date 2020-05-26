package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.Course;
import com.example.graduation.sys.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 课程表 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/sys/course")
@Api(tags = "课程相关接口")
public class CourseController {
    @Autowired
    ICourseService courseService;

    @GetMapping("/list")
    @ApiOperation(value = "获取所有课程信息接口")
    public AjaxVoResult list(Course course) {
        QueryWrapper<Course> qw = new QueryWrapper<>(course);
        qw.orderByDesc("course_time");
        List<Course> courses = courseService.list(qw);
        if (courses.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), courses);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增课程接口")
    public AjaxVoResult add(Course course) {
        /**
         *
         * @description: 新增用户
         * @param course
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        return courseService.add(course);
    }

    @Transactional
    @PostMapping("/delete")
    @ApiOperation(value = "删除课程接口")
    public AjaxVoResult delete(int[] courseIds) {
        /**
         *
         * @description: 通过courseId删除用户
         * @param courseIds
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        ArrayList<AjaxVoResult> ajaxVoResults = new ArrayList<>();
        for (int i = 0; i < courseIds.length; i++) {
            int courseId = courseIds[i];
            boolean b = courseService.removeById(courseId);
            if (b) {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "课程Id：" + courseId + "已被删除"));
            } else {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "课程Id：" + courseId + "删除出错"));
            }
        }
        return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), ajaxVoResults);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新课程接口")
    public AjaxVoResult update(Course course) {
        /**
         *
         * @description: 通过courseId更新用户信息
         * @param course
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = courseService.updateById(course);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/get")
    @ApiOperation(value = "获取前11个课程接口")
    public AjaxVoResult get(Page page, Course course) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param course
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<Course> qw = new QueryWrapper<>(course);
        qw.orderByDesc("course_time");
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = courseService.page(page, qw);
        if (page1.getTotal() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
