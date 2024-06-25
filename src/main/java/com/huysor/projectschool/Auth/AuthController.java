package com.huysor.projectschool.Auth;

import com.huysor.projectschool.dto.auth.PermissionDTO;
import com.huysor.projectschool.dto.auth.RoleDTO;
import com.huysor.projectschool.dto.auth.UserRegisterDTO;
import com.huysor.projectschool.dto.auth.UserResponseDTO;
import com.huysor.projectschool.entity.user.Permission;
import com.huysor.projectschool.entity.user.Role_Enum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
private final AuthService authService;
    @PostMapping("register")
    public ResponseEntity<?>register(@RequestBody UserRegisterDTO userRegister) {
        UserResponseDTO userResponse =authService.register(userRegister);
        return ResponseEntity.ok(userResponse);
    }
    @PostMapping("authentication")
    public ResponseEntity<?>login(@RequestBody UserLogin userLogin){
        UserResponseDTO userResponse = authService.login(userLogin);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("roles")
    public ResponseEntity<?> create(){
        List<RoleDTO>roleDTOS= Arrays.stream(Role_Enum.values())
                .map(roleEnum -> RoleDTO.builder().roleName(roleEnum.name()).build())
                .toList();
        return ResponseEntity.ok(roleDTOS) ;
    }
    @GetMapping("role/permission")
    public ResponseEntity<?>getUserPermission(){
        List<PermissionDTO>permissionDTOList= Arrays
                .stream(Permission.values())
                .map(
                        permission ->
                                PermissionDTO.builder()
                                        .name(permission.name())
                                        .permission(permission.getPermissionName())
                                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(permissionDTOList) ;
    }
}
