package com.tyrone.blog.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.tyrone.blog.annotation.RateLimit;
import com.tyrone.blog.annotation.SysLog;
import com.tyrone.blog.converter.LoginConverter;
import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.domain.vo.LoginVO;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.enums.RateLimitType;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.UserService;
import com.tyrone.blog.utils.IpUtil;
import com.tyrone.blog.utils.LocationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/1
 */
@RestController
@RequestMapping("/api")
@Tag(name = "登录注册")
public class LoginController {
    @Resource
    private UserService userService;
    // 用户注册
    @PostMapping("/register")
    @SysLog("用户注册")
    @Operation(summary = "用户注册")
    public ResultResponse<LoginVO> register(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {
        // 调用注册服务
        try {
            String ip = IpUtil.getIpAddr(request);
            loginDTO.setRegisterIp(ip);
            String location = LocationUtil.getLocationByIp(ip);
            loginDTO.setRegisterAddress(location);
            return ResultResponse.success(LoginConverter.INSTANCE.loginDTOToLoginVO(userService.register(loginDTO)));// 注册成功
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 注册失败
        }
    }
    // 用户登录
    @PostMapping("/login")
    @SysLog("用户登录")
    @RateLimit(LimitNum = 0.5,type = RateLimitType.IP)
    @Operation(summary = "用户登录")
    public ResultResponse<LoginVO> login(@RequestBody LoginDTO loginDTO) {
        // 调用登录服务
        try {
            if (loginDTO != null) {
                LoginVO loginVO = LoginConverter.INSTANCE.loginDTOToLoginVO(loginDTO);
                StpUtil.login(loginDTO.getUsername());
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                loginVO.setTokenName(tokenInfo.getTokenName());
                loginVO.setTokenValue(tokenInfo.getTokenValue());
                return ResultResponse.success(loginVO);// 登录成功
                // 登录成功
            } else {
                return ResultResponse.fail(CodeEnum.FAILURE.getCode(), "用户名或密码不正确");// 登录失败
            }
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 登录
        }

    }

}
