package com.huysor.projectschool.services.implement;

import com.huysor.projectschool.dto.request.CategoryReq;
import com.huysor.projectschool.dto.request.FilterDate;
import com.huysor.projectschool.dto.request.PageReq;
import com.huysor.projectschool.dto.response.CategoryResp;
import com.huysor.projectschool.dto.response.ResultResp;
import com.huysor.projectschool.entity.Category;
import com.huysor.projectschool.mapping.CategoryMapper;
import com.huysor.projectschool.repo.CategoryRepo;
import com.huysor.projectschool.services.CategoryServices;
import com.huysor.projectschool.utils.Pagination;
import com.huysor.projectschool.utils.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryImpl implements CategoryServices {
    private final CategoryRepo categoryRepo;
    private final CategoryMapper categoryMapper;

    @Override
    public ResultResp allCategory() {
        return ResultResp.success(categoryRepo.findAll().stream().map(categoryMapper::toCategoryResp).toList());
    }

    @Override
    public ResultResp allCateWithPage(PageReq pageReq, FilterDate filterDate) {
        Pageable pageable = PageRequest.of(pageReq.getPageIndex() - 1, pageReq.getPageSize(), Sort.by(Sort.Direction.DESC, "id"));
        Page<Category> categories;
        if (filterDate.getEndDate()==null|| filterDate.getStartDate()==null) {
            categories = categoryRepo.findAll(pageable);
        } else {
            categories = categoryRepo.findByCreateTimeAfterAndUpdateTimeBefore(filterDate.getStartDate(), filterDate.getEndDate(), pageable);
        }

        List<CategoryResp> categoryRespList = categories.getContent().stream()
                .map(categoryMapper::toCategoryResp)
                .toList();

        return ResultResp.success(new ResponseData<CategoryResp>(categoryRespList, new Pagination(
                categories.getNumber() + 1, // Adjust for 1-based index
                categories.getSize(),
                categories.getTotalElements(),
                categories.getTotalPages(),
                categories.hasNext(),
                categories.hasPrevious()
        )));
    }

    @Override
    public ResultResp create(CategoryReq categoryReq) {
        try {
            if (categoryReq.getId() == null) {
                categoryRepo.save(categoryMapper.toCategory(categoryReq));
                log.info("Create category success");
                return ResultResp.success();
            }
            Optional<Category> category = categoryRepo.findById(categoryReq.getId());
            if (category.isPresent()) {
                categoryMapper.updateEntityFromDto(categoryReq, category.get());
                categoryRepo.save(category.get());
                log.info("Create category success");
                return ResultResp.success();
            } else {
                log.info("Can't Update category ");
                return ResultResp.fail("Update category fail");
            }


        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultResp.fail();
        }

    }

    @Override
    public ResultResp countCategory() {
        return ResultResp.success(categoryRepo.count());
    }

    @Override
    public ResultResp delete(Long id) {
        try {
            categoryRepo.deleteById(id);
            log.info("delete category success");
            return ResultResp.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResultResp.fail();
        }


    }

    @Override
    public ResultResp getCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category == null) {
            return ResultResp.fail();
        }
        CategoryResp categoryResp = categoryMapper.toCategoryResp(category);
        return ResultResp.success(categoryResp);

    }
}
