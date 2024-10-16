package com.tyrone.blog.mapper;

import com.tyrone.blog.domain.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author zhaotianyu3
* @description 针对表【t_role(角色表)】的数据库操作Mapper
* @createDate 2024-10-10 14:16:30
* @Entity com.tyrone.blog.domain.pojo.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    int updateByCode(@Param("role") Role role);

    int deleteByRoleCode(@Param("role") Role role);
}




