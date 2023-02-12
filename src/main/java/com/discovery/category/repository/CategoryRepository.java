package com.discovery.category.repository;

import com.discovery.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    // 상위 카테고리 조회
    List<Category> findByCategoryLevelEquals(String level);

    // 카테고리 정보 조회
    Category findByCategoryId(String CategoryId);

    // 하위 카테고리 조회
    List<Category> findByUpperCategoryIdAndCategoryLevelOrderByCategoryOrderAsc(String CategoryId , String level);

    // 하위 카테고리 카운트 조회
    int countByUpperCategoryId(String categoryId);

}
