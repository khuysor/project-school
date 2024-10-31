package com.huysor.projectschool.dto.response;

import com.huysor.projectschool.constant.ApiConstant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;

@Getter
@Setter
public class CourseResp extends BaseEntityResp {
    private String courseName;
    private String teacherName;
    private String description;
    private BigDecimal price;
    private String imageName;
    private String categoryName;
    public String getImageUrl() {
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        return baseUrl + ApiConstant.ImagePath + (imageName!=null&& !imageName.isEmpty()?imageName:"course.jpeg");
    }
}
