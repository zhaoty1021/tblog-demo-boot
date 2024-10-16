package com.tyrone.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tyrone.blog.annotation.SysLog;
import com.tyrone.blog.converter.LoginConverter;
import com.tyrone.blog.converter.UserConverter;
import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.dto.TPage;
import com.tyrone.blog.domain.dto.UserDTO;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.domain.response.Pagination;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.domain.vo.LoginVO;
import com.tyrone.blog.domain.vo.UserVO;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yingxiu.zty
 * @createTime on 2024/7/28
 */
@RestController
@RequestMapping("/api")
@Tag(name = "用户管理")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user/update")
    @SysLog("更新用户信息")
    @Operation(summary = "更新用户信息")
    public ResultResponse<UserVO> updateUserInfo(@RequestBody UserDTO userDTO) {
        try {
            return ResultResponse.success(
                    UserConverter.INSTANCE.userDTOToUserVO(userService.updateUserInfo(userDTO)),
                    "更新成功");// 成功
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 更新失败
        }
    }

    @GetMapping("/user/info")
    @SysLog("查询用户信息")
    @Operation(summary = "分页查询用户信息")
    public ResultResponse<Pagination<UserVO>> getUserInfoByPage(@RequestParam int currentPage, @RequestParam int pageSize) {
        try {
            return ResultResponse.success(
                    userService.listUsersByPage(currentPage, pageSize),
                    "查询成功");// 成功
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 更新失败
        }
    }



}
