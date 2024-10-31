package com.huysor.projectschool.services;

import com.huysor.projectschool.entity.FileAttachments;
import org.springframework.web.multipart.MultipartFile;

public interface FileAttachmentServices {
    FileAttachments toUpload(MultipartFile file) throws Exception ;

    String toGetFileByFileName(String fileName);
}
