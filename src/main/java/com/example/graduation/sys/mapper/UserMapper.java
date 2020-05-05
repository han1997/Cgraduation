package com.example.graduation.sys.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.graduation.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author test
 * @since 2020-05-04
 */
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT user_id,user_no,user_name,user_psd,user_role,user_level,last_login_time,last_logout_time FROM user ${ew.customSqlSegment}")
//    IPage<User> selectPageVo(IPage<User> page);
    IPage<User> selectPageVo(IPage<User> page ,@Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}
