package com.fiba.inventoryservice.entity;

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
 * @created 08/11/2022 - 03:19
 */
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @SequenceGenerator(name = "PRODUCT", sequenceName = "PRODUCT_ID_SEQ")
    @GeneratedValue(generator = "PRODUCT")
    @Column(name = "PRODUCT_ID")
    private long productId;

    @Column(name = "PRODUCT_NAME", length = 50, nullable = false)
    private String productName;


    @Column(name = "PRODUCT_PRICE", nullable = false)
    private BigDecimal salesPrice;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    public Product() {
    }

    public Product(long productId, String productName, BigDecimal salesPrice, Category category) {
        this.productId = productId;
        this.productName = productName;
        this.salesPrice = salesPrice;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
