package com.example.graduation.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目消息
 * </p>
 *
 * @author test
 * @since 2020-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProjectMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pm_id", type = IdType.AUTO)
    private Integer pmId;

    /**
     * 项目id
     */
    private String projectId;

    /**
     * 发布人编号
     */
    private String providerId;

    /**
     * 被回复的人编号
     */
    private String refUserId;

    /**
     * 消息标题
     */
    private String messageTitle;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 消息类型1通知2会议记录3讨论
     */
    private String messageType;

    /**
     * 发布时间
     */
    private String publishTime;


}
