package com.fiba.shoppingservice.service.impl;

import com.fiba.shoppingservice.converter.CartMapper;
import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.entity.Cart;
import com.fiba.shoppingservice.service.CartService;
import com.fiba.shoppingservice.service.entityservice.CartEntityService;
import org.springframework.stereotype.Service;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:55
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartEntityService cartEntityService;

    public CartServiceImpl(CartEntityService cartEntityService) {
        this.cartEntityService = cartEntityService;
    }

    @Override
    public CartDto createCart(CartSaveRequestDto cartSaveRequestDto) {
        CartDto cartDto = CartMapper.INSTANCE.convertToCartDto(cartSaveRequestDto);

        CartDto savedCart = cartEntityService.saveCart(cartDto);

        return savedCart;
    }
}
