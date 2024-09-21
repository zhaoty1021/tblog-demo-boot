package com.tyrone.blog.service;

import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;


/**
* @author zhaot
* @description 针对表【t_user(用户表)】的数据库操作Service
* @createDate 2024-07-28 17:25:19
*/
@Service
public interface UserService extends IService<User> {
    LoginDTO register(LoginDTO loginDTO);
    LoginDTO login(String username, String password);

}
