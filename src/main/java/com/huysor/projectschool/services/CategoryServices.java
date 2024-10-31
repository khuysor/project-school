package com.huysor.projectschool.services;

import com.huysor.projectschool.dto.request.CategoryReq;
import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.response.ResultResp;

public interface CategoryServices {
    ResultResp allCategory();

    ResultResp allCateWithPage(PageReq pageReq, FilterDate filterDate);

    ResultResp create(CategoryReq category);

    ResultResp countCategory();
    ResultResp delete(Long id);

    ResultResp getCategoryById(Long id);
}
