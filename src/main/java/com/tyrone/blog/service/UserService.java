package com.tyrone.blog.service;

import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.dto.UserDTO;
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
    /**
     * 用户注册
     * @param loginDTO 登录DTO
     * @return loginDTO 登录DTO
     */
    LoginDTO register(LoginDTO loginDTO);
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return loginDTO 登录DTO
     */
    LoginDTO login(String username, String password);
    /**
     * 更新用户信息
     * @param userDTO 登录DTO
     * @return userDTO 登录DTO
     */
    UserDTO updateUserInfo(UserDTO userDTO);



}
