package com.example.graduation.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.Course;

/**
 * <p>
 * 课程表 服务类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
public interface ICourseService extends IService<Course> {

    AjaxVoResult add(Course course);
}
