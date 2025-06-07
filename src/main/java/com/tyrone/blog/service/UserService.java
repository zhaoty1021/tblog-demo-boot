package com.tyrone.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.dto.UserDTO;
import com.tyrone.blog.domain.dto.TPage;
import com.tyrone.blog.domain.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tyrone.blog.domain.response.Pagination;
import com.tyrone.blog.domain.vo.UserVO;
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
    UserDTO updatePassword(UserDTO userDTO);
    /**
     * 获取分页用户列表，currentPage表示当前页数，pageSize表示每页显示的记录条数
     * @param currentPage 当前页数
     * @param pageSize 每页显示的记录条数
     * @return Page
     */
    Pagination<UserVO> listUsersByPage(int currentPage, int pageSize);

    /**
     * 获取用户信息
     * @return UserDTO 用户信息
     */
    UserDTO getUserInfoByUsername();

    /**
     * 更新用户信息
     * @param userDTO
     * @return UserDTO
     */
    UserDTO updateUserInfo(UserDTO userDTO);

    /**
     * 添加用户
     * @param userDTO
     * @return UserDTO
     */
    UserDTO addUser(UserDTO userDTO);



}
