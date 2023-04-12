package com.example.thc.entity;

import lombok.Data;

@Data
public class Collect {
    private Integer id;
//    用户账号的唯一id
    private Long userId;
//    文章的唯一id
    private Long articleId;
}
