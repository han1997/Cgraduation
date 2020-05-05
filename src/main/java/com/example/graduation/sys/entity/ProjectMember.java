package com.example.graduation.sys.entity;

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
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProjectMember implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "pm_id", type = IdType.AUTO)
    private Integer pmId;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 项目成员id
     */
    private String projectMemberId;

    /**
     * 项目成员职务
     */
    private String projectMemberJob;


}
