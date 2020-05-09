package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.News;
import com.example.graduation.sys.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 新闻、通知 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/sys/news")
public class NewsController {
    @Autowired
    INewsService newsService;

    @RequestMapping("/list")
    public AjaxVoResult list(News news) {
        Wrapper<News> qw = new QueryWrapper<>(news);
        List<News> newss = newsService.list();
        if (newss.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), newss);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    public AjaxVoResult add(News news) {
        /**
         *
         * @description: 新增用户
         * @param news
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        boolean b = newsService.save(news);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/delete")
    public AjaxVoResult delete(int newsId) {
        /**
         *
         * @description: 通过newsId删除用户
         * @param newsId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        News news = new News();
        news.setNewsId(newsId);
        boolean b = newsService.removeById(newsId);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/update")
    public AjaxVoResult update(News news) {
        /**
         *
         * @description: 通过newsId更新用户信息
         * @param news
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = newsService.updateById(news);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/get")
    public AjaxVoResult get(Page page, News news) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param news
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<News> qw = new QueryWrapper<>(news);
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = newsService.page(page, qw);
        if (page1.getTotal() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
