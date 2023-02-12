package com.discovery.category.model;

import com.discovery.category.entity.Category;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CategoryDto {
    @ApiModelProperty(value="카테고리 ID",example = "CA00001" , required = true) private String category_id;
    @ApiModelProperty(value="상위 카테고리 ID",example = "" , required = false) private String upper_category_id;
    @ApiModelProperty(value="카테고리 명" ,example = "가전"     , required = true) private String category_name;
    @ApiModelProperty(value="카테고리 설명",example = "가전 전체 카테고리 입니다." , required = false) private String category_desc;
    @ApiModelProperty(value="카테고리 레벨(UPPER , LOW)",example = "U" , allowableValues = "U , L" , required = true) private String category_level;
    @ApiModelProperty(value="카테고리 순번" , required = false) private Integer category_order;

    //하위 카테고리 리스트
    private List<CategoryDto> low_category;

    public CategoryDto(Category entity){
        category_id = entity.getCategoryId();;
        upper_category_id = entity.getUpperCategoryId();
        category_name = entity.getCategoryName();
        category_desc = entity.getCategoryDesc();
        category_level = entity.getCategoryLevel();
        category_order = entity.getCategoryOrder();
    }
}
