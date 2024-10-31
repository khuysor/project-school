package com.huysor.projectschool.controller;

import com.huysor.projectschool.services.FileAttachmentServices;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class FileAttachmentController {
    private  final FileAttachmentServices fileAttachmentServices;

    @PostMapping("api/upload")
    public ResponseEntity<?>uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(fileAttachmentServices.toUpload(file));
    }

    @GetMapping("api/view/{name}")
    public ResponseEntity<?> viewFile(@PathVariable("name") String name) throws Exception {
        String fileName= fileAttachmentServices.toGetFileByFileName(name);
        String directoryName = System.getProperty("user.dir")+"/upload/file/";
        Path filePath= Paths.get(directoryName).resolve(fileName).normalize();
        Resource resource = new FileSystemResource(filePath);
        if(resource.exists()&& resource.isReadable()){
            MediaType mediaType = MediaType.parseMediaType(Files.probeContentType(filePath));
            return ResponseEntity.ok().contentType(mediaType).body(resource);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
