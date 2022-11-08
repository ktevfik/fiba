package com.fiba.inventoryservice.controller;

import com.fiba.inventoryservice.dto.category.CategoryDto;
import com.fiba.inventoryservice.dto.category.CategorySaveRequestDto;
import com.fiba.inventoryservice.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:29
 */
@RestController
@RequestMapping("/api/inventory/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getCategories() {

        List<CategoryDto> categoryDtoList = categoryService.getCategories();

        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {

        CategoryDto categoryDto = categoryService.getCategoryById(id);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody CategorySaveRequestDto categorySaveRequestDto) {
        CategoryDto savedCategory = categoryService.addCategory(categorySaveRequestDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto updatedCategory = categoryService.updateCategory(categoryDto);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
}
