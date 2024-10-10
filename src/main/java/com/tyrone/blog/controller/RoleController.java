package com.tyrone.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.tyrone.blog.domain.dto.RoleDTO;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yingxiu.zty
 * @createTime on 2024/10/10
 * @description 角色控制器
 */
@RestController
@RequestMapping("/api/role")
@Tag(name = "角色操作")
public class RoleController {
    @Resource
    private RoleService roleService;

    @GetMapping("/list")
    @SaCheckLogin
    @Operation(summary = "查询全部角色")
    public ResultResponse listRole() {
        return ResultResponse.success(roleService.listRoles());
    }

    @PostMapping("/add")
    @SaCheckLogin
    @Operation(summary = "添加角色")
    public ResultResponse addRole(@RequestBody RoleDTO roleDTO) {
        try {
            roleService.addRole(roleDTO);
            return ResultResponse.success();
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 插入失败
        }

    }


}
