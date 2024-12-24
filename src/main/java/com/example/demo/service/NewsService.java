package com.example.demo.service;
import java.util.Map;
import com.example.demo.pojo.News;
import java.util.List;

public interface NewsService {
    List<News> getHotList();
    List<News> getRecommendedList();

    boolean likeNews(Integer newsId, Integer userId, Map<String, Double> typeWeights);

    // 收藏新闻
    boolean favoriteNews(Integer newsId, Integer userId, Map<String, Double> typeWeights);
}

