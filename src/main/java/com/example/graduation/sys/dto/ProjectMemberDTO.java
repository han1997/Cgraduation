package com.example.graduation.sys.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目成员表
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Data
public class ProjectMemberDTO {

    /**
     * id
     */
    private Integer pmId;

    /**
     * 项目id
     */
    private String projectId;

//    项目名称
    private String projectName;

    /**
     * 项目成员id
     */
    private String projectMemberId;

    /**
     * 项目成员职务
     */
    private String projectMemberJob;


}
