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
import com.example.thc.service.LikesService;
import com.example.thc.service.UserService;
import com.example.thc.util.OkHttp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private LikesService likesService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/article")
    public Article getArticle(@RequestParam("id") Long id) {
        // 根据文章id从文章数据中获取文章内容
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getId, id);
        Article article = articleService.getOne(queryWrapper);
        if(article.getNoContent()==0){
            return article;
        }
//        表示内容存储在多个字段，将这些字段内容进行拼接
        String content = article.getContent();
        String restAcontent = article.getRestAcontent();
        String restBcontent = article.getRestBcontent();
        String restCcontent = article.getRestCcontent();
        article.setContent(content+restAcontent+restBcontent+restCcontent);
        return article;
    }

    /*
    添加文章
     */
    @PostMapping("/addArticle")
    public R<String> addArticle(@RequestBody Article article) {
//        调用第三方api，获得随机封面，存储数据库
        try {
            OkHttp okHttp = new OkHttp();
            String src = okHttp.run("https://api.vvhan.com/api/acgimg?type=json");
//          请求返回的数据截取出来
            JSONObject object = JSONObject.parseObject(src);
            String substring = object.getString("imgurl");
//            String img = substring.substring(2);
            article.setSrc(substring);
//            检查文章数字的多少
            String allContent = article.getContent();
            if(allContent.length()>255&&allContent.length()<=510){
//                将文章内容分批存储进数据库，避免数据库字段过载报错
                String content = allContent.substring(0, 255);
                article.setContent(content);
                String contentA = allContent.substring(255);
                article.setRestAcontent(contentA);
                article.setNoContent(1L);
            } else if (allContent.length()>255&&allContent.length()<=765) {
//                将文章内容分批存储进数据库，避免数据库字段过载报错
                String content = allContent.substring(0, 255);
                article.setContent(content);
                String contentA = allContent.substring(255,510);
                article.setRestAcontent(contentA);
                String contentB = allContent.substring(510);
                article.setRestBcontent(contentB);
                article.setNoContent(1L);
            }else if(allContent.length()>255&&allContent.length()<=1020){
//                将文章内容分批存储进数据库，避免数据库字段过载报错
                String content = allContent.substring(0, 255);
                article.setContent(content);
                String contentA = allContent.substring(255,510);
                article.setRestAcontent(contentA);
                String contentB = allContent.substring(510,765);
                article.setRestBcontent(contentB);
                String contentC = allContent.substring(765);
                article.setRestCcontent(contentC);
                article.setNoContent(1L);
            }
            articleService.save(article);
            return R.success("发布成功");
        } catch (Exception err) {
            return R.error(err.toString());
        }
    }

    //    添加评论内容到数据库
    @PostMapping("/Addcomments")
    public boolean addComments(@RequestBody Comment comment, @RequestParam("userId") Long userId) {
//        根据账号的id，拿到用户的头像和昵称
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId, userId);
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
        queryWrapper.eq(Comment::getArticleId, id);
        List<Comment> commentList = commentService.list(queryWrapper);

        JSONArray jsonArray = new JSONArray();
        for (Comment comment : commentList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", comment.getId());
            jsonObject.put("articleId", comment.getArticleId());
            jsonObject.put("avatarUrl", comment.getAvatarUrl());
            jsonObject.put("nickName", comment.getNickName());
            jsonObject.put("content", comment.getContent());
            jsonObject.put("time", comment.getTime());
            jsonObject.put("likes", comment.getLikes());
            jsonObject.put("replies", comment.getReplies());
            // 其他属性同理
            jsonArray.add(jsonObject);
        }

        return jsonArray.toString();
    }

//    查询点赞
    @GetMapping("/like")
    public boolean getlike(@RequestParam Long articleId,@RequestParam Long userId){
        Likes likes = new Likes();
        likes.setArticleId(articleId);
        likes.setUserId(userId);
        LambdaQueryWrapper<Likes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Likes::getArticleId,likes.getArticleId());
        lambdaQueryWrapper.eq(Likes::getUserId,likes.getUserId());
        log.info("查询点赞表，查找当前用户是否点赞");
        Likes likesServiceOne = likesService.getOne(lambdaQueryWrapper);
        if(likesServiceOne==null){
//            表示没有点赞
            return false;
        }else{
//            表示已经点赞
            return true;
        }
    }
//    进行点赞
    @PostMapping("/like")
    public boolean setlike(@RequestBody Likes likes) {
        LambdaQueryWrapper<Likes> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Likes::getArticleId,likes.getArticleId());
        lambdaQueryWrapper.eq(Likes::getUserId,likes.getUserId());
        log.info("查询点赞表，查找用户是否点赞");
        Likes likesServiceOne = likesService.getOne(lambdaQueryWrapper);
        if(likesServiceOne==null){
            // 根据文章id将点赞数+1
            log.info("用户没有点赞，点赞数加一");
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Article::getId,likes.getArticleId());
            Article article = articleService.getOne(queryWrapper);
            article.setLikes(article.getLikes()+1);
            articleService.updateById(article);
            // 省略具体实现
            likesService.save(likes);
            return true;
        }else{
            log.info("用户点赞了，删除点赞表中的数据，然后点赞数减一");
            likesService.removeById(likesServiceOne);
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Article::getId,likes.getArticleId());
            Article article = articleService.getOne(queryWrapper);
            article.setLikes(article.getLikes()==0?article.getLikes():article.getLikes()-1);
            articleService.updateById(article);
            return false;
        }
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