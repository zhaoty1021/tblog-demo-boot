package com.tyrone.blog.mapper;

import com.tyrone.blog.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zhaot
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2024-07-28 17:25:19
* @Entity com.tyrone.blog.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




