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
import com.example.graduation.utils.TimeUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public AjaxVoResult register(QueryWrapper<User> qw, User user) {
        User userFromDb = getOne(qw);
        if (userFromDb == null) {
//            数据库对应该学号不存在账户
//            密码做加密
            psdEnc(user);
            boolean b = save(user);
            if (b) {
                return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), b);
            }
            return new AjaxVoResult(StatusCode.ADMIN_USER_INSERT_FAILED.getCode(), StatusCode.ADMIN_USER_INSERT_FAILED.getMessage(), b);
        }
        return new AjaxVoResult(StatusCode.USERNO_EXISTS.getCode(), StatusCode.USERNO_EXISTS.getMessage(), null);
    }

    @Override
    public void psdEnc(User user) {
        String hashPsd = "";
        try {
            hashPsd = PasswordStorage.createHash(user.getUserPsd());
        } catch (PasswordStorage.CannotPerformOperationException e) {
            log.error("密码加密错误");
        }
        if (StringUtils.isNotBlank(hashPsd)) {
            user.setUserPsd(hashPsd);
        }
    }

    @Override
    @Transactional
    public AjaxVoResult login(User user, HttpServletRequest request, HttpServletResponse response) {
        QueryWrapper<User> qw = new QueryWrapper<>();
//        使用userno + userrole验证用户
        qw.eq("user_no", user.getUserNo());
        qw.eq("user_role", user.getUserRole());
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
            if (b) {
                ObjectMapper mapper = new ObjectMapper();
//                密码正确，
//                更新数据库登录时间
                userFromDb.setLastLoginTime(TimeUtils.getDateTime());
                System.out.println("------------>上次登录时间：" + userFromDb.getLastLoginTime());
                try {
                    updateById(userFromDb);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AjaxVoResult(StatusCode.ROLE_UPDATE_FAILED.getCode(), StatusCode.ROLE_UPDATE_FAILED.getMessage(), null);
                }
//                写session
                HttpSession session = request.getSession();
                session.setAttribute("user", userFromDb);
                log.info("login_sessionid" + session.getId());

//                返回user信息--去除隐私信息
                userFromDb.setUserId(null);
                userFromDb.setUserPsd("******");
                log.info(userFromDb.toString() + "登录");
                return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), userFromDb);
            }
            return new AjaxVoResult(StatusCode.ADMIN_USER_WRONG_PASSWORD.getCode(), StatusCode.ADMIN_USER_WRONG_PASSWORD.getMessage(), null);
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), null);
    }

    @Override
    public AjaxVoResult logout(HttpServletRequest request,HttpServletResponse response) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            log.info(user.getUserNo()+"请求登出");
//            session保存有user信息，清除session，cookie信息
            session.invalidate();
            CookieUtil.deleteCookie(request,response,"session");
//            写数据库登出时间
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_no", user.getUserNo());
            User userFromdb = getOne(queryWrapper);
            if (userFromdb != null) {
                userFromdb.setLastLogoutTime(TimeUtils.getDateTime());
                updateById(userFromdb);
            } else {
                return new AjaxVoResult(StatusCode.ADMIN_USER_NOT_FOUND.getCode(), StatusCode.ADMIN_USER_NOT_FOUND.getMessage(), null);
            }
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), null);
        }else {
//        session没有user信息，用户未登录
            return new AjaxVoResult(StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getCode(), StatusCode.RESOURCE_NOT_MESSAGE_EXIT.getMessage(), null);
        }
    }

    @Override
    public AjaxVoResult addUser(User user) {
        //        设置默认密码
        user.setUserPsd(user.getUserNo());
//        默认userno不为空，先查询该userno是否已被注册
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_no", user.getUserNo());
        User userFromDb = getOne(queryWrapper);
        boolean b = false;
        if (userFromDb == null) {
//        没有注册，则密码加密，写数据库，返回1
            psdEnc(user);
            try {
                int insert = userMapper.insert(user);
                if (insert == 1) {
                    b = true;
                }
            } catch (Exception e) {
                log.error("写" + user.toString() + "入数据库错误");
                return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "写" + user.toString() + "入数据库错误");
            }
        }else {
            log.error("" + user.getUserNo() + "该用户已经注册");
            return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "" + user.getUserNo() + "该用户已经注册");
        }
//        注册了返回0
        if (b) {
            return new AjaxVoResult(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMessage(), "" + user.getUserNo() + "注册成功");
        }
        return new AjaxVoResult(StatusCode.ERROR.getCode(), StatusCode.ERROR.getMessage(), "" + user.getUserNo() + "注册失败");
    }

}
