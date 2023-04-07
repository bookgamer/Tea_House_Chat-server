package com.example.thc.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.thc.common.R;
import com.example.thc.entity.User;
import com.example.thc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public R<String> login(@RequestBody User user){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAvatarUrl,user.getAvatarUrl());
        User one1 = userService.getOne(queryWrapper);
        if(one1!=null){
            return R.error("登录的账号已存在！！！，如持续无法解决。请联系开发人员");
        }
//        用户登录
       try{
           userService.save(user);
           LambdaQueryWrapper<User> queryWrapper1 = new LambdaQueryWrapper<>();
           queryWrapper1.eq(User::getAvatarUrl,user.getAvatarUrl());
           User one = userService.getOne(queryWrapper1);
           return R.success((one.getId()).toString());
       }catch (Exception err){
           log.info("登录接口报错："+err);
           return R.error("程序未知错误，请稍后重试");
       }
    }
}