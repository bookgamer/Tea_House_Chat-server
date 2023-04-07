package com.example.thc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.thc.entity.Likes;
import com.example.thc.mapper.LikesMapper;
import com.example.thc.service.LikesService;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements LikesService {
}
