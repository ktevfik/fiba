package com.fiba.inventoryservice.service.impl;

import com.fiba.inventoryservice.converter.CategoryMapper;
import com.fiba.inventoryservice.converter.ProductMapper;
import com.fiba.inventoryservice.dto.category.CategoryDto;
import com.fiba.inventoryservice.dto.product.ProductDto;
import com.fiba.inventoryservice.dto.product.ProductSaveRequestDto;
import com.fiba.inventoryservice.entity.Category;
import com.fiba.inventoryservice.entity.Product;
import com.fiba.inventoryservice.service.ProductService;
import com.fiba.inventoryservice.service.entityservice.ProductEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:26
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductEntityService productEntityService;

    public ProductServiceImpl(ProductEntityService productEntityService) {
        this.productEntityService = productEntityService;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productEntityService.findAll();

        List<ProductDto> productDtoList = ProductMapper.INSTANCE.convertToProductDtoList(products);

        return productDtoList;
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productEntityService.getByIdWithControl(id);

        ProductDto productDto = ProductMapper.INSTANCE.convertToProductDto(product);

        return productDto;
    }

    @Override
    public List<ProductDto> getProductsByCategoryId(Long id) {
        List<Product> products = productEntityService.findProductsByCategoryId(id);

        List<ProductDto> productDtoList = ProductMapper.INSTANCE.convertToProductDtoList(products);

        return productDtoList;
    }

    @Override
    public ProductDto createProduct(ProductSaveRequestDto productSaveRequestDto) {
        ProductDto productDto = ProductMapper.INSTANCE.convertToProductDto(productSaveRequestDto);

        Product savedProduct = productEntityService.save(productDto);

        Category category = savedProduct.getCategory();

        CategoryDto categoryDto = CategoryMapper.INSTANCE.convertToCategoryDto(category);

        ProductDto responseProductDto = ProductMapper.INSTANCE.convertToProductDto(savedProduct);

        responseProductDto.setCategoryDto(categoryDto);

        return responseProductDto;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productEntityService.getByIdWithControl(id);

        productEntityService.delete(product);
    }
}
