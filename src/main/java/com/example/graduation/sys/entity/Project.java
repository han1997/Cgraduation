package com.example.graduation.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目信息表
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目id
     */
    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;

    /**
     * 项目名
     */
    private String projectName;

    /**
     * 项目所有人id
     */
    private String projectOwner;

    /**
     * 项目描述
     */
    private String projectDesc;


}
