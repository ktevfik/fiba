package com.fiba.shoppingservice.converter;

import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 21:03
 */
@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto convertToCartDto(Cart cart);

    CartDto convertToCartDto(CartSaveRequestDto cartSaveRequestDto);

    Cart convertToCart(CartDto cartDto);
}
