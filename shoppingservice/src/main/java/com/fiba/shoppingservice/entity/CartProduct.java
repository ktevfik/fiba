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
}
