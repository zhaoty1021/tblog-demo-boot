package com.tyrone.blog.service;

import com.tyrone.blog.domain.dto.RoleDTO;
import com.tyrone.blog.domain.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author zhaotianyu3
* @description 针对表【t_role(角色表)】的数据库操作Service
* @createDate 2024-10-10 14:16:30
*/
public interface RoleService extends IService<Role> {

    /**
     * 添加角色
     * @param roleDTO 角色DTO
     */
    RoleDTO addRole(RoleDTO roleDTO);

    /**
     * 查询全部角色
     * @return List
     */
    List<RoleDTO> listRoles();

    /**
     * 更新角色
     * @param roleDTO 角色DTO
     * @return 角色
     */
    boolean updateRole(RoleDTO roleDTO);

    /**
     * 删除角色
     * @param roleCode 角色编码
     */
    boolean deleteRole(String roleCode);
}
