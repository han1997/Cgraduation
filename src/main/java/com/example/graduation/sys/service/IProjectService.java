package com.example.graduation.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduation.sys.entity.Project;

/**
 * <p>
 * 项目信息表 服务类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
public interface IProjectService extends IService<Project> {

    boolean saveProject(Project project);
}
