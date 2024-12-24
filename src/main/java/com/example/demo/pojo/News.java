package com.example.demo.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动生成ID
    private Integer newsId;

    private String title;

    private String content;

    private String newsType;

    private Integer views3h;

    private Integer views12h;

    private Integer totalViews;

    private Integer likes;

    private Integer favorites;

    private Float hotScore;

    private LocalDateTime createdAt;

    private Float advScore;
}
