package com.example.thc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.thc.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
}
