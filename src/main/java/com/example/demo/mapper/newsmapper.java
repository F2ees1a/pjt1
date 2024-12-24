package com.example.demo.mapper;



import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.demo.pojo.News;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface newsmapper {
    @Select("SELECT * FROM news ORDER BY hot_score DESC LIMIT 10") // 热度榜
    List<News> getHotList();

    @Select("SELECT * FROM news ORDER BY hot_score * type_weights LIMIT 10") // 推荐榜
    List<News> getRecommendedList();

    @Select("SELECT * FROM news WHERE news_id IN (SELECT news_id FROM similar_news WHERE user_id = #{userId})") // 关联榜
    List<News> getRelatedList(Integer userId);
    @Update("UPDATE news SET likes = likes + 1 WHERE id = #{newsId}")
    int incrementLikes(Integer newsId);

    @Update("UPDATE news SET favorites = favorites + 1 WHERE id = #{newsId}")
    int incrementFavorites(Integer newsId);

}
