package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.auth.UserResponseDTO;
import com.huysor.projectschool.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    UserResponseDTO toUserResponse(User user);
}
