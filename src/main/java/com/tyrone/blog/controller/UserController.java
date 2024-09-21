package com.tyrone.blog.controller;

import com.tyrone.blog.annotation.SysLog;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/28
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    @SysLog("查询用户列表")
    public List<User> list() {
       return userService.list();
    }

}
