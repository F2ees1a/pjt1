package com.example.demo.service;

import com.example.demo.pojo.User;

public interface userservice {

    User findbyName(String username);

    void register(String username, String password);

    User getUserById(Long userId);
}