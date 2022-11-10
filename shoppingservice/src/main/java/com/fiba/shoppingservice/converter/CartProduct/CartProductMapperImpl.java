package com.fiba.shoppingservice.converter.CartProduct;

import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;
import com.fiba.shoppingservice.entity.CartProduct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 04:58
 */
public class CartProductMapperImpl implements CartProductMapper {
    public CartProductMapperImpl() {
    }

    public CartProductDto convertToCartProductDto(CartProduct cartProduct) {
        if (cartProduct == null) {
            return null;
        } else {
            CartProductDto cartProductDto = new CartProductDto();
            cartProductDto.setCartProductId(cartProduct.getCartProductId());
            cartProductDto.setCartId(cartProduct.getCart().getCartId());
            cartProductDto.setProductId(cartProduct.getProductId());
            cartProductDto.setSalesQuantity(cartProduct.getSalesQuantity());
            cartProductDto.setSalesPrice(cartProduct.getSalesPrice());
            cartProductDto.setLineAmount(cartProduct.getLineAmount());
            return cartProductDto;
        }
    }

    @Override
    public List<CartProductDto> convertToCartProductDtoList(List<CartProduct> cartProducts) {
        if (cartProducts == null) {
            return null;
        } else {
            List<CartProductDto> list = new ArrayList(cartProducts.size());
            Iterator var3 = cartProducts.iterator();

            while(var3.hasNext()) {
                CartProduct cartProduct = (CartProduct)var3.next();
                list.add(this.convertToCartProductDto(cartProduct));
            }

            return list;
        }
    }
}

