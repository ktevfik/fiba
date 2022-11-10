package com.fiba.ecommerce.models.shopping.cart;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 21:00
 */
public class CartSaveRequestDto {
    private String customerName;

    public CartSaveRequestDto() {
    }

    public CartSaveRequestDto(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
