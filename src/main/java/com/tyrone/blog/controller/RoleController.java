package com.tyrone.blog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.tyrone.blog.converter.RoleConverter;
import com.tyrone.blog.domain.dto.RoleDTO;
import com.tyrone.blog.domain.response.ResultResponse;
import com.tyrone.blog.domain.vo.RoleVO;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public ResultResponse<List<RoleVO>> listRole() {
        try {
            List<RoleVO> roleVOList = RoleConverter.INSTANCE.roleDTOListToRoleVOList(roleService.listRoles());
            return ResultResponse.success(roleVOList, "查询成功");
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());
        }
    }

    @PostMapping("/add")
    @SaCheckLogin
    @Operation(summary = "添加角色")
    public ResultResponse<RoleVO> addRole(@RequestBody RoleDTO roleDTO) {
        try {
            RoleVO roleVO = RoleConverter.INSTANCE.roleDTOToRoleVO(roleService.addRole(roleDTO));
            return ResultResponse.success(roleVO);
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 插入失败
        }
    }
    @PostMapping("/update")
    @SaCheckLogin
    @Operation(summary = "更新角色")
    public ResultResponse updateRole(@RequestBody RoleDTO roleDTO) {
        try {
            boolean update = roleService.updateRole(roleDTO);
            return ResultResponse.success(update,"更新完成");
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 更新失败
        }
    }
    @PostMapping("/delete")
    @SaCheckLogin
    @Operation(summary = "删除角色")
    public ResultResponse deleteRole(@RequestBody RoleDTO roleDTO) {
        try {
            boolean delete = roleService.deleteRole(roleDTO.getRoleCode());
            return ResultResponse.success(delete,"删除完成");
        }catch (BizException e){
            return ResultResponse.fail(e.getCode(), e.getMessage());// 删除失败
        }
    }


}
