package com.discovery.category.service.impl;

import com.discovery.category.common.Constant;
import com.discovery.category.common.Exception.CommonException;
import com.discovery.category.entity.Category;
import com.discovery.category.model.CategoryDto;
import com.discovery.category.repository.CategoryRepository;
import com.discovery.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    public CategoryRepository categoryRepository;
    @Autowired
    public void CategoryRepository(CategoryRepository categoryRepository){this.categoryRepository = categoryRepository;}

    @Override
    public void addCategory(CategoryDto dto){
        Category category = Optional.ofNullable(categoryRepository.findByCategoryId(dto.getCategory_id()))
                .map(s -> { s.setCategoryDesc(dto.getCategory_desc());
                            s.setCategoryName(dto.getCategory_name());
                            s.setCategoryOrder(dto.getCategory_order());
                            s.setUpdDt(LocalDateTime.now());
                            s.setUpdId("어드민 수정자");
                            return categoryRepository.save(s);})
                .orElseGet(() -> {return categoryRepository.save(Category.builder()
                                                                        .categoryId(dto.getCategory_id()).upperCategoryId(dto.getUpper_category_id())
                                                                        .categoryName(dto.getCategory_name()).categoryDesc(dto.getCategory_desc()).categoryLevel(dto.getCategory_level())
                                                                        .categoryOrder(dto.getCategory_order()).crtDt(LocalDateTime.now()).crtId("어드민 등록자").updDt(LocalDateTime.now()).build());});
    }

    @Override
    public CategoryDto getCategory(String categoryId){
        Category category = Optional.ofNullable(categoryRepository.findByCategoryId(categoryId)).orElseGet(Category::new);
        List<CategoryDto> categoryDtoList = categoryRepository.findByUpperCategoryIdAndCategoryLevelOrderByCategoryOrderAsc(categoryId , Constant.CATEGORY_TYPE.LOW.getDesc()).stream().map(CategoryDto::new).collect(Collectors.toList());
        return CategoryDto.builder()
                    .category_id(category.getCategoryId())
                    .category_name(category.getCategoryName())
                    .category_desc(category.getCategoryDesc())
                    .category_level(category.getCategoryLevel())
                    .upper_category_id(category.getUpperCategoryId())
                    .low_category(categoryDtoList)
                    .build();
    }

    @Override
    public List<CategoryDto> getCategory(){
        Optional<List<Category>> categoryEntityList = Optional.ofNullable(categoryRepository.findByCategoryLevelEquals("U"));
        List<CategoryDto> categoryDtoList = categoryEntityList.get().stream().map(CategoryDto::new).collect(Collectors.toList());
        categoryDtoList.forEach(s -> {
                                        s.setLow_category(Optional.ofNullable(categoryRepository.findByUpperCategoryIdAndCategoryLevelOrderByCategoryOrderAsc(s.getCategory_id() , "L"))
                                        .get().stream().map(CategoryDto::new).collect(Collectors.toList()));});
        return categoryDtoList;

    }

    @Override
    public void deleteCategory(String categoryId){
        Category category = Optional.ofNullable(categoryRepository.findByCategoryId(categoryId)).orElseThrow(NoSuchElementException::new);
        categoryRepository.delete(category);
    }
}
