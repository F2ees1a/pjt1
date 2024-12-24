package com.example.demo.service;

import com.example.demo.pojo.News;
import com.example.demo.pojo.User;
import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

    // 热度计算公式
    private double calculateHotScore(News news) {
        return (news.getViews3h() * 0.5 + news.getViews12h() * 0.3 + news.getTotalViews() * 0.2) * 0.2
                + news.getLikes() * 0.3 + news.getFavorites() * 0.5;
    }

    // 加权热度计算
    private double calculateAdvScore(double hotScore, double typeWeight) {
        return hotScore * typeWeight;
    }

    // 获取热门新闻
    @Override
    public List<News> getHotList() {
        return newsRepository.findTop10ByOrderByHotScoreDesc();
    }

    // 获取推荐新闻
    @Override
    public List<News> getRecommendedList() {
        return newsRepository.findTop10ByOrderByAdvScoreDesc();
    }



    // 点赞操作，接收 typeWeights 参数
    @Override
    public boolean likeNews(Integer newsId, Integer userId, Map<String, Double> typeWeights) {
        Optional<News> newsOpt = newsRepository.findById(newsId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (newsOpt.isPresent() && userOpt.isPresent()) {
            News news = newsOpt.get();
            User user = userOpt.get();

            news.setLikes(news.getLikes() + 1);
            double hotScore = calculateHotScore(news);
            // 使用从前端传来的 typeWeights 来计算加权热度
            double typeWeight = typeWeights.getOrDefault(news.getNewsType(), 1.0);
            news.setHotScore((float) hotScore);
            news.setAdvScore((float) calculateAdvScore(hotScore, typeWeight));

            newsRepository.save(news);
            return true;
        }
        return false;
    }

    // 收藏操作，接收 typeWeights 参数
    @Override
    public boolean favoriteNews(Integer newsId, Integer userId, Map<String, Double> typeWeights) {
        Optional<News> newsOpt = newsRepository.findById(newsId);
        Optional<User> userOpt = userRepository.findById(userId);

        if (newsOpt.isPresent() && userOpt.isPresent()) {
            News news = newsOpt.get();
            User user = userOpt.get();

            news.setFavorites(news.getFavorites() + 1);
            double hotScore = calculateHotScore(news);
            // 使用从前端传来的 typeWeights 来计算加权热度
            double typeWeight = typeWeights.getOrDefault(news.getNewsType(), 1.0);
            news.setHotScore((float) hotScore);
            news.setAdvScore((float) calculateAdvScore(hotScore, typeWeight));

            newsRepository.save(news);
            return true;
        }
        return false;
    }
}
