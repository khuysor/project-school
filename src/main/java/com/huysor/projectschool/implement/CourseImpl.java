package com.huysor.projectschool.implement;

import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.repo.CourseRepo;
import com.huysor.projectschool.services.CourseServices;
import com.huysor.projectschool.utils.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseImpl implements CourseServices {
    private static final Logger log = LoggerFactory.getLogger(CourseImpl.class);
    private final CourseRepo courseRepo;

    @Override
    public List<Course> findAll() {
        return courseRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Course findById(Long id) {
        return courseRepo.findById(id).orElseThrow(() -> new ApiRequestException("Course with id : %d not found".valueOf(id)));
    }

    @Override
    public Course createWithImage(MultipartFile file, Course course) {
        String directoryName = System.getProperty("user.dir") + "/upload/file/";
        String fileName = file.getOriginalFilename();
        try {
            File dir = new File(directoryName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (fileName.contains("..")) {
                throw new IllegalArgumentException("File name contain invalid path sequence" + fileName);
            }
            String newFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            if (fileName.contains(".pdf") || fileName.contains(".doc") || fileName.contains(".pttx")) {

                // handle file doc or pdf
                String filePa = directoryName + File.separator + newFileName;
                File newFile = new File(filePa);
                file.transferTo(newFile);
                  course.setImageName(newFileName);
                  return courseRepo.save(course);
            } else {
                // handle image
                Path path = Paths.get(directoryName, newFileName);
                Files.write(path, file.getBytes());
                course.setImageName(newFileName);
                course.setFileByte(ImageUtil.compressImage(file.getBytes()));
                return courseRepo.save(course); 
            }
        } catch (Exception e) {
            log.error("Invalid Upload");
            throw new ApiRequestException("Invalid Upload");
        }

    }

    @Override
    public Course findByName(String name) {
        return courseRepo.findByImageName(name);
    }

    @Override
    public Course create(Course course) {
        return courseRepo.save(course);
    }

    @Override
    public Course update(MultipartFile file, Course course) {
        String directoryName = System.getProperty("user.dir") + "/upload/file/";
        String fileName = file.getOriginalFilename();
        try {
            File dir = new File(directoryName);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            if (fileName.contains("..")) {
                throw new IllegalArgumentException("File name contain invalid path sequence" + fileName);
            }
            String newFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
            if (fileName.contains(".pdf") || fileName.contains(".doc") || fileName.contains(".pttx")) {

                String filePa = directoryName + File.separator + newFileName;
                File newFile = new File(filePa);
                file.transferTo(newFile);
                course.setImageName(newFileName);
                return courseRepo.save(course);
            } else {
                Path path = Paths.get(directoryName, newFileName);
                Files.write(path, file.getBytes());
                course.setImageName(newFileName);
                course.setFileByte(ImageUtil.compressImage(file.getBytes()));
                return courseRepo.save(course);
            }
        } catch (Exception e) {
            log.error("Invalid Upload");
            throw new ApiRequestException("Invalid Upload");
        }
    }

    @Override
    public void delete(Long id) {
        Course course = findById(id);
        courseRepo.delete(course);
    }

    @Override
    public List<Course> findByCategoryId(Long cateId) {
        return courseRepo.findCourseByCategoryId(cateId);
    }


}
