package com.example.thc.entity;

import lombok.Data;

@Data
public class User {
    //    用户账号信息的唯一id
    private Long id;
    //    用户头像的在线地址
    private String avatarUrl;
    //    用户账号昵称
    private String nickName;
    //    用户性别
    private Long gender;
}
