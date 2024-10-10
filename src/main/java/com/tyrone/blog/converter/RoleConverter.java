package com.tyrone.blog.converter;

import com.tyrone.blog.domain.dto.RoleDTO;
import com.tyrone.blog.domain.pojo.Role;
import com.tyrone.blog.domain.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

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
    RoleVO roleDTOToRoleVO(RoleDTO roleDTO);

    List<RoleVO> roleDTOListToRoleVOList(List<RoleDTO> roleDTOList);
}
