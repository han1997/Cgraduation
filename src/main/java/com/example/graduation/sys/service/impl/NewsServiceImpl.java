package com.example.graduation.sys.service.impl;

import com.example.graduation.sys.entity.News;
import com.example.graduation.sys.mapper.NewsMapper;
import com.example.graduation.sys.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 新闻、通知 服务实现类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

}
