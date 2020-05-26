package com.example.graduation.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduation.sys.entity.Project;
import com.example.graduation.sys.entity.ProjectMember;
import com.example.graduation.sys.mapper.ProjectMapper;
import com.example.graduation.sys.service.IProjectMemberService;
import com.example.graduation.sys.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 项目信息表 服务实现类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements IProjectService {

    @Autowired
    IProjectMemberService projectMemberService;

    @Override
    @Transactional
    public boolean saveProject(Project project) {
        boolean b = save(project);
        ProjectMember projectMember = new ProjectMember();
        projectMember.setProjectId(project.getProjectId().toString());
        projectMember.setProjectMemberId(project.getProjectOwner());
        boolean b1 = projectMemberService.save(projectMember);
        if (b & b1) {
            return true;
        } else {
            return false;
        }
    }
}
