package com.huysor.projectschool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Request object for creating or updating a course")
public class CourseReq {
    @Schema(description = "Unique identifier of the course (required for updates)", example = "1",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long id;
    @Schema(description = "Name of the course", example = "CAT001")
    private String courseName;
    @Schema(description = "Name of the teacher", example = "Books")
    private String teacherName;
    @Schema(description = "Description of the course", example = "For IT student")
    private String description;
    @Schema(description = "Price of the course", example = "100")
    private BigDecimal price;
    private Long categoryId;
}
