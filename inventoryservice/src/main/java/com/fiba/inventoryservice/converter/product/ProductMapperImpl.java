package com.fiba.inventoryservice.converter.product;

import com.fiba.inventoryservice.converter.category.CategoryMapper;
import com.fiba.inventoryservice.converter.category.CategoryMapperImpl;
import com.fiba.inventoryservice.dto.product.ProductDto;
import com.fiba.inventoryservice.dto.product.ProductSaveRequestDto;
import com.fiba.inventoryservice.entity.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 02:31
 */
public class ProductMapperImpl implements ProductMapper {

    private final CategoryMapper categoryMapper = new CategoryMapperImpl();

    public ProductMapperImpl() {
    }

    public List<ProductDto> convertToProductDtoList(List<Product> productList) {
        if (productList == null) {
            return null;
        } else {
            List<ProductDto> list = new ArrayList(productList.size());
            Iterator var3 = productList.iterator();

            while(var3.hasNext()) {
                Product product = (Product)var3.next();
                list.add(this.convertToProductDto(product));
            }

            return list;
        }
    }

    public ProductDto convertToProductDto(Product product) {
        if (product == null) {
            return null;
        } else {
            ProductDto productDto = new ProductDto();
            productDto.setProductId(product.getProductId());
            productDto.setProductName(product.getProductName());
            productDto.setSalesPrice(product.getSalesPrice());
            productDto.setCategoryDto(categoryMapper.convertToCategoryDto(product.getCategory()));
            return productDto;
        }
    }

    public ProductDto convertToProductDto(ProductSaveRequestDto productSaveRequestDto) {
        if (productSaveRequestDto == null) {
            return null;
        } else {
            ProductDto productDto = new ProductDto();
            productDto.setProductName(productSaveRequestDto.getProductName());
            productDto.setSalesPrice(productSaveRequestDto.getSalesPrice());
            productDto.setCategoryDto(productSaveRequestDto.getCategoryDto());
            return productDto;
        }
    }

    public Product convertToProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        } else {
            Product product = new Product();
            product.setProductId(productDto.getProductId());
            product.setProductName(productDto.getProductName());
            product.setSalesPrice(productDto.getSalesPrice());
            product.setCategory(categoryMapper.convertToCategory(productDto.getCategoryDto()));
            return product;
        }
    }
}