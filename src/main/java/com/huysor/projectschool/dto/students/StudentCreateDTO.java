package com.huysor.projectschool.dto.students;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StudentCreateDTO {
    private String name;
    private Integer age;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}