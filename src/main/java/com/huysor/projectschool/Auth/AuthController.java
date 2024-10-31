package com.huysor.projectschool.Auth;

import com.huysor.projectschool.constant.ApiConstant;
import com.huysor.projectschool.dto.auth.*;
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

public class AuthController {
    private final AuthService authService;

    @PostMapping(ApiConstant.ApiRegister)
    public ResponseEntity<?> register(@RequestBody UserReq userRegister) {
        UserResp userResponse = authService.register(userRegister);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping(ApiConstant.ApiLogin)
    public ResponseEntity<?> login(@RequestBody UserLogin userLogin) {
        UserResp userResponse = authService.login(userLogin);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping(ApiConstant.ApiRole)
    public ResponseEntity<?> create() {
        List<RoleResp> roleResp = Arrays.stream(Role_Enum.values())
                .map(roleEnum -> RoleResp.builder().roleName(roleEnum.name()).build())
                .toList();
        return ResponseEntity.ok(roleResp);
    }

    @GetMapping(ApiConstant.ApiRolePermission)
    public ResponseEntity<?> getUserPermission() {
        List<PermissionResp> permissionRespList = Arrays
                .stream(Permission.values())
                .map(
                        permission ->
                                PermissionResp.builder()
                                        .permission(permission.name())
                                        .build())
                .collect(Collectors.toList());
        return ResponseEntity.ok(permissionRespList);
    }


    @PutMapping(ApiConstant.ApiUserPermission)
    public ResponseEntity<?> updateUserPermission(@RequestBody UpdatePermissionsReq userPermission) {
        return ResponseEntity.ok(authService.updateRolePermission(userPermission));
    }
}
