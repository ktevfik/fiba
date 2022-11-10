package com.fiba.shoppingservice.converter.CartProduct;

import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;
import com.fiba.shoppingservice.entity.CartProduct;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 03:23
 */
public interface CartProductMapper {
    CartProductMapper INSTANCE = Mappers.getMapper(CartProductMapper.class);

    CartProductDto convertToCartProductDto(CartProduct cartProduct);

    List<CartProductDto> convertToCartProductDtoList(List<CartProduct> cartProducts);
}
