package com.fiba.ecommerce.models.shopping.cart;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    private boolean isPaid;

    @JsonProperty("cartProducts")
    private List<CartProductDto> cartProducts;

    public CartDto() {
    }

    public CartDto(long cartId, String customerName, BigDecimal totalAmount) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
    }

    public CartDto(long cartId, String customerName, BigDecimal totalAmount, List<CartProductDto> cartProducts) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.cartProducts = cartProducts;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
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

    public List<CartProductDto> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductDto> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
