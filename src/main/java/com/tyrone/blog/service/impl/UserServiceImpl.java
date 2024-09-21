package com.tyrone.blog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyrone.blog.converter.LoginConverter;
import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.UserService;
import com.tyrone.blog.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

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
    public LoginDTO register(LoginDTO loginDTO) {
        // 使用 Optional 检查用户名是否为空
        String username = Optional.ofNullable(loginDTO.getUsername())
                .orElseThrow(() -> new BizException(CodeEnum.MISSING_PARAMETER, "username"));
        // 使用 Optional 检查密码是否为空
        String password = Optional.ofNullable(loginDTO.getPassword())
                .orElseThrow(() -> new BizException(CodeEnum.MISSING_PARAMETER, "password"));
        String encryptedPassword = passwordEncoder.encode(password); // 加密密码
        loginDTO.setPassword(encryptedPassword);
        userMapper.insert(LoginConverter.INSTANCE.loginDTOToUser(loginDTO));
        return loginDTO;
    }

    @Override
    public LoginDTO login(String username, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            StpUtil.login(user.getId());
            return LoginConverter.INSTANCE.userTologinDTO(user);
        }else{
            throw new BizException(CodeEnum.LOGIN_ERROR);
        }
    }
}




