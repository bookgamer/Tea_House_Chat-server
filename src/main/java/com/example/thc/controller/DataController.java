package com.example.thc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.thc.entity.Article;
import com.example.thc.service.ArticleService;
import com.example.thc.service.HotArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        Map<String, Object> data1 = new HashMap<>();
        data1.put("src", "https://imgapi.xl0408.top/index.php");
//        论文标题
        data1.put("Title", "java是什么");
//        简介
        data1.put("Intro", "java是啥");
//        文章内容和ID
        data1.put("ID",1);
        Map<String, Object> data2 = new HashMap<>();
        data2.put("src", "https://img.xjh.me/random_img.php?return=302");
        data2.put("Title", "java的背景");
        data2.put("Intro", "详细适合新手看");
        data2.put("ID",2);
        Map<String, Object> data3 = new HashMap<>();
        data3.put("src", "https://img.xjh.me/random_img.php?return=302");
        data3.put("Title", "spring boot2.0项目实战开发");
        data3.put("Intro", "熟练使用spring boot2.0框架，进行项目实战");
        data3.put("ID",1);
        Map<String, Object> data4 = new HashMap<>();
        data4.put("src", "https://img.xjh.me/random_img.php?return=302");
        data4.put("Title", "spring boot4.0项目实战开发");
        data4.put("Intro", "熟练使用spring boot4.0框架，进行项目实战");
        data4.put("ID",1);
        Map<String, Object> data5 = new HashMap<>();
        data5.put("src", "https://img.xjh.me/random_img.php?return=302");
        data5.put("Title", "spring boot5.0项目实战开发");
        data5.put("Intro", "熟练使用spring boot5.0框架，进行项目实战");
        data5.put("ID",1);
        List<Map<String, Object>> dataList = new ArrayList<>();
        dataList.add(data1);
        dataList.add(data2);
        dataList.add(data3);
        dataList.add(data4);
        dataList.add(data5);
        return new ResponseEntity<>(dataList, HttpStatus.OK);
    }
}
