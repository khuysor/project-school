package com.huysor.projectschool.dto.files;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class FileAttachmentResponse {
    private Long id;
    private String fileName;
    private String filePath;
    private String fileType;
    private String fileSize;
}
