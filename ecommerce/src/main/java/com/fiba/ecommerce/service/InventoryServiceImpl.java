package com.fiba.ecommerce.service;

import com.fiba.ecommerce.enums.ErrorMessage;
import com.fiba.ecommerce.exception.exceptions.CategoryNotFoundException;
import com.fiba.ecommerce.models.inventory.category.CategoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 13:21
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    @Override
    public List<CategoryDto> getCategories() {
        String url = "http://localhost:8081/api/inventory/categories";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CategoryDto[]> responseEntity = restTemplate.getForEntity(url, CategoryDto[].class);
        List<CategoryDto> categoryDtoList = List.of(Objects.requireNonNull(responseEntity.getBody()));
        return categoryDtoList;
    }

    @Override
    public CategoryDto createCategory() {
        String url = "http://localhost:8081/api/inventory/categories";

        RestTemplate restTemplate = new RestTemplate();

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryName("Test Category");

        ResponseEntity<CategoryDto> responseEntity = restTemplate.postForEntity(url, categoryDto, CategoryDto.class);

        return responseEntity.getBody();
    }

    @Override
    public void deleteCategory(Long id) {
        try {
            String url = "http://localhost:8081/api/inventory/categories/" + id;

            RestTemplate restTemplate = new RestTemplate();

            restTemplate.delete(url);
        } catch (Exception e) {
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }

    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        try {
            String url = "http://localhost:8081/api/inventory/categories/" + id;

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<CategoryDto> responseEntity = restTemplate.getForEntity(url, CategoryDto.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public void updateCategory(Long id) {
        try {
            String url = "http://localhost:8081/api/inventory/categories";

            RestTemplate restTemplate = new RestTemplate();

            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryName("Test Category 2");

            restTemplate.put(url, categoryDto);
        } catch (Exception e) {
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }
    }
}
