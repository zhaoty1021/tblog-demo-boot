package com.tyrone.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyrone.blog.domain.User;
import com.tyrone.blog.service.UserService;
import com.tyrone.blog.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author zhaot
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2024-07-28 17:25:19
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




