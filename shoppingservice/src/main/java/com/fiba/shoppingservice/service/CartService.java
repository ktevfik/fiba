package com.fiba.shoppingservice.service;

import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.dto.cart.SaveProductToCartDto;
import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:55
 */
public interface CartService {

    CartDto createCart(CartSaveRequestDto cartSaveRequestDto);

    List<CartDto> getAllCarts();

    CartDto getCartById(long id);

    void deleteCartById(long id);

    CartDto addProductToCart(SaveProductToCartDto saveProductToCartDto);

    void removeProductFromCart(long cartId, long productId);

    CartDto checkoutCart(long cartId);

    CartProductDto getCartProductById(long cartProductId);
}
