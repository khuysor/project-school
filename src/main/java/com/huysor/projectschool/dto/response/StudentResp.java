package com.huysor.projectschool.dto.response;

import com.huysor.projectschool.enums.GenderEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResp extends BaseEntityResp{
    private String firstName;
    private String lastName;
    private Integer age;
    private GenderEnum gender;
    private String email;
    private String phone;
    private String address;
}
