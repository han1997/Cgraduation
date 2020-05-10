package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.News;
import com.example.graduation.sys.service.INewsService;
import com.example.graduation.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
        QueryWrapper<News> qw = new QueryWrapper<>(news);
        qw.orderByDesc("release_time");
        List<News> newss = newsService.list(qw);
        newss.forEach(news1 -> {
            news1.setReleaseTime(news1.getReleaseTime().substring(0, 10));
        });
        if (newss.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), newss);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    public AjaxVoResult add(News news, MultipartFile file) {
        /**
         *
         * @description: 新增用户
         * @param news
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        String enclosure = "";
        if (file != null && file.getSize() > 0) {
//        如果上传了附件
            AjaxVoResult ajaxVoResult = FileUtils.saveUploadFile(file);
            if (ajaxVoResult.getStatusCode() == 200) {
                String filePath = (String) ajaxVoResult.getDatas();
//                将\转为/保存数据库
                String[] split = filePath.split("\\\\" );
                filePath = split[0] + "/" + split[1] + "/" + split[2];
                enclosure = "http://35.241.68.51:8080/sys/news/downloadEnclosure?filePath=" + filePath;
            }
        }
        news.setEnclosure(enclosure);
        boolean b = newsService.add(news);
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

    @RequestMapping("/downloadEnclosure")
    public AjaxVoResult downloadEnclosure(HttpServletRequest request, HttpServletResponse response, String filePath) {
        String[] split = filePath.split("/");
        String fileName = split[2];
        File file = new File(filePath);
        if (file.exists()) {
//            文件存在
            FileUtils.download(file, fileName, request, response);
//            还加return AjaxVoResult 会发生Cannot call sendError() after the response has been committed 错误，因为download方法里面已经将要返回的数据写入response里面了
//            所以return null;
//            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "下载成功");
            return null;
        } else {
            return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), "附件不存在");

        }
    }

}
