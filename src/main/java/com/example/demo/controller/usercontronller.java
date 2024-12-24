package com.example.demo.controller;

import com.example.demo.utils.JwtUtil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.pojo.LoginRequest;
import com.example.demo.service.userservice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.pojo.RegisterRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class usercontronller {

    @Autowired
    private userservice Userservice;

    @PostMapping("/register")
    public Result register(@RequestBody @Valid RegisterRequest registerRequest) {
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        User u = Userservice.findbyName(username);
        if (u == null) {
            Userservice.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }


    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();


        User loguser = Userservice.findbyName(username);


        if (loguser == null) {
            return Result.error("用户名错误");
        }

        // 判断密码是否为 null，再进行密码比较
        if (loguser.getPassword() != null && loguser.getPassword().equals(password)) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loguser.getUserId());
            claims.put("username", loguser.getUserName());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    @GetMapping("/{id}/username")
    public Result<String> getUsername(@PathVariable Long id) {
        User user = Userservice.getUserById(id);
        if (user != null) {
            return Result.success(user.getUserName());
        } else {
            return Result.error("用户不存在");
        }
    }
}