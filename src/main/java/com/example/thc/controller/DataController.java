package com.example.thc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.thc.entity.Article;
import com.example.thc.entity.HotArticle;
import com.example.thc.service.ArticleService;
import com.example.thc.service.HotArticleService;
import com.example.thc.util.OkHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/index")
@CrossOrigin
public class DataController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private HotArticleService hotArticleService;

    @GetMapping("getData")
    public ResponseEntity<List<Map<String, Object>>> getData() {
//        每日热门文章
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<HotArticle> list = hotArticleService.list();
        for (HotArticle h:
             list) {
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Article::getId,h.getArticleId());
            Article article = articleService.getOne(queryWrapper);
            Map<String, Object> data1 = new HashMap<>();
            data1.put("src", article.getSrc());
//        论文标题
            data1.put("Title", article.getTitle());
//        简介
            data1.put("Intro", article.getIntro());
//        文章内容和ID
            data1.put("ID",article.getId());
            dataList.add(data1);
        }


        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }
}
