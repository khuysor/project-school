package com.huysor.projectschool.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CategoryResp {
    private  Long id;
    private  String code;
    private  String categoryName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
