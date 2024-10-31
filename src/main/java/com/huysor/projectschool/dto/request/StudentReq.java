package com.huysor.projectschool.dto.request;

import com.huysor.projectschool.enums.GenderEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentReq {

    @Schema(description = "Unique identifier of the student (required for updates)",
            example = "1",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long id;

    @Schema(description = "First name of the student",
            example = "John",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @Schema(description = "Last name of the student",
            example = "Doe",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @Schema(description = "Age of the student (must be a positive integer)",
            example = "20",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer age;

    @Schema(description = "Gender of the student",
            example = "MALE",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private GenderEnum gender;

    @Schema(description = "Email address of the student",
            example = "john.doe@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;

    @Schema(description = "Phone number of the student",
            example = "+1234567890",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String phone;

    @Schema(description = "Residential address of the student",
            example = "123 Main St, City, Country",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String address;
}
