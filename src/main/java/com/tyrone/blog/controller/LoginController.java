package com.tyrone.blog.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.tyrone.blog.annotation.SysLog;
import com.tyrone.blog.converter.LoginConverter;
import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.dto.UserDTO;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.domain.vo.LoginVO;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
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
    @SysLog("用户注册")
    public ResultResponse<LoginVO> register(@RequestBody LoginDTO loginDTO) {
        // 调用注册服务
        try {
            return ResultResponse.success(LoginConverter.INSTANCE.loginDTOToLoginVO(userService.register(loginDTO)));// 注册成功
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 注册失败
        }
    }
    // 用户登录
    @PostMapping("/login")
    @SysLog("用户登录")
    public ResultResponse<LoginVO> login(@RequestParam String username, @RequestParam String password) {
        // 调用登录服务
        LoginDTO loginDTO = userService.login(username, password);
        try {
            if (loginDTO != null) {
                LoginVO loginVO = LoginConverter.INSTANCE.loginDTOToLoginVO(loginDTO);
                return ResultResponse.success(loginVO);// 登录成功
                // 登录成功
            } else {
                return ResultResponse.fail(CodeEnum.FAILURE.getCode(), "用户名或密码不正确");// 登录失败
            }
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 注册失败
        }

    }
}
