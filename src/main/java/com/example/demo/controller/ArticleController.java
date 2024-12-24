package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.News;
import com.example.demo.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private NewsService newsService;

    // 获取热度榜
    @GetMapping("/list/hot")
    public Result<List<News>> listHot() {
        List<News> hotNews = newsService.getHotList();
        return Result.success(hotNews);
    }

    // 获取推荐榜
    @GetMapping("/list/recommended")
    public Result<List<News>> listRecommended() {
        List<News> recommendedNews = newsService.getRecommendedList();
        return Result.success(recommendedNews);
    }



    // 点赞新闻，接收 typeWeights 参数
    @PostMapping("/like/{newsId}")
    public Result<String> likeNews(@PathVariable Integer newsId, @RequestParam Integer userId, @RequestParam Map<String, Double> typeWeights) {
        boolean success = newsService.likeNews(newsId, userId, typeWeights);  // 传递 typeWeights 参数
        return success ? Result.success("点赞成功") : Result.error("点赞失败");
    }

    // 收藏新闻，接收 typeWeights 参数
    @PostMapping("/favorite/{newsId}")
    public Result<String> favoriteNews(@PathVariable Integer newsId, @RequestParam Integer userId, @RequestParam Map<String, Double> typeWeights) {
        boolean success = newsService.favoriteNews(newsId, userId, typeWeights);  // 传递 typeWeights 参数
        return success ? Result.success("收藏成功") : Result.error("收藏失败");
    }
}
