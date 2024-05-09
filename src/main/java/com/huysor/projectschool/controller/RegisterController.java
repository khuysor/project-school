package com.huysor.projectschool.controller;

import com.huysor.projectschool.entity.Register;
import com.huysor.projectschool.services.RegisterServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import  com.huysor.projectschool.mapping.RegisterMapper;
import com.huysor.projectschool.dto.register.RegisterCreateDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/register")
public class RegisterController {
    private final RegisterServices registerServices;
    private final RegisterMapper registerMapper;

    @PostMapping
    public ResponseEntity<?> createRegister(@RequestBody RegisterCreateDTO registerCreateDTO){
        Register register= registerMapper.toRegister(registerCreateDTO);
        return ResponseEntity.ok(registerCreateDTO);
    }

}
