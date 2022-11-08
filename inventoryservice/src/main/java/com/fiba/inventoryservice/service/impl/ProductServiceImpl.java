package com.fiba.inventoryservice.service.impl;

import com.fiba.inventoryservice.converter.product.ProductMapper;
import com.fiba.inventoryservice.dto.product.ProductDto;
import com.fiba.inventoryservice.dto.product.ProductSaveRequestDto;
import com.fiba.inventoryservice.entity.Product;
import com.fiba.inventoryservice.enums.ErrorMessage;
import com.fiba.inventoryservice.exception.exceptions.ProductNotFoundException;
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

        ProductDto responseProductDto = ProductMapper.INSTANCE.convertToProductDto(savedProduct);

        return responseProductDto;
    }

    @Override
    public void deleteProduct(Long id) {
        if (!productEntityService.existById(id)) {
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }

        productEntityService.delete(id);
    }
}
