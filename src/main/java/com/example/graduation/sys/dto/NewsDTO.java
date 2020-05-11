package com.example.graduation.sys.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author: huiyuan
 * @date: 2020/5/11 15:26
 * @desc: 这是描述
 **/
@Data
public class NewsDTO {
    /**
     * 新闻id
     */
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

//    文件名
    private String fileName;
}
