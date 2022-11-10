package com.fiba.ecommerce.service;

import com.fiba.ecommerce.models.inventory.category.CategoryDto;
import com.fiba.ecommerce.models.inventory.product.ProductDto;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 13:21
 */
public interface InventoryService {
    List<CategoryDto> getCategories();

    CategoryDto createCategory();

    void deleteCategory(Long id);

    CategoryDto getCategoryById(Long id);

    void updateCategory(Long id);

    List<ProductDto> getProducts();

    ProductDto createProduct();

    void deleteProduct(Long id);

    ProductDto getProductById(Long id);

    List<ProductDto> getProductsByCategoryId(Long id);
}
