package com.example.graduation.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.Course;
import com.example.graduation.sys.mapper.CourseMapper;
import com.example.graduation.sys.service.ICourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程表 服务实现类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired
    CourseMapper courseMapper;

    @Override
    public AjaxVoResult add(Course course) {
//        查询课程编号
        if (StringUtils.isBlank(course.getCourseNo())){
            return new AjaxVoResult(StatusCode.MISSING_REQUIRE_FIELD.getCode(),StatusCode.MISSING_REQUIRE_FIELD.getMessage(),null);
        }
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_no",course.getCourseNo());
        Course courseFromDb = courseMapper.selectOne(queryWrapper);
        if (courseFromDb != null){
//            存在相同课程编号
            return new AjaxVoResult(StatusCode.RESOURCE_IS_EXISTS.getCode(),StatusCode.RESOURCE_IS_EXISTS.getMessage(),null);
        }
        courseMapper.insert(course);
        return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "课程创建成功");
    }
}
