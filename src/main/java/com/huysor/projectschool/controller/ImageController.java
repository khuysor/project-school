package com.huysor.projectschool.controller;


import com.huysor.projectschool.constant.ApiConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping(ApiConstant.ImagePath)
@RequiredArgsConstructor
public class ImageController {

    private final ResourceLoader resourceLoader;

    @GetMapping("{imageName}")
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        String imagePath = System.getProperty("user.dir") + "/upload/file/" + imageName;
        Path path = Paths.get(imagePath);
        Resource resource = resourceLoader.getResource("file:" + path.toString());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG) // Adjust the content type based on the image type
                .body(resource);
    }
}
