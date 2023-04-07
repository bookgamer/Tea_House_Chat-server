package com.example.thc;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.thc.common.R;
import com.example.thc.entity.Article;
import com.example.thc.entity.Comment;
import com.example.thc.entity.HotArticle;
import com.example.thc.entity.User;
import com.example.thc.mapper.HotArticleMapper;
import com.example.thc.service.ArticleService;
import com.example.thc.service.HotArticleService;
import com.example.thc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@SpringBootTest
class TeaHouseChatServerApplicationTests {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private HotArticleService hotArticleService;

    @Test
    void test() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String time = date.format(formatter);
        LambdaQueryWrapper<HotArticle> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(HotArticle::getTime, time);
        List<HotArticle> HotArticle = hotArticleService.list(queryWrapper);
        JSONArray jsonArray = new JSONArray();
        for (HotArticle h :HotArticle) {
            LambdaQueryWrapper<Article> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Article::getId,h.getArticleId());
            List<Article> articleList = articleService.list(queryWrapper1);
            for (Article article : articleList) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("src", article.getSrc());
                jsonObject.put("Title", article.getTitle());
                jsonObject.put("Intro", article.getIntro());
                jsonObject.put("ID", article.getId());
                // 其他属性同理
                jsonArray.add(jsonObject);
            }
        }
        System.out.println(jsonArray);
    }

    @Test
    void contextLoads() {
        // 创建两个LocalTime对象，表示两个时间
        LocalTime time1 = LocalTime.of(11, 11);
        LocalTime time2 = LocalTime.of(10, 11);

        // 使用ChronoUnit类的between方法计算两个时间之间的差值，可以指定时间单位
        long hours = ChronoUnit.HOURS.between(time2, time1);
        long minutes = ChronoUnit.MINUTES.between(time2, time1);

        // 输出结果
        System.out.println("Hours: " + hours); // Hours: 1
        System.out.println("Minutes: " + minutes); // Minutes: 60
    }

}
