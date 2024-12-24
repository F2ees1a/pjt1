package com.example.demo.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.demo.pojo.User;
@Mapper
public interface usermapper {

    @Select("select * from user where username=#{username}")
    User findbyname(String username);

    @Select("insert into user(username,password)" +
            " values(#{username},#{password})")
    void add(String username, String password);
    User findById(Long userId);
}