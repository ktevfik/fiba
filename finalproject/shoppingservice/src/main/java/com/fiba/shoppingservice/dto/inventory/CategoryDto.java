package com.fiba.shoppingservice.dto.inventory;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:40
 */
public class CategoryDto {

    private long categoryId;
    private String categoryName;

    public CategoryDto() {
    }

    public CategoryDto(long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
