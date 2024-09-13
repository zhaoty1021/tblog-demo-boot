package com.tyrone.blog.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.tyrone.blog.domain.User;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/1
 */
@RestController
@RequestMapping("/api")
public class LoginController {
    @Resource
    private UserService userService;
    // 用户注册
    @PostMapping("/register")
    public void register(@RequestBody User user) {
        // 调用注册服务
        userService.register(user);
    }
    // 用户登录
    @PostMapping("/login")
    public ResultResponse<User> login(@RequestParam String username, @RequestParam String password) {
        System.out.println(username);
        // 调用登录服务
        User user = userService.login(username, password);
        if (user != null) {
            StpUtil.login(user.getId());
            return ResultResponse.success(user);// 登录成功
            // 登录成功
        } else {
            return ResultResponse.fail(CodeEnum.FAILURE.getCode(), "用户名或密码不正确");// 登录失败
        }
    }
}
