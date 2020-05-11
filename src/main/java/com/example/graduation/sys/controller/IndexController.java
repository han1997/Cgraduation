package com.example.graduation.sys.controller;

import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.utils.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: huiyuan
 * @date: 2020/5/8 16:06
 * @desc: 这是描述
 **/
@RestController
@Api(tags = "通用接口")
public class IndexController {
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @PostMapping("/upload")
    @ApiOperation("上传文件接口")
    public AjaxVoResult upload(@RequestBody MultipartFile file){
        String enclosure = "";
        if (file != null && file.getSize() > 0) {
//        如果上传了附件
            AjaxVoResult ajaxVoResult = FileUtils.saveUploadFile(file);
            if (ajaxVoResult.getStatusCode() == 200) {
                String filePath = (String) ajaxVoResult.getDatas();
//                将\转为/保存数据库
                String[] split = filePath.split("/|\\\\");
                filePath = split[0] + "/" + split[1] + "/" + split[2];
                enclosure = "http://35.241.68.51:8080/download?filePath=" + filePath;
                Map<String,String> returnMap = new HashMap<>();
                returnMap.put("fileName",file.getOriginalFilename());
                returnMap.put("enclosure",enclosure);
                return new AjaxVoResult(StatusCode.SUCCESS.getCode(),StatusCode.SUCCESS.getMessage(),returnMap);
            }
        }
        return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(),StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(),"上传文件为空？");
    }

    @GetMapping("/download")
    @ApiOperation("下载文件接口")
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
