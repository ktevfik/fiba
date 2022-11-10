package com.fiba.ecommerce.service;

import com.fiba.ecommerce.models.shopping.cart.CartDto;
import com.fiba.ecommerce.models.shopping.cartproduct.CartProductDto;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 14:34
 */
public interface ShoppingService {
    CartDto createCart();

    List<CartDto> getCarts();

    CartDto getCartById(Long id);

    void deleteCart(Long id);

    List<CartProductDto> getProductsByCartId(Long cartId);

    CartDto addProductToCart();

    CartDto checkoutCart(Long cartId);

    void deleteProductFromCart(Long cartId, Long productId);
}
