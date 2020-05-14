package com.example.graduation.sys.mapper;

import com.example.graduation.sys.dto.ProjectMemberDTO;
import com.example.graduation.sys.entity.ProjectMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 项目成员表 Mapper 接口
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
public interface ProjectMemberMapper extends BaseMapper<ProjectMember> {
    @Select("select pm.pm_id,pm.project_id,pm.project_member_id,p.project_name,p.project_psd,project_desc,u.user_name as project_owner,p.project_desc from project_member pm,project p,user u where pm.project_id = p.project_id and pm.project_member_id = #{projectMemberId} and p.project_owner = u.user_no;")
    List<ProjectMemberDTO> getProjectByUserNo(@Param("projectMemberId") String projectMemberId);

}
