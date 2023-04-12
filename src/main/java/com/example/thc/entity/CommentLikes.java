package com.example.thc.entity;

import lombok.Data;

/*
评论点赞表，如果表中有数据表示用户已经点赞这条评论，如果没有就没有点赞
 */
@Data
public class CommentLikes {
//    评论点赞表唯一id
    private Integer id;
//  用户账号的唯一id
    private Long userId;
//    评论id
    private Long commentId;
}
