package com.example.graduation.sys.service.impl;

import com.example.graduation.sys.entity.Comment;
import com.example.graduation.sys.mapper.CommentMapper;
import com.example.graduation.sys.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论/回复 服务实现类
 * </p>
 *
 * @author test
 * @since 2020-05-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
