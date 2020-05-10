package com.example.graduation.sys.service.impl;

import com.example.graduation.sys.entity.News;
import com.example.graduation.sys.mapper.NewsMapper;
import com.example.graduation.sys.service.INewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduation.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Autowired
    NewsMapper newsMapper;

    @Override
    public boolean add(News news) {
        news.setReleaseTime(TimeUtils.getDateTime());
        int insert = newsMapper.insert(news);
        if (insert == 1){
            return true;
        }
        return false;
    }
}
