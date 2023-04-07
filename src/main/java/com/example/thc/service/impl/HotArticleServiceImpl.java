package com.example.thc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.thc.entity.HotArticle;
import com.example.thc.mapper.HotArticleMapper;
import com.example.thc.service.HotArticleService;
import org.springframework.stereotype.Service;

@Service
public class HotArticleServiceImpl extends ServiceImpl<HotArticleMapper, HotArticle> implements HotArticleService {
}
