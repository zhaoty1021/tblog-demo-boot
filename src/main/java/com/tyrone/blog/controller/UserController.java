package com.tyrone.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.tyrone.blog.annotation.SysLog;
import com.tyrone.blog.converter.LoginConverter;
import com.tyrone.blog.converter.UserConverter;
import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.dto.UserDTO;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.domain.vo.LoginVO;
import com.tyrone.blog.domain.vo.UserVO;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/28
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/user/list")
    @SysLog("查询用户列表")
    public List<User> list() {
       return userService.list();
    }

    @PostMapping("/user/update")
    @SysLog("更新用户信息")
    public ResultResponse<UserVO> updateUserInfo(@RequestBody UserDTO userDTO) {
        try {
            return ResultResponse.success(
                    UserConverter.INSTANCE.userDTOToUserVO(userService.updateUserInfo(userDTO)),
                    "更新成功");// 成功
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 更新失败
        }
    }



}
