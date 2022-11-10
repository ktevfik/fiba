package com.fiba.inventoryservice.dto.category;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 04:32
 */
public class CategorySaveRequestDto {

    private String categoryName;

    public CategorySaveRequestDto() {
    }

    public CategorySaveRequestDto(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
