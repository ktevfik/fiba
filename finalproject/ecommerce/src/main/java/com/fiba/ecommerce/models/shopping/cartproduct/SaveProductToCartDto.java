package com.fiba.ecommerce.models.shopping.cartproduct;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 02:47
 */
public class SaveProductToCartDto {

    private Long cartId;

    private Long productId;

    private int salesQuantity;


    public SaveProductToCartDto() {
    }

    public SaveProductToCartDto(Long cartId, Long productId, int salesQuantity) {
        this.cartId = cartId;
        this.productId = productId;
        this.salesQuantity = salesQuantity;
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

}
