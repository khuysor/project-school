package com.huysor.projectschool.controller;

import com.huysor.projectschool.constant.ApiConstant;
import com.huysor.projectschool.dto.request.CategoryReq;
import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.response.ResultResp;
import com.huysor.projectschool.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.huysor.projectschool.utils.DateUtil.parseAndValidateDate;

@RestController
@RequiredArgsConstructor

public class CategoryController {
    private final CategoryServices categoryServices;

    @PostMapping(ApiConstant.ApiCategory)
    public ResultResp createCategory(@RequestBody CategoryReq categoryReq) {
        return categoryServices.create(categoryReq);
    }

    @GetMapping(ApiConstant.ApiCategory)
    public ResultResp allCategoryWithPage(@RequestParam(value = "_pageIndex", defaultValue = "1") int pageIndex,
                                          @RequestParam(value = "_pageSize", defaultValue = "25") int pageSize,
                                          @RequestParam(value = "startDate", required = false) String startDate,
                                          @RequestParam(value = "endDate", required = false) String endDate) {
        FilterDate filterDate=new FilterDate();
        filterDate.setStartDate(parseAndValidateDate(startDate));
        filterDate.setEndDate( parseAndValidateDate(endDate));
        PageReq pageReq = new PageReq(pageIndex, pageSize);
        return categoryServices.allCateWithPage(pageReq,filterDate);
    }

    @GetMapping(ApiConstant.ApiCategoryAll)
    public ResultResp allCategory() {
        return categoryServices.allCategory();
    }
    @GetMapping(ApiConstant.ApiCategoryCount)
    public ResultResp countCategory() {
        return categoryServices.countCategory();
    }

    @GetMapping(ApiConstant.ApiCategoryID)
    public ResultResp findById(@PathVariable("id") Long id) {
        return categoryServices.getCategoryById(id);
    }

}
