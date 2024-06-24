package com.huysor.projectschool.Auth;

import com.huysor.projectschool.dto.auth.UserRegisterDTO;
import com.huysor.projectschool.dto.auth.UserResponseDTO;
import com.huysor.projectschool.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<?> create(){
        User user = new User();
        return ResponseEntity.ok("Hello World") ;
    }
}
