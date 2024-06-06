package com.huysor.projectschool.services;

import com.huysor.projectschool.dto.files.FileAttachmentResponse;
import com.huysor.projectschool.entity.FileAttachments;
import org.springframework.web.multipart.MultipartFile;

public interface FileAttachmentServices {
    FileAttachments toUpload(MultipartFile file) throws Exception ;
    FileAttachmentResponse toGetFileById(Long id);
    String toGetFileByFileName(String fileName);
}
