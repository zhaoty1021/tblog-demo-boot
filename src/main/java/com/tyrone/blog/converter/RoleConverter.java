package com.tyrone.blog.converter;

import com.tyrone.blog.domain.dto.RoleDTO;
import com.tyrone.blog.domain.pojo.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author yingxiu.zty
 * @createTime on 2024/10/10
 * @description
 */
@Mapper
public interface RoleConverter {
    // 获取实例
    RoleConverter INSTANCE = Mappers.getMapper(RoleConverter.class);

    // 定义 PO -> DTO 的映射
    RoleDTO roleToRoleDTO(Role role);

    // 定义 DTO -> PO 的映射，反向映射
    Role roleDTOToRole(RoleDTO roleDTO);

    // dto -> vo
    // UserVO userDTOToUserVO(UserDTO userDTO);
}
