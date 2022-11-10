package com.fiba.ecommerce.models.shopping.cart;

import java.math.BigDecimal;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:47
 */
public class CartProductDto {

    private Long cartProductId;

    private Long cartId;

    private Long productId;

    private int salesQuantity;

    private BigDecimal salesPrice;

    private BigDecimal lineAmount;

    public CartProductDto() {
    }

    public CartProductDto(Long productId, int salesQuantity, BigDecimal salesPrice, BigDecimal lineAmount) {
        this.productId = productId;
        this.salesQuantity = salesQuantity;
        this.salesPrice = salesPrice;
        this.lineAmount = lineAmount;
    }

    public CartProductDto(Long cartProductId, Long productId, int salesQuantity, BigDecimal salesPrice, BigDecimal lineAmount) {
        this.cartProductId = cartProductId;
        this.productId = productId;
        this.salesQuantity = salesQuantity;
        this.salesPrice = salesPrice;
        this.lineAmount = lineAmount;
    }

    public Long getCartProductId() {
        return cartProductId;
    }

    public void setCartProductId(Long cartProductId) {
        this.cartProductId = cartProductId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(int salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public BigDecimal getLineAmount() {
        return lineAmount;
    }

    public void setLineAmount(BigDecimal lineAmount) {
        this.lineAmount = lineAmount;
    }
}
