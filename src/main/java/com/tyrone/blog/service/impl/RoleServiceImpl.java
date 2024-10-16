package com.tyrone.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyrone.blog.converter.RoleConverter;
import com.tyrone.blog.domain.dto.RoleDTO;
import com.tyrone.blog.domain.pojo.Role;
import com.tyrone.blog.enums.CodeEnum;
import com.tyrone.blog.exceptions.BizException;
import com.tyrone.blog.service.RoleService;
import com.tyrone.blog.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author zhaotianyu3
* @description 针对表【t_role(角色表)】的数据库操作Service实现
* @createDate 2024-10-10 14:16:30
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Resource
    private RoleMapper roleMapper;


    @Override
    public RoleDTO addRole(RoleDTO roleDTO) {
        // 使用 Optional 检查roleCode是否为空
        String roleCode = Optional.ofNullable(roleDTO.getRoleCode())
                .orElseThrow(() -> new BizException(CodeEnum.MISSING_PARAMETER, "roleCode"));
        // 使用 Optional 检查roleName是否为空
        String roleName = Optional.ofNullable(roleDTO.getRoleName())
                .orElseThrow(() -> new BizException(CodeEnum.MISSING_PARAMETER, "roleName"));
        // 使用 Optional 检查描述是否为空
        String description = Optional.ofNullable(roleDTO.getDescription())
                .orElseThrow(() -> new BizException(CodeEnum.MISSING_PARAMETER, "description"));
        if(roleMapper.insert(RoleConverter.INSTANCE.roleDTOToRole(roleDTO))==1){
            return roleDTO;
        }else{
            throw new BizException(CodeEnum.ERROR,"添加角色失败");
        }
    }

    @Override
    public List<RoleDTO> listRoles() {
        // 使用 MyBatis-Plus 查询 Role 列表
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        List<Role> roleList = roleMapper.selectList(queryWrapper);


        // 使用 Stream API 将 Role 列表转换为 RoleDTO 列表
        return roleList.stream()
                .map(role -> {
                    RoleDTO roleDTO = new RoleDTO();
                    roleDTO.setRoleName(role.getRoleName());
                    roleDTO.setDescription(role.getDescription());
                    roleDTO.setRoleCode(role.getRoleCode());
                    // 设置其他字段...
                    return roleDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateRole(RoleDTO roleDTO) {
        // 使用 Optional 检查roleCode是否为空
        String roleCode = Optional.ofNullable(roleDTO.getRoleCode())
                .orElseThrow(() -> new BizException(CodeEnum.MISSING_PARAMETER, "roleCode"));
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code", roleCode);
        if(roleMapper.selectOne(queryWrapper) == null){
            throw new BizException(CodeEnum.DATA_NOT_EXIST,"角色不存在");
        }
        // 使用 Optional 检查roleName是否为空
        String roleName = Optional.ofNullable(roleDTO.getRoleName())
                .orElseThrow(() -> new BizException(CodeEnum.MISSING_PARAMETER, "roleName"));
        // 使用 Optional 检查描述是否为空
        String description = Optional.ofNullable(roleDTO.getDescription())
                .orElseThrow(() -> new BizException(CodeEnum.MISSING_PARAMETER, "description"));
        Role role = RoleConverter.INSTANCE.roleDTOToRole(roleDTO);
        if(roleMapper.updateByCode(role)==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteRole(String roleCode) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_code", roleCode);
        Role role = Optional.ofNullable(roleMapper.selectOne(queryWrapper))
                .orElseThrow(() -> new BizException(CodeEnum.DATA_NOT_EXIST,"角色不存在"));
        role.setIsDeleted(1);
        if(roleMapper.deleteByRoleCode(role)==1){
            return true;
        }else {
            return false;
        }
    }
}




