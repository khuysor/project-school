package com.huysor.projectschool.entity;

import com.huysor.projectschool.enums.GenderEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "tbl_students")
public class Students {
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
    @CreatedDate
    @Column(name = "create_time ", updatable = false)
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;

}
