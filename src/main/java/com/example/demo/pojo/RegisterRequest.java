package com.example.demo.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class RegisterRequest {
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^[^\\s]{5,16}$", message = "用户名必须为5到16个字符且不包含空格")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^[^\\s]{5,16}$", message = "密码必须为5到16个字符且不包含空格")
    private String password;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
