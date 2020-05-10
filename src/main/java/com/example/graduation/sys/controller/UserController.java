package com.example.graduation.sys.controller;


import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.util.PoiPublicUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.annotations.LoginRequire;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.service.IUserService;
import com.example.graduation.utils.FileUtils;
import com.example.graduation.utils.PasswordStorage;
import com.example.graduation.utils.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Slf4j
@Api(tags = "用户相关接口", description = "提供用户相关的 Rest API")
@RestController
@RequestMapping("/sys/user")
public class UserController {
    @Autowired
    IUserService userService;

    @ApiOperation("管理员查询所有用户接口")
    @RequestMapping("/list")
    public AjaxVoResult list(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>(user);
        List<User> users = userService.list(qw);
        if (users.size() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), users);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @ApiOperation("管理员新增用户接口")
    @PostMapping("/add")
    public AjaxVoResult add(User user) {
        /**
         *
         * @description: 新增用户
         * @param user
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 4:12 下午
         */
        return userService.addUser(user);

    }

    @ApiOperation("管理员删除用户接口")
    @PostMapping("/delete")
    public AjaxVoResult delete(int userId) {
        /**
         *
         * @description: 通过userId删除用户
         * @param userId
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        User user = new User();
        user.setUserId(userId);
        boolean b = userService.removeById(userId);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @ApiOperation("更新用户接口")
    @PostMapping("/update")
    public AjaxVoResult update(User user) {
        /**
         *
         * @description: 通过userId更新用户信息
         * @param user
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        userService.psdEnc(user);
        boolean b = userService.updateById(user);
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @ApiOperation("获取前11个用户信息接口")
    @PostMapping("/get")
    public AjaxVoResult get(Page page, User user) {
        /**
         *
         * @description: 条件查询用户
         * @param page
         * @param user
         * @return: com.example.graduation.sys.dto.AjaxVoResult
         * @time: 2020/5/5 5:18 下午
         */
        QueryWrapper<User> qw = new QueryWrapper<>(user);
        if (page == null) {
            page = new Page<>(1, 11);
        }
        page.setSize(11L);
        Page page1 = userService.page(page, qw);
        if (page1.getTotal() > 0) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), page1);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @ApiOperation("用户注册接口")
    @PostMapping("/register")
    public AjaxVoResult register(User user) {
        QueryWrapper<User> qw = new QueryWrapper<>();
//        学号唯一，作为匹配
        qw.eq("user_no", user.getUserNo());
        user.setUserPsd(user.getUserNo());
        return userService.register(qw, user);
    }

    @ApiOperation("用户登录接口")
    @PostMapping("/login")
    public AjaxVoResult login(User user, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(user.getUserRole())) {
//            如果没有选用户角色，默认学生
            user.setUserRole("3");
        }
        return userService.login(user, request, response);
    }

    @ApiOperation("用户注销接口")
    @RequestMapping("/logout")
    public AjaxVoResult logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logout(request, response);
    }

    @ApiOperation("管理员Excel导入接口")
    @RequestMapping("/importExcel")
    public AjaxVoResult importExcel(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) {
//        判断上传文件格式
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        if (!"xls".equals(split[split.length -1]) || !"xlsx".equals(split[split.length -1])){
            return new AjaxVoResult(StatusCode.RESOURCE_TYPE_ERROR.getCode(), StatusCode.RESOURCE_TYPE_ERROR.getMessage(), "Excel导入出错");
        }

        File uploadExcel = new File(originalFilename);

        if (file != null && file.getSize() > 0) {
            try (FileOutputStream fileOutputStream = new FileOutputStream(uploadExcel)) {
                fileOutputStream.write(file.getBytes());
                fileOutputStream.flush();
            } catch (FileNotFoundException e) {
                log.error("文件没有找到");
                return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "Excel导入出错");
            } catch (IOException e) {
                log.error("MultipartFile文件转化类型出错");
                return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "Excel导入出错");
            }
        }
        //        使用easypoi从excel文件读取数据
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<User> list = ExcelImportUtil.importExcel(uploadExcel, User.class, params);
        ArrayList<AjaxVoResult> ajaxVoResults = new ArrayList<>();
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(user -> {
                AjaxVoResult ajaxVoResult = userService.addUser(user);
                ajaxVoResults.add(ajaxVoResult);
            });
            uploadExcel.delete();
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), ajaxVoResults);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "Excel导入出错");

    }

    @ApiOperation("管理员Excel下载接口")
    @RequestMapping("/downloadExcel")
    public AjaxVoResult downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<User> users = userService.list();
        String fileName = users.get(0).getClass().getSimpleName() + "-" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) + ".xls";
        File tem = new File(fileName);
        try (FileOutputStream out = new FileOutputStream(tem);
        ) {
            //			转化excel
            Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("用户表", "1"),
                    User.class, users);
            workbook.write(out);
            workbook.close();
            workbook = null;
//            写入响应
            FileUtils.download(tem, fileName, request, response);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }



}
