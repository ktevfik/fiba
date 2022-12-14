package com.fiba.shoppingservice.dto.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:39
 */
public class ProductDto {

    private long productId;

    private String productName;

    private BigDecimal salesPrice;

    @JsonProperty("category")
    private CategoryDto categoryDto;

    public ProductDto() {
    }

    public ProductDto(long productId, String productName, BigDecimal salesPrice, CategoryDto categoryDto) {
        this.productId = productId;
        this.productName = productName;
        this.salesPrice = salesPrice;
        this.categoryDto = categoryDto;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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