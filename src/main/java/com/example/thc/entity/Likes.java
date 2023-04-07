package com.example.thc.entity;

import lombok.Data;

@Data
public class Likes {
    //    文章的唯一id
    private Long articleId;
    //    用户账号的唯一id
    private Long userId;
}
