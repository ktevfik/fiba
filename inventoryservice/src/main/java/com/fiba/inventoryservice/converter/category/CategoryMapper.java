package com.fiba.inventoryservice.converter.category;

import com.fiba.inventoryservice.dto.category.CategoryDto;
import com.fiba.inventoryservice.dto.category.CategorySaveRequestDto;
import com.fiba.inventoryservice.entity.Category;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:55
 */
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    List<CategoryDto> convertToCategoryDtoList(List<Category> categoryList);

    CategoryDto convertToCategoryDto(Category category);

    Category convertToCategory(CategorySaveRequestDto categorySaveRequestDto);

    Category convertToCategory(CategoryDto categoryDto);
}
