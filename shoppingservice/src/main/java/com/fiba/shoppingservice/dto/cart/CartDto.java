package com.fiba.shoppingservice.dto.cart;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiba.shoppingservice.dto.inventory.ProductDto;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:43
 */
public class CartDto {

    private long cartId;

    private String customerName;

    private BigDecimal totalAmount;

    @JsonProperty("cartProducts")
    private List<ProductDto> cartProducts;

    public CartDto() {
    }

    public CartDto(long cartId, String customerName, BigDecimal totalAmount) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    public CartDto(long cartId, String customerName, BigDecimal totalAmount, List<ProductDto> cartProducts) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.cartProducts = cartProducts;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<ProductDto> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<ProductDto> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
