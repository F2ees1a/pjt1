package com.example.demo.service;



import com.example.demo.mapper.usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.pojo.User;



@Service
public class userserviceimpl implements userservice {
    @Autowired
    private usermapper userMapper;

    @Override
    public User findbyName(String username) {
        User u = userMapper.findbyname(username);

        return u;
    }

    @Override
    public void register(String username, String password) {
        userMapper.add(username,password);
    }
    @Override
    public User getUserById(Long userId) {
        return userMapper.findById(userId);
    }

}