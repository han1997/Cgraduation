package com.example.graduation.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 评论/回复
 * </p>
 *
 * @author test
 * @since 2020-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    /**
     * 帖子Id

     */
    private String discussId;

    /**
     * 评论者名字
     */
    private String userName;

    /**
     * 被回复评论id
     */
    private String refCommentId;

    /**
     * 被回复评论者名字
     */
    private String refUserName;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 评论时间
     */
    private String publishTime;


}
