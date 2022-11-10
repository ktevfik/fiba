package com.fiba.shoppingservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:18
 */
@Entity
@Table(name = "CART_PRODUCT")
public class CartProduct {

    @Id
    @SequenceGenerator(name = "CART_PRODUCT", sequenceName = "CART_PRODUCT_SEQ")
    @GeneratedValue(generator = "CART_PRODUCT")
    @Column(name = "CART_PRODUCT_ID")
    private Long cartProductId;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "SALES_QUANTITY", nullable = false)
    private int salesQuantity;

    @Column(name = "SALES_PRICE", nullable = false)
    private BigDecimal salesPrice;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal lineAmount;

    public CartProduct() {
    }

    public CartProduct(Long productId, int salesQuantity, BigDecimal salesPrice, BigDecimal lineAmount) {
        this.productId = productId;
        this.salesQuantity = salesQuantity;
        this.salesPrice = salesPrice;
        this.lineAmount = lineAmount;
    }

    public CartProduct(Long cartProductId, Long productId, int salesQuantity, BigDecimal salesPrice, BigDecimal lineAmount) {
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
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
