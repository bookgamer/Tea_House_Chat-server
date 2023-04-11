package com.example.thc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
public class Likes {
    private Integer id;
    //    文章的唯一id
    private Long articleId;
    //    用户账号的唯一id
    private Long userId;
}
