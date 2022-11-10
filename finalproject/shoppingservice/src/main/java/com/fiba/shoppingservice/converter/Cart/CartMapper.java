package com.fiba.shoppingservice.converter.Cart;

import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.entity.Cart;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 21:03
 */
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDto convertToCartDto(Cart cart);

    CartDto convertToCartDto(CartSaveRequestDto cartSaveRequestDto);

    Cart convertToCart(CartDto cartDto);

    List<CartDto> convertToCartDtoList(List<Cart> carts);
}
