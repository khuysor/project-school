package com.huysor.projectschool.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Entity
@Data
public class FileAttachments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;
    private Long fileSize;
    @Column(updatable = false)
    private LocalDateTime localDateTime =LocalDateTime.now();
}
