package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.InternationalCooperation;
import com.example.graduation.sys.service.IInternationalCooperationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * 国际合作 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@RestController
@RequestMapping("/sys/international-cooperation")
@Api(tags = "国际合作相关接口")
public class InternationalCooperationController {
    @Autowired
    IInternationalCooperationService internationalCooperationService;

    @GetMapping("/list")
    @ApiOperation(value = "获取所有国际合作项目信息")
    public AjaxVoResult list(InternationalCooperation internationalCooperation) {
        QueryWrapper<InternationalCooperation> qw = new QueryWrapper<>(internationalCooperation);
        List<InternationalCooperation> internationalCooperations = internationalCooperationService.list(qw);
        if (internationalCooperations.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), internationalCooperations);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增国际合作项目信息")
    public AjaxVoResult add(InternationalCooperation internationalCooperation) {
        /**
         *
         * @description: 新增用户
         * @param internationalCooperation
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        boolean b = internationalCooperationService.save(internationalCooperation);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @Transactional
    @PostMapping("/delete")
    @ApiOperation(value = "删除国际合作项目信息")
    public AjaxVoResult delete(int[] internationalCooperationIds) {
        /**
         *
         * @description: 通过internationalCooperationId删除用户
         * @param internationalCooperationId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        ArrayList<AjaxVoResult> ajaxVoResults = new ArrayList<>();
        for (int i = 0; i < internationalCooperationIds.length; i++) {
            int internationalCooperationId = internationalCooperationIds[i];
            boolean b = internationalCooperationService.removeById(internationalCooperationId);
            if (b) {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "国际合作Id：" + internationalCooperationId + "已被删除"));
            } else {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "国际合作Id：" + internationalCooperationId + "删除出错"));
            }
        }
        return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), ajaxVoResults);
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新国际合作项目信息")
    public AjaxVoResult update(InternationalCooperation internationalCooperation) {
        /**
         *
         * @description: 通过internationalCooperationId更新用户信息
         * @param internationalCooperation
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = internationalCooperationService.updateById(internationalCooperation);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/get")
    @ApiOperation(value = "获取前11条国际合作项目信息")
    public AjaxVoResult get(Page page, InternationalCooperation internationalCooperation) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param internationalCooperation
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<InternationalCooperation> qw = new QueryWrapper<>(internationalCooperation);
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = internationalCooperationService.page(page, qw);
        if (page1.getTotal() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
