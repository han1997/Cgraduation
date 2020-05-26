package com.example.graduation.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.ProjectMember;

/**
 * <p>
 * 项目成员表 服务类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
public interface IProjectMemberService extends IService<ProjectMember> {

    AjaxVoResult getProject(String projectMemberId);
}
