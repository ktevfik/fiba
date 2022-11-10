package com.fiba.inventoryservice.converter.category;

import com.fiba.inventoryservice.dto.category.CategoryDto;
import com.fiba.inventoryservice.dto.category.CategorySaveRequestDto;
import com.fiba.inventoryservice.entity.Category;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 02:26
 */
public class CategoryMapperImpl implements CategoryMapper {
    public CategoryMapperImpl() {
    }

    @Override
    public List<CategoryDto> convertToCategoryDtoList(List<Category> categoryList) {
        if (categoryList == null) {
            return null;
        } else {
            List<CategoryDto> list = new ArrayList(categoryList.size());
            Iterator var3 = categoryList.iterator();

            while (var3.hasNext()) {
                Category category = (Category) var3.next();
                list.add(this.convertToCategoryDto(category));
            }

            return list;
        }
    }

    @Override

    public CategoryDto convertToCategoryDto(Category category) {
        if (category == null) {
            return null;
        } else {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(category.getCategoryId());
            categoryDto.setCategoryName(category.getCategoryName());
            return categoryDto;
        }
    }

    @Override
    public Category convertToCategory(CategorySaveRequestDto categorySaveRequestDto) {
        if (categorySaveRequestDto == null) {
            return null;
        } else {
            Category category = new Category();
            category.setCategoryName(categorySaveRequestDto.getCategoryName());
            return category;
        }
    }

    @Override
    public Category convertToCategory(CategoryDto categoryDto) {
        if (categoryDto == null) {
            return null;
        } else {
            Category category = new Category();
            category.setCategoryId(categoryDto.getCategoryId());
            category.setCategoryName(categoryDto.getCategoryName());
            return category;
        }
    }
}
