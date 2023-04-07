package com.example.thc.entity;

import lombok.Data;

@Data
public class Article {
//    文章的唯一id
    private Long id;
//    文章的标题
    private String title;
//    文章的大概内容简介
    private String intro;
//    文章封面
    private String src;
//    文章内容
    private String content;
//    文章内容一一个字段是否可以填充 0表示可以，1表示不可以 默认是0
    private Long noContent;
//  文章剩余内容 1
    private String restAcontent;
//    文章剩余内容 2
    private String restBcontent;
//    文章剩余内容 3
    private String restCcontent;
//    文章是否公开 0公开 1不公开
    private Long publics;
}
