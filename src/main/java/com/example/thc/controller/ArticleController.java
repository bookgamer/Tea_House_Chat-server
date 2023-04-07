package com.example.thc.controller;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.thc.common.R;
import com.example.thc.entity.Article;
import com.example.thc.entity.Comment;
import com.example.thc.entity.Likes;
import com.example.thc.entity.User;
import com.example.thc.service.ArticleService;
import com.example.thc.service.CommentService;
import com.example.thc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/article")
    public Article getArticle(@RequestParam("id") Long id) {
        // 根据文章id从文章数据中获取文章内容
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, id);
        Article one = articleService.getOne(queryWrapper);
        return one;
    }

    //    添加评论内容到数据库
    @PostMapping("/Addcomments")
    public boolean addComments(@RequestBody Comment comment, @RequestParam("userId") Long userId) {
//        根据账号的id，拿到用户的头像和昵称
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,userId);
        User userInfo = userService.getOne(queryWrapper);
        comment.setNickName(userInfo.getNickName());
        comment.setAvatarUrl(userInfo.getAvatarUrl());
//        添加内容到数据库
        commentService.save(comment);
        return true;
    }

    @GetMapping("/comments")
    public String getComments(@RequestParam("id") Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId,id);
        List<Comment> commentList = commentService.list(queryWrapper);

        JSONArray jsonArray = new JSONArray();
        for (Comment comment : commentList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", comment.getId());
            jsonObject.put("articleId", comment.getArticleId());
            jsonObject.put("avatarUrl", comment.getAvatarUrl());
            jsonObject.put("nickName", comment.getNickName());
            jsonObject.put("content",comment.getContent());
            jsonObject.put("time",comment.getTime());
            jsonObject.put("likes",comment.getLikes());
            jsonObject.put("replies",comment.getReplies());
            // 其他属性同理
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

    @PostMapping("/like")
    public boolean like(@RequestBody Likes likes) {
        System.out.println(likes);
        // 根据文章id将点赞数+1
        // 省略具体实现
        return true;
    }

    @PostMapping("/collect")
    public boolean collect(@RequestBody(required = false) String id) {
        if (id != null) {
            // 根据文章id设置为已收藏
            // 省略具体实现
            return true;
        } else {
            return false;
        }
    }

}