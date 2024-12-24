package com.example.demo.repository;

import com.example.demo.pojo.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    List<News> findTop10ByOrderByHotScoreDesc();
    List<News> findTop10ByOrderByAdvScoreDesc();

}
