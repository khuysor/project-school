package com.huysor.projectschool.repo;

import com.huysor.projectschool.entity.FileAttachments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileAttachmentRepo extends JpaRepository<FileAttachments,Long> {
    FileAttachments findByFileName(String fileName);
}
