package com.huysor.projectschool.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tbl_register")
@Data
public class Register {
    @Id
    private Long Id;
    @ManyToOne
    private Students students;
    @ManyToMany
    private List< Course> course;
    private BigDecimal price;

}
