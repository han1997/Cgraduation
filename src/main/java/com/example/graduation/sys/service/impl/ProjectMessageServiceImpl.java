package com.example.graduation.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.graduation.sys.entity.ProjectMessage;
import com.example.graduation.sys.mapper.ProjectMessageMapper;
import com.example.graduation.sys.service.IProjectMessageService;
import com.example.graduation.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目消息 服务实现类
 * </p>
 *
 * @author test
 * @since 2020-05-07
 */
@Service
public class ProjectMessageServiceImpl extends ServiceImpl<ProjectMessageMapper, ProjectMessage> implements IProjectMessageService {
    @Autowired
    ProjectMessageMapper projectMessageMapper;

    @Override
    public boolean add(ProjectMessage projectMessage) {
        projectMessage.setPublishTime(TimeUtils.getDateTime());
        int insert = projectMessageMapper.insert(projectMessage);
        if (insert == 1){
            return true;
        }
        return false;
    }
}
