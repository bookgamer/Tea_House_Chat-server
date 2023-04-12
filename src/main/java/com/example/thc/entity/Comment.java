package com.example.thc.entity;

import lombok.Data;
//评论内容实体类
@Data
public class Comment {
//    评论唯一id
    private Long id;
//    对应文章的唯一id
    private Long articleId;
//    用户的账户昵称
    private String nickName;
//    用户头像的在线地址
    private String avatarUrl;
//    评论内容
    private String content;
//    评论时间
    private String time;
//评论点赞数
    private Long likes;
//    评论回复数
    private Long replies;
}
