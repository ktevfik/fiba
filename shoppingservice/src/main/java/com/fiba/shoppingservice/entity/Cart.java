package com.fiba.shoppingservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:14
 */

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    @SequenceGenerator(name = "CATEGORY", sequenceName = "CATEGORY_SEQ")
    @GeneratedValue(generator = "CATEGORY")
    @Column(name = "CART_ID")
    private Long cartId;

    @Column(name = "CUSTOMER_NAME", length = 50, nullable = false)
    private String customerName;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "IS_PAID")
    private boolean isPaid;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartProduct> cartProducts;

    public Cart() {
    }

    public Cart(String customerName, BigDecimal totalAmount, boolean isPaid) {
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
    }

    public Cart(Long cartId, String customerName, BigDecimal totalAmount, boolean isPaid) {
        this.cartId = cartId;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public List<CartProduct> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
    }
}
