package com.tyrone.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyrone.blog.domain.User;
import com.tyrone.blog.service.UserService;
import com.tyrone.blog.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author zhaot
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2024-07-28 17:25:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private UserMapper userMapper;
    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword()); // 加密密码
        user.setPassword(encryptedPassword);
        System.out.println(encryptedPassword);
        userMapper.insert(user);
    }

    @Override
    public User login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }
}




