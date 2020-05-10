package com.example.graduation.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 新闻、通知
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 新闻id
     */
    @TableId(value = "news_id", type = IdType.AUTO)
    private Integer newsId;

    /**
     * 新闻发布人id
     */
    private String newsProvider;

    /**
     * 新闻标题
     */
    private String newsTitle;

    /**
     * 新闻描述
     */
    private String newsDesc;

    /**
     * 新闻类型
     */
    private String newsType;

    /**
     * 发布时间
     */
    private String releaseTime;

    /**
     * 附件
     */
    private String enclosure;


}
