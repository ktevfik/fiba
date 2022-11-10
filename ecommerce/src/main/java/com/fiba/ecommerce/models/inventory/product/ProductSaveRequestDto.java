package com.fiba.ecommerce.models.inventory.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiba.ecommerce.models.inventory.category.CategoryDto;

import java.math.BigDecimal;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 00:03
 */
public class ProductSaveRequestDto {
    private String productName;

    private BigDecimal salesPrice;

    @JsonProperty("category")
    private CategoryDto categoryDto;

    public ProductSaveRequestDto() {
    }

    public ProductSaveRequestDto(String productName, BigDecimal salesPrice, CategoryDto categoryDto) {
        this.productName = productName;
        this.salesPrice = salesPrice;
        this.categoryDto = categoryDto;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
