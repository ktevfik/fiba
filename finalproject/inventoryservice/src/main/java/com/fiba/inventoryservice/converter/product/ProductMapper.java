package com.fiba.inventoryservice.converter.product;

import com.fiba.inventoryservice.dto.product.ProductDto;
import com.fiba.inventoryservice.dto.product.ProductSaveRequestDto;
import com.fiba.inventoryservice.entity.Product;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 23:24
 */
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductDto> convertToProductDtoList(List<Product> productList);

    ProductDto convertToProductDto(Product product);

    ProductDto convertToProductDto(ProductSaveRequestDto productSaveRequestDto);

    Product convertToProduct(ProductDto productDto);
}
