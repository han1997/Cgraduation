package com.example.graduation.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户实体")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    @ApiModelProperty("用户 id")
    private Integer userId;

    /**
     * 用户编号
     */
    @ApiModelProperty("用户 学号")
    private String userNo;

    /**
     * 用户姓名
     */
    @ApiModelProperty("用户 姓名")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty("用户 密码")
    private String userPsd;

    /**
     * 用户角色1管理员2教师3学生
     */
    @ApiModelProperty("用户 角色1管理员2教师3学生")
    private String userRole;

    /**
     * 学生层次
     */
    @ApiModelProperty("用户 层次")
    private String userLevel;

    /**
     * 上次登入时间
     */
    @ApiModelProperty("用户 首次登录时间")
    private String lastLoginTime;

    /**
     * 上次登出时间
     */
    @ApiModelProperty("用户 上次登出时间")
    private String lastLogoutTime;


}
