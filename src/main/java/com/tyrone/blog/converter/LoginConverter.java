package com.tyrone.blog.converter;

import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.domain.vo.LoginVO;
import org.springframework.stereotype.Component;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/21
 */
@Component
public class LoginConverter {
    public static final LoginConverter INSTANCE = new LoginConverter();

    private LoginConverter() {}

    // User(PO) -> LoginDTO
    public LoginDTO userTologinDTO(User user) {
        if (user == null) {
            return null;
        }
        LoginDTO dto = new LoginDTO();
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setNickname(user.getNickname());
        dto.setEmail(user.getEmail());
        dto.setRegisterIp(user.getRegisterIp());
        dto.setRegisterAddress(user.getRegisterAddress());
        return dto;
    }

    // LoginDTO -> User(PO)
    public User loginDTOToUser(LoginDTO loginDTO) {
        if (loginDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(loginDTO.getUsername());
        user.setPassword(loginDTO.getPassword());
        user.setNickname(loginDTO.getNickname());
        user.setEmail(loginDTO.getEmail());
        user.setRegisterIp(loginDTO.getRegisterIp());
        user.setRegisterAddress(loginDTO.getRegisterAddress());
        return user;
    }

    // LoginDTO -> LoginVO
    public LoginVO loginDTOToLoginVO(LoginDTO loginDTO) {
        if (loginDTO == null) {
            return null;
        }
        LoginVO vo = new LoginVO();
        vo.setUsername(loginDTO.getUsername());
        vo.setNickname(loginDTO.getNickname());
        vo.setEmail(loginDTO.getEmail());
        vo.setRegisterIp(loginDTO.getRegisterIp());
        vo.setRegisterAddress(loginDTO.getRegisterAddress());
        // tokenName和tokenValue需要额外设置，LoginDTO中没有这些字段
        return vo;
    }

    // 可选：添加一个方法用于设置token
    public LoginVO loginDTOToLoginVOWithToken(LoginDTO loginDTO, String tokenName, String tokenValue) {
        LoginVO vo = loginDTOToLoginVO(loginDTO);
        if (vo != null) {
            vo.setTokenName(tokenName);
            vo.setTokenValue(tokenValue);
        }
        return vo;
    }
}