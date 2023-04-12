package com.example.thc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.thc.entity.CommentLikes;
import com.example.thc.mapper.CommentLikesMapper;
import com.example.thc.service.CommentLikesService;
import org.springframework.stereotype.Service;

@Service
public class CommentLikesServiceImpl extends ServiceImpl<CommentLikesMapper, CommentLikes> implements CommentLikesService {
}
