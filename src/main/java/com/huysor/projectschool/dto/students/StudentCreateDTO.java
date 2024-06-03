package com.huysor.projectschool.dto.students;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class StudentCreateDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private String email;
    private String phone;
    private String address;
}
