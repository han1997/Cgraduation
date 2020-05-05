package com.example.graduation.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduation.constants.StatusCode;
import com.example.graduation.sys.dto.AjaxVoResult;
import com.example.graduation.sys.entity.User;
import com.example.graduation.sys.mapper.UserMapper;
import com.example.graduation.sys.service.IUserService;
import com.example.graduation.utils.CookieUtil;
import com.example.graduation.utils.JwtUtil;
import com.example.graduation.utils.PasswordStorage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public AjaxVoResult register(QueryWrapper<User> qw, User user) {
        User userFromDb = getOne(qw);
        if (userFromDb == null) {
//            密码做加密
            String hashPsd = "";
            try {
                hashPsd = PasswordStorage.createHash(user.getUserPsd());
            } catch (PasswordStorage.CannotPerformOperationException e) {
                e.printStackTrace();
            }
            if (StringUtils.isNotBlank(hashPsd)) {
                user.setUserPsd(hashPsd);
            }
            boolean b = save(user);
            if (b) {
                return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
            }
            return new AjaxVoResult(StatusCode.ADMIN_USER_INSERT_FAILED.getCode(), StatusCode.ADMIN_USER_INSERT_FAILED.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.USERNO_EXISTS.getCode(), StatusCode.USERNO_EXISTS.getMessage(), null);
    }

    @Override
    public AjaxVoResult login(User user, HttpServletRequest request, HttpServletResponse response) {
        final QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("user_no", user.getUserNo());
        User userFromDb = getOne(qw);
        if (userFromDb != null) {
//            该学号创建了用户帐号验证密码
            boolean b = false;
            try {
                b = PasswordStorage.verifyPassword(user.getUserPsd(), userFromDb.getUserPsd());
            } catch (PasswordStorage.CannotPerformOperationException e) {
                e.printStackTrace();
            } catch (PasswordStorage.InvalidHashException e) {
                e.printStackTrace();
            }
            if (b){
                ObjectMapper mapper = new ObjectMapper();
//                密码正确，写session

                return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
            }
            return new AjaxVoResult(StatusCode.ADMIN_USER_WRONG_PASSWORD.getCode(),StatusCode.ADMIN_USER_WRONG_PASSWORD.getMessage(),null);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }
}
