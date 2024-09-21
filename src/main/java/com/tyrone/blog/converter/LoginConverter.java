package com.tyrone.blog.converter;

import com.tyrone.blog.domain.dto.LoginDTO;
import com.tyrone.blog.domain.pojo.User;
import com.tyrone.blog.domain.vo.LoginVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author yingxiu.zty
 * @createTime on 2024/9/21
 */
@Mapper
public interface LoginConverter {
    // 获取实例
    LoginConverter INSTANCE = Mappers.getMapper(LoginConverter.class);

    // 定义 PO -> DTO 的映射
    LoginDTO userTologinDTO(User user);

    // 定义 DTO -> PO 的映射，反向映射
    User loginDTOToUser(LoginDTO loginDTO);

    // dto -> vo
    LoginVO loginDTOToLoginVO(LoginDTO loginDTO);

}
