package com.huysor.projectschool.Auth;

import com.huysor.projectschool.config.security.JwtServices;
import com.huysor.projectschool.dto.auth.UpdatePermissionsReq;
import com.huysor.projectschool.dto.auth.UserReq;
import com.huysor.projectschool.dto.auth.UserRequest;
import com.huysor.projectschool.dto.auth.UserResp;
import com.huysor.projectschool.entity.user.Permission;
import com.huysor.projectschool.entity.user.Role_Enum;
import com.huysor.projectschool.entity.user.User;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.mapping.UserMapper;
import com.huysor.projectschool.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
private final PasswordEncoder passwordEncoder;
private  final UserRepo userRepository;
private final JwtServices jwtServices;
    public UserResp register(UserReq userRegister) {
        User user = User.builder()
                .firstname(userRegister.getFirstname())
                .lastname(userRegister.getLastname())
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .permissions(userRegister.getPermissions())
                .role(Role_Enum.valueOf(userRegister.getRole())).build();

        user= userRepository.save(user);
        UserResp userResponse = UserMapper.INSTANCE.toUserResponse(user);
        userResponse.setToken(jwtServices.generateToken(user));
        return userResponse;
    }

    public UserResp login(UserLogin userLogin) {
        User user = userRepository
                .findByUsername(userLogin.getUsername())
                .orElseThrow(()-> new ApiRequestException("User not found !"));

        if(passwordEncoder.matches(userLogin.getPassword(),user.getPassword())){
            UserResp userResponse = UserMapper.INSTANCE.toUserResponse(user);
            userResponse.setToken(jwtServices.generateToken(user));
            return userResponse;
        } else {
            throw new ApiRequestException("User password Invalid!");
        }

    }

    public UserRequest updateRolePermission(UpdatePermissionsReq userUpdatePermissionsReq) {
        User user = userRepository
                .findByUsername(userUpdatePermissionsReq.getUsername())
                .orElseThrow(()-> new ApiRequestException("User not found !"));
        user.setRole(Role_Enum.valueOf(userUpdatePermissionsReq.getRole()));
        Set<Permission> permissions = userUpdatePermissionsReq.getPermissions().stream()
                .map(Permission::valueOf)
                .collect(Collectors.toSet());
        user.setPermissions(permissions);

        return UserMapper.INSTANCE.toUserRequest(userRepository.save(user));
    }
}
