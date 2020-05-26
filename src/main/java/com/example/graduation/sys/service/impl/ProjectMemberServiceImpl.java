package com.example.graduation.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.dto.ProjectMemberDTO;
import com.example.graduation.sys.entity.ProjectMember;
import com.example.graduation.sys.mapper.ProjectMemberMapper;
import com.example.graduation.sys.service.IProjectMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 项目成员表 服务实现类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Service
public class ProjectMemberServiceImpl extends ServiceImpl<ProjectMemberMapper, ProjectMember> implements IProjectMemberService {
    @Autowired
    ProjectMemberMapper projectMemberMapper;

    @Override
    public AjaxVoResult getProject(String projectMemberId) {
        List<ProjectMemberDTO> projectByUserNo = projectMemberMapper.getProjectByUserNo(projectMemberId);
        if (!CollectionUtils.isEmpty(projectByUserNo)){
//            有查询结果
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMessage(),projectByUserNo);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(),StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(),null);
    }
}
