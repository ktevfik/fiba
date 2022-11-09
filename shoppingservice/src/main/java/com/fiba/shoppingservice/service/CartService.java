package com.fiba.shoppingservice.service;

import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:55
 */
public interface CartService {

    CartDto createCart(CartSaveRequestDto cartSaveRequestDto);
}
