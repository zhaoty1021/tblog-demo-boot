package com.tyrone.blog.converter;

import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.dto.UserDTO;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.domain.vo.LoginVO;
import com.tyrone.blog.domain.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/22
 */
@Mapper
public interface UserConverter {
    // 获取实例
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    // 定义 PO -> DTO 的映射
    UserDTO userTouserDTO(User user);

    // 定义 DTO -> PO 的映射，反向映射
    User userDTOToUser(UserDTO userDTO);

    // dto -> vo
    UserVO userDTOToUserVO(UserDTO userDTO);

    // po -> vo
    UserVO userToUserVO(User user);

    List<UserVO> usersToUserVOs(List<User> users);
}
