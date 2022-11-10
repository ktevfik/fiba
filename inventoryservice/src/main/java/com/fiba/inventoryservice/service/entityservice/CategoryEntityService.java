package com.fiba.inventoryservice.service.entityservice;

import com.fiba.inventoryservice.entity.Category;
import com.fiba.inventoryservice.enums.ErrorMessage;
import com.fiba.inventoryservice.exception.exceptions.CategoryAlreadyExistException;
import com.fiba.inventoryservice.exception.exceptions.CategoryNotFoundException;
import com.fiba.inventoryservice.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 04:03
 */
@Service
public class CategoryEntityService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CategoryRepository categoryRepository;

    public CategoryEntityService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category getByIdWithControl(Long id) {
        log.info("Get By Id process started for CategoryId: {}", id);

        Optional<Category> categoryOptional = categoryRepository.findById(id);

        Category category;

        if (categoryOptional.isPresent()) {

            category = categoryOptional.get();
            log.info("Category found for CategoryId: {}", id);
        } else {
            log.error(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }
        return category;
    }

    public Category save(Category category) {
        log.info("Save process started for category: {}", category.getCategoryName());
        try {

            return categoryRepository.save(category);
        } catch (Exception e) {
            log.error(ErrorMessage.CATEGORY_ALREADY_EXIST_MESSAGE.getMessage());
            throw new CategoryAlreadyExistException(ErrorMessage.CATEGORY_ALREADY_EXIST);
        }

    }

    public Category update(Category category) {
        log.info("Update process started for category: {}", category.getCategoryName());
        try {
            categoryRepository.updateCategoryName(category.getCategoryId(), category.getCategoryName());
        } catch (Exception e) {
            log.error(ErrorMessage.CATEGORY_NOT_FOUND.getMessage());
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }
        return category;
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public boolean existById(Long id) {
        return categoryRepository.existsById(id);
    }

    public boolean isCategoryExist(Long id, String name) {
        return categoryRepository.isCategoryExist(id, name).isPresent();
    }
}
