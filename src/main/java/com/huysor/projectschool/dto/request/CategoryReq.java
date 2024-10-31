package com.huysor.projectschool.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Request object for creating or updating a category")
public class CategoryReq {
    @Schema(description = "Unique identifier of the category (required for updates)", example = "1",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long id;
    @Schema(description = "Code of the category", example = "CAT001")
    private String code;
    @Schema(description = "Name of the category", example = "Books")
    private String categoryName;
}
