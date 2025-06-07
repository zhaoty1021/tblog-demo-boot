package com.tyrone.blog.converter;

import com.tyrone.blog.domain.dto.RoleDTO;
import com.tyrone.blog.domain.pojo.Role;
import com.tyrone.blog.domain.vo.RoleVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yingxiu.zty
 * @createTime on 2024/10/10
 */
@Component
public class RoleConverter {
    public static final RoleConverter INSTANCE = new RoleConverter();

    private RoleConverter() {}

    // Role(PO) -> RoleDTO
    public RoleDTO roleToRoleDTO(Role role) {
        if (role == null) {
            return null;
        }
        RoleDTO dto = new RoleDTO();
        dto.setRoleCode(role.getRoleCode());
        dto.setRoleName(role.getRoleName());
        dto.setDescription(role.getDescription());
        return dto;
    }

    // RoleDTO -> Role(PO)
    public Role roleDTOToRole(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        Role role = new Role();
        role.setRoleCode(roleDTO.getRoleCode());
        role.setRoleName(roleDTO.getRoleName());
        role.setDescription(roleDTO.getDescription());
        return role;
    }

    // RoleDTO -> RoleVO
    public RoleVO roleDTOToRoleVO(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        RoleVO vo = new RoleVO();
        vo.setRoleCode(roleDTO.getRoleCode());
        vo.setRoleName(roleDTO.getRoleName());
        vo.setDescription(roleDTO.getDescription());
        return vo;
    }

    // List<RoleDTO> -> List<RoleVO>
    public List<RoleVO> roleDTOListToRoleVOList(List<RoleDTO> roleDTOList) {
        if (roleDTOList == null) {
            return null;
        }
        return roleDTOList.stream()
                .map(this::roleDTOToRoleVO)
                .collect(Collectors.toList());
    }
}