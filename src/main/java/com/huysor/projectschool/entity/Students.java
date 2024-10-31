package com.huysor.projectschool.entity;

import com.huysor.projectschool.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "tbl_students")
public class Students extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    private String email;
    private String phone;
    private String address;
}
