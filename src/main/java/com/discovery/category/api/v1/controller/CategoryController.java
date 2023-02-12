package com.discovery.category.api.v1.controller;

import com.discovery.category.common.Constant;
import com.discovery.category.common.Exception.CommonException;
import com.discovery.category.common.annotation.DiscoveryApi;
import com.discovery.category.common.result.ServiceResult;
import com.discovery.category.model.CategoryDto;
import com.discovery.category.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags="카테고리", value="카테고리 관련 API")
@RequestMapping("/api/v1")
@Slf4j
@RestController
public class CategoryController {

    public CategoryService categoryService;
    @Autowired
    public void CategoryService(CategoryService categoryService){this.categoryService = categoryService;}

    @PostMapping("/category")
    @ApiOperation(value="카테고리 등록", notes="카테고리 등록")
    @DiscoveryApi
    public ServiceResult<?> createCategory(@RequestBody CategoryDto category){
        if(category.getCategory_id() == null){
            throw new CommonException(Constant.RESULT_CODE.INVALID_PARAMS);
        }
        categoryService.addCategory(category);
        return new ServiceResult<>();
    }

    @GetMapping("/category")
    @ApiOperation(value="카테고리 조회", notes="카테고리 조회")
    @DiscoveryApi
    public ServiceResult<List<CategoryDto>> readCategory(){
        List<CategoryDto> list = categoryService.getCategory();
        return new ServiceResult<>(list);
    }

    @GetMapping("/category/{category_id}")
    @ApiOperation(value="개별 카테고리 조회", notes="개별 카테고리 조회")
    @DiscoveryApi
    public ServiceResult<?> readCategoryId(@ApiParam(value="카테고리 ID", required=true) @PathVariable(value="category_id",required=true) String categoryId){
        if(categoryId == null){
            throw new CommonException(Constant.RESULT_CODE.INVALID_PARAMS);
        }

        CategoryDto dto = categoryService.getCategory(categoryId);
        return new ServiceResult<CategoryDto>(dto);
    }

    @PatchMapping("/category/{category_id}")
    @ApiOperation(value="카테고리 수정", notes="카테고리 수정")
    @DiscoveryApi
    public ServiceResult<?> updateCategory(@RequestBody CategoryDto category){
        if(category.getCategory_id() == null){
            throw new CommonException(Constant.RESULT_CODE.INVALID_PARAMS);
        }
        categoryService.addCategory(category);
        return new ServiceResult<>();
    }

    @DeleteMapping("/category/{category_id}")
    @ApiOperation(value="카테고리 삭제", notes="카테고리 삭제")
    @DiscoveryApi
    public ServiceResult<?> deleteCategory(@ApiParam(value="카테고리 ID", required=true) @PathVariable(value="category_id",required=true) String categoryId){
        if(categoryId == null){
            throw new CommonException(Constant.RESULT_CODE.INVALID_PARAMS);
        }
        categoryService.deleteCategory(categoryId);
        return new ServiceResult<>();
    }
}
