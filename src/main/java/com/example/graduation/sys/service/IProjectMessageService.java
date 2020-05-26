package com.example.graduation.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.graduation.sys.entity.ProjectMessage;

/**
 * <p>
 * 项目消息 服务类
 * </p>
 *
 * @author test
 * @since 2020-05-07
 */
public interface IProjectMessageService extends IService<ProjectMessage> {

    boolean add(ProjectMessage projectMessage);
}
