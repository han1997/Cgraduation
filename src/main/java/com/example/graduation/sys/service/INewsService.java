package com.example.graduation.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduation.sys.entity.News;

/**
 * <p>
 * 新闻、通知 服务类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
public interface INewsService extends IService<News> {

    boolean add(News news);
}
