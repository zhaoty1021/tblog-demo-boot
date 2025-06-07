package com.tyrone.blog.converter;

import com.tyrone.blog.domain.dto.UserDTO;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.domain.vo.UserVO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/22
 */
@Component
public class UserConverter {
    public static final UserConverter INSTANCE = new UserConverter();

    private UserConverter() {}

    // User(PO) -> UserDTO
    public UserDTO userTouserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setNickname(user.getNickname());
        dto.setPhone(user.getPhone());
        dto.setSex(user.getSex());
        dto.setEmail(user.getEmail());
        return dto;
    }

    // UserDTO -> User(PO)
    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setNickname(userDTO.getNickname());
        user.setPhone(userDTO.getPhone());
        user.setSex(userDTO.getSex());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    // UserDTO -> UserVO
    public UserVO userDTOToUserVO(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        UserVO vo = new UserVO();
        vo.setUsername(userDTO.getUsername());
        vo.setNickname(userDTO.getNickname());
        vo.setEmail(userDTO.getEmail());
        vo.setSex(userDTO.getSex());
        vo.setPhone(userDTO.getPhone());
        // 注意：UserDTO中没有avatar、registerIp等字段，所以这些字段不会被设置
        return vo;
    }

    // User(PO) -> UserVO
    public UserVO userToUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO vo = new UserVO();
        vo.setUsername(user.getUsername());
        vo.setNickname(user.getNickname());
        vo.setEmail(user.getEmail());
        vo.setSex(user.getSex());
        vo.setPhone(user.getPhone());
        vo.setAvatar(user.getAvatar());
        vo.setRegisterIp(user.getRegisterIp());
        vo.setRegisterAddress(user.getRegisterAddress());
        vo.setCreateTime(user.getCreateTime());
        vo.setUpdateTime(user.getUpdateTime());
        return vo;
    }

    // List<User> -> List<UserVO>
    public List<UserVO> usersToUserVOs(List<User> users) {
        if (users == null) {
            return null;
        }
        return users.stream()
                .map(this::userToUserVO)
                .collect(Collectors.toList());
    }
}