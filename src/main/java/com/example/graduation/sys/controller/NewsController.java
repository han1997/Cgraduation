package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.News;
import com.example.graduation.sys.service.INewsService;
import com.example.graduation.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
@Api(tags = "新闻相关接口")
@RequestMapping("/sys/news")
public class NewsController {
    @Autowired
    INewsService newsService;

    @GetMapping("/list")
    @ApiOperation(value = "根据输入字段获取所有新闻")
    public AjaxVoResult list(News news) {
        QueryWrapper<News> qw = new QueryWrapper<>(news);
        qw.orderByDesc("release_time");
        List<News> newss = newsService.list(qw);

        if (newss.size() > 0) {
            newss.forEach(news1 -> {
                news1.setReleaseTime(news1.getReleaseTime().substring(0, 10));
            });
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), newss);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    @ApiOperation(value = "根据输入信息添加新闻数据")
    public AjaxVoResult add(News news) {
        /**
         *
         * @description: 新增新闻
         * @param news
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        if (StringUtils.isBlank(news.getReleaseTime())){
            news.setReleaseTime(TimeUtils.getDateTime());
        }
        boolean b = newsService.add(news);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @Transactional
    @PostMapping("/delete")
    @ApiOperation(value = "根据newsId删除新闻数据")
    public AjaxVoResult delete(int[] newsIds) {
        /**
         *
         * @description: 通过projectId删除项目
         * @param projectId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        ArrayList<AjaxVoResult> ajaxVoResults = new ArrayList<>();
        for (int i = 0; i < newsIds.length; i++) {
            int newsId = newsIds[i];
            boolean b = newsService.removeById(newsId);
            if (b) {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "新闻Id：" + newsId + "已被删除"));
            } else {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "新闻Id：" + newsId + "删除出错"));
            }
        }
        return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), ajaxVoResults);
    }

    @PostMapping("/update")
    @ApiOperation(value = "根据输入信息更新新闻数据")
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
    @ApiOperation(value = "根据输入信息获取前11条新闻数据")
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
        qw.orderByDesc("release_time");
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = newsService.page(page, qw);
        List<News> records = page1.getRecords();
        if (page1.getTotal() > 0) {
            records.forEach(news1 -> {
                news1.setReleaseTime(news1.getReleaseTime().substring(0, 10));
            });
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
