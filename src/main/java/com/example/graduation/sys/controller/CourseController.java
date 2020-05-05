package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.Course;
import com.example.graduation.sys.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class CourseController {
    @Autowired
    ICourseService courseService;

    @RequestMapping("/list/{currPage}/{size}")
    public AjaxVoResult list(@PathVariable int currPage, @PathVariable int size, Course course) {
        final Page<Course> page = new Page<Course>(currPage, size);
        Wrapper<Course> qw = new QueryWrapper<>(course);
        Page users = null;
        if (qw == null){
            users = courseService.page(page);
        }else {
            users = courseService.page(page,qw);
        }
        if (users.getRecords().size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), users);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    public AjaxVoResult add(@RequestBody Course course) {
        /**
         *
         * @description: 新增用户
         * @param course
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        boolean b = courseService.save(course);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/delete")
    public AjaxVoResult delete(int courseId) {
        /**
         *
         * @description: 通过courseId删除用户
         * @param courseId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        Course user = new Course();
        user.setCourseId(courseId);
        boolean b = courseService.removeById(courseId);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/update")
    public AjaxVoResult update(@RequestBody Course course) {
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
    public AjaxVoResult get(Page page,Course course){
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param course
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<Course> qw = new QueryWrapper<>(course);
        if (page == null){
            Page<Course> userPage = new Page<>(1,5);
        }
        Page page1 = courseService.page(page, qw);
        if (page1.getTotal() > 0){
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
