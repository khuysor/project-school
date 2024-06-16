package com.huysor.projectschool.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("auth")
public class AuthController {
private final AuthService authService;
    @PostMapping("register")
    public ResponseEntity<?>register(@RequestBody UserRegister userRegister) {
        UserResponse userResponse =authService.register(userRegister);
        return ResponseEntity.ok(userResponse);
    }
    @PostMapping("authentication")
    public ResponseEntity<?>login(@RequestBody UserLogin userLogin){
        UserResponse userResponse = authService.login(userLogin);
        return ResponseEntity.ok(userResponse);
    }
}
