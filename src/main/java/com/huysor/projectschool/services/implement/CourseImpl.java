package com.huysor.projectschool.services.implement;

import com.huysor.projectschool.dto.request.CourseReq;
import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.response.CourseResp;
import com.huysor.projectschool.dto.response.ResultResp;
import com.huysor.projectschool.entity.Category;
import com.huysor.projectschool.entity.Course;
import com.huysor.projectschool.exception.ApiRequestException;
import com.huysor.projectschool.mapping.CourseMapper;
import com.huysor.projectschool.repo.CategoryRepo;
import com.huysor.projectschool.repo.CourseRepo;
import com.huysor.projectschool.services.CourseServices;
import com.huysor.projectschool.utils.ImageUtil;
import com.huysor.projectschool.utils.Pagination;
import com.huysor.projectschool.utils.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseImpl implements CourseServices {

    private final CourseRepo courseRepo;
    private final CourseMapper courseMapper;
    private final String directoryName = System.getProperty("user.dir") + "/upload/file/";
    private final CategoryRepo categoryRepo;

    @Override
    public ResultResp findAll(PageReq pageReq, FilterDate filterDate) {
        Pageable pageable = PageRequest.of(pageReq.getPageIndex()-1, pageReq.getPageSize(),Sort.by(Sort.Direction.DESC, "id"));
        Page<Course> coursePage;
        if (filterDate.getEndDate()==null|| filterDate.getStartDate()==null) {
            coursePage = courseRepo.findAll(pageable);
        }else{
            coursePage=courseRepo.findByCreateTimeAfterAndUpdateTimeBefore(filterDate.getStartDate(),filterDate.getEndDate(),pageable);
        }
        List<CourseResp> courseResp = coursePage.map(courseMapper::toCourseResp).getContent();
        return ResultResp.success(
                new ResponseData<>(
                        courseResp,
                        new Pagination(
                                coursePage.getNumber() + 1, // Adjust for 1-based index if needed
                                coursePage.getSize(),
                                coursePage.getTotalElements(),
                                coursePage.getTotalPages(),
                                coursePage.hasNext(),
                                coursePage.hasPrevious()
                        )
                )
        );
    }

    @Override
    public Course findById(Long id) {
        return courseRepo.findById(id).orElseThrow(() -> new ApiRequestException("Course with id : %d not found".valueOf(id)));
    }

    @Override
    public ResultResp createWithImage(MultipartFile file, CourseReq courseReq) {

        try {
            System.out.println(courseReq);
            Course course = new Course();
            Optional<Category> category = categoryRepo.findById(courseReq.getCategoryId());
            if (category.isEmpty()) {
                log.error("can't find category with category Id {}", courseReq.getCategoryId());
                return ResultResp.fail("Can't Save course ");
            }
            if (courseReq.getId() == null) {
                course = courseMapper.toCourse(courseReq);
            } else {
                // Updating an existing course
                course = courseRepo.findById(courseReq.getId()).orElse(null);
                if (course == null) {
                    return ResultResp.fail();
                }
                courseMapper.updateEntityFromDto(courseReq, course);
            }
            course.setCategory(category.get());
            if (file == null) {
                try {
                    courseRepo.save(course);
                    log.error("==>save course success");
                    return ResultResp.success();
                } catch (Exception e) {
                    log.error("==>Can't save course");
                    return ResultResp.fail();
                }

            }
            if (uploadCourse(file, course)) {
                return ResultResp.success();
            }
            return ResultResp.fail();
        } catch (Exception e) {
            log.error("==>Can't upload file");
            throw new ApiRequestException("Invalid Upload");
        }

    }


    @Override
    public Course findByName(String name) {
        return courseRepo.findByImageName(name);
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

    @Override
    public ResultResp countCourse() {
        return ResultResp.success(courseRepo.count());
    }


    private Boolean uploadCourse(MultipartFile file, Course course) throws IOException {

        final String fileName = file.getOriginalFilename();
        File dir = new File(directoryName);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (fileName == null || fileName.contains("..")) {
            throw new IllegalArgumentException("File name contain invalid path sequence" + fileName);
        }
        String newFileName = LocalDateTime.now() + "." + ImageUtil.getFileExtension(fileName);
        if (fileName.contains(".pdf") || fileName.contains(".doc") || fileName.contains(".pttx")) {
            // handle file doc or pdf
            String filePa = directoryName + ImageUtil.getFileExtension(fileName);
            File newFile = new File(filePa);
            file.transferTo(newFile);
        } else {
            // handle image
            Path path = Paths.get(directoryName, newFileName);
            Files.write(path, file.getBytes());
        }
        course.setImageName(newFileName);
        try {
            courseRepo.save(course);
            log.info("==>Create course success");
            return true;
        } catch (Exception e) {
            log.error("==>Can't create course");
            return false;
        }

    }


}
