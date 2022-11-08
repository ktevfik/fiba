package com.fiba.inventoryservice.service.impl;

import com.fiba.inventoryservice.converter.CategoryMapper;
import com.fiba.inventoryservice.dto.category.CategoryDto;
import com.fiba.inventoryservice.dto.category.CategorySaveRequestDto;
import com.fiba.inventoryservice.entity.Category;
import com.fiba.inventoryservice.enums.ErrorMessage;
import com.fiba.inventoryservice.exception.exceptions.CategoryNotFoundException;
import com.fiba.inventoryservice.service.CategoryService;
import com.fiba.inventoryservice.service.entityservice.CategoryEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:26
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryEntityService categoryEntityService;

    public CategoryServiceImpl(CategoryEntityService categoryEntityService) {
        this.categoryEntityService = categoryEntityService;
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categoryList = categoryEntityService.findAll();

        List<CategoryDto> categoryDtoList = CategoryMapper.INSTANCE.convertToCategoryDtoList(categoryList);

        return categoryDtoList;
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        Category category = categoryEntityService.getByIdWithControl(id);

        CategoryDto categoryDto = CategoryMapper.INSTANCE.convertToCategoryDto(category);

        return categoryDto;
    }

    @Override
    public CategoryDto addCategory(CategorySaveRequestDto categorySaveRequestDto) {
        Category category = CategoryMapper.INSTANCE.convertToCategory(categorySaveRequestDto);

        Category savedCategory = categoryEntityService.save(category);

        CategoryDto savedCategoryDto = CategoryMapper.INSTANCE.convertToCategoryDto(savedCategory);

        return savedCategoryDto;
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryEntityService.getByIdWithControl(id);

        categoryEntityService.delete(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        controlIsCategoryExist(categoryDto);

        Category category = CategoryMapper.INSTANCE.convertToCategory(categoryDto);

        Category updatedCategory = categoryEntityService.save(category);

        CategoryDto updatedCategoryDto = CategoryMapper.INSTANCE.convertToCategoryDto(updatedCategory);

        return updatedCategoryDto;
    }

    private void controlIsCategoryExist(CategoryDto categoryDto) {
        Long id = categoryDto.getCategoryId();

        boolean isExist = categoryEntityService.existById(id);

        if (!isExist) {
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }
    }
}
