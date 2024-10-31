package com.huysor.projectschool.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class ResponseData<T> {
    private List<T> data;
    private Pagination pagination;

}
