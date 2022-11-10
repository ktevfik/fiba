package com.fiba.inventoryservice.service;

import com.fiba.inventoryservice.dto.product.ProductDto;
import com.fiba.inventoryservice.dto.product.ProductSaveRequestDto;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:26
 */
public interface ProductService {
    List<ProductDto> getAllProducts();

    ProductDto getProductById(Long id);

    List<ProductDto> getProductsByCategoryId(Long id);

    ProductDto createProduct(ProductSaveRequestDto productSaveRequestDto);

    void deleteProduct(Long id);
}
