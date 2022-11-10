package com.fiba.inventoryservice.service;

import com.fiba.inventoryservice.dto.category.CategoryDto;
import com.fiba.inventoryservice.dto.category.CategorySaveRequestDto;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:25
 */
public interface CategoryService {

    List<CategoryDto> getCategories();

    CategoryDto getCategoryById(Long id);

    CategoryDto addCategory(CategorySaveRequestDto categorySaveRequestDto);

    void deleteCategory(Long id);

    CategoryDto updateCategory(Long id, CategorySaveRequestDto categorySaveRequestDto);
}
