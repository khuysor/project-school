package com.huysor.projectschool.mapping;

import com.huysor.projectschool.dto.auth.UserRequest;
import com.huysor.projectschool.dto.auth.UserResp;
import com.huysor.projectschool.entity.user.Permission;
import com.huysor.projectschool.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);
    @Mapping(source = "permissions", target = "permission")
    UserResp toUserResponse(User user);

    @Mapping(source = "permissions", target = "permission")
    UserRequest toUserRequest(User user);
    default List<String> mapPermissions(List<Permission> permissions) {
        return permissions.stream()
                .map(Permission::name)
                .collect(Collectors.toList());
    }
}
