package com.example.graduation.sys.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.UploadFile;
import com.example.graduation.sys.service.IUploadFileService;
import com.example.graduation.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
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
 * 下载文件 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-26
 */
@RestController
@Slf4j
@Api(tags = "下载文件相关接口")
@RequestMapping("/sys/upload-file")
public class UploadFileController {
    @Autowired
    IUploadFileService uploadFileService;

    @GetMapping("/list")
    @ApiOperation(value = "根据输入字段获取所有下载文件")
    public AjaxVoResult list(UploadFile uploadFile) {
        QueryWrapper<UploadFile> qw = new QueryWrapper<>(uploadFile);
        List<UploadFile> uploadFiles = uploadFileService.list(qw);
        if (uploadFiles.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), uploadFiles);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

    @PostMapping("/add")
    @ApiOperation(value = "根据输入信息添加下载文件数据")
    public AjaxVoResult add(UploadFile uploadFile) {
        /**
         *
         * @description: 新增下载文件
         * @param uploadFile
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        List<AjaxVoResult> ajaxVoResults = new ArrayList<>();
        String fileAddrs = uploadFile.getFileAddr();
        if (StringUtils.isBlank(fileAddrs)) {
            return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "地址为空，无法保存数据");
        }
//        获取所有文件
        addUploadFile(ajaxVoResults, fileAddrs);
        return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), ajaxVoResults);
    }

    private void addUploadFile(List<AjaxVoResult> ajaxVoResults, String fileAddrs) {
        List<UploadFile> uploadFiles = new ArrayList<>();
        try {
            String[] split = fileAddrs.split("\\|");
            for (int i = 0; i < split.length; i++) {
                UploadFile uploadFile1 = new UploadFile();
                uploadFile1.setFileAddr(split[i]);
                String[] split1 = split[i].split("-");
                uploadFile1.setFileName(split1[1]);
                uploadFiles.add(uploadFile1);
            }
        } catch (Exception e) {
            log.info("文件名分割出错");
            e.printStackTrace();
        }
        uploadFiles.forEach(uploadFile1 -> {
//            保存文件
            boolean save = uploadFileService.save(uploadFile1);
            AjaxVoResult ajaxVoResult = null;
            if (save) {
                ajaxVoResult = new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), uploadFile1.getFileName() + "保存成功");
            }else {
                ajaxVoResult = new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), uploadFile1.getFileName() + "保存失败");
            }
            ajaxVoResults.add(ajaxVoResult);
        });
    }

    @Transactional
    @PostMapping("/delete")
    @ApiOperation(value = "根据uploadFileId删除下载文件数据")
    public AjaxVoResult delete(int[] uploadFileIds) {
        /**
         *
         * @description: 通过projectId删除项目
         * @param projectId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        ArrayList<AjaxVoResult> ajaxVoResults = new ArrayList<>();
        for (int i = 0; i < uploadFileIds.length; i++) {
            int uploadFileId = uploadFileIds[i];
            boolean b = uploadFileService.removeById(uploadFileId);
            if (b) {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "下载文件Id：" + uploadFileId + "已被删除"));
            } else {
                ajaxVoResults.add(new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "下载文件Id：" + uploadFileId + "删除出错"));
            }
        }
        return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), ajaxVoResults);
    }

    @PostMapping("/update")
    @ApiOperation(value = "根据输入信息更新下载文件数据")
    public AjaxVoResult update(UploadFile uploadFile) {
        /**
         *
         * @description: 通过uploadFileId更新用户信息
         * @param uploadFile
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        boolean b = uploadFileService.updateById(uploadFile);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @PostMapping("/get")
    @ApiOperation(value = "根据输入信息获取前11条下载文件数据")
    public AjaxVoResult get(Page page, UploadFile uploadFile) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param uploadFile
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<UploadFile> qw = new QueryWrapper<>(uploadFile);
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = uploadFileService.page(page, qw);
        List<UploadFile> records = page1.getRecords();
        if (page1.getTotal() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
    }

}
