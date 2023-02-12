package com.discovery.category.service;

import com.discovery.category.entity.Category;
import com.discovery.category.model.CategoryDto;

import java.util.List;

public interface CategoryService {
    //카테고리 등록 및 수정
    public void addCategory(CategoryDto dto);

    //카테고리 조회
    public CategoryDto getCategory(String categoryId);

    //카테고리 전체 조회
    public List<CategoryDto> getCategory();

    //카테고리 삭제
    public void deleteCategory(String categoryId);
}
