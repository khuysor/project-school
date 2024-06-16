package com.huysor.projectschool.Auth;

import com.huysor.projectschool.config.security.JwtServices;
import com.huysor.projectschool.entity.user.Role;
import com.huysor.projectschool.entity.user.User;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.mapping.UserMapper;
import com.huysor.projectschool.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
private final PasswordEncoder passwordEncoder;
private  final UserRepo userRepository;
private final JwtServices jwtServices;
    public UserResponse register(UserRegister userRegister) {
        User user = User.builder()
                .firstname(userRegister.getFirstname())
                .lastname(userRegister.getLastname())
                .username(userRegister.getUsername())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .role(Role.valueOf(userRegister.getRole())).
                build();
        user= userRepository.save(user);
        UserResponse userResponse = UserMapper.INSTANCE.toUserResponse(user);
        userResponse.setToken(jwtServices.generateToken(user));
        return userResponse;
    }

    public UserResponse login(UserLogin userLogin) {
        User user = userRepository
                .findByUsername(userLogin.getUsername())
                .orElseThrow(()-> new ApiRequestException("User not found !"));
        if(passwordEncoder.matches(userLogin.getPassword(),user.getPassword())){
            UserResponse userResponse = UserMapper.INSTANCE.toUserResponse(user);
            userResponse.setToken(jwtServices.generateToken(user));
            return userResponse;
        } else {
            throw new ApiRequestException("User password Invalid!");
        }
    }
}
