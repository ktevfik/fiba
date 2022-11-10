package com.fiba.shoppingservice.converter.Cart;

import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;
import com.fiba.shoppingservice.entity.Cart;
import com.fiba.shoppingservice.entity.CartProduct;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 01:42
 */
public class CartMapperImpl implements CartMapper {
    public CartMapperImpl() {
    }

    public CartDto convertToCartDto(Cart cart) {
        if (cart == null) {
            return null;
        } else {
            CartDto cartDto = new CartDto();
            if (cart.getCartId() != null) {
                cartDto.setCartId(cart.getCartId());
            }
            List<CartProductDto> productDtoList = new ArrayList<>();

            for (CartProduct cartProduct : cart.getCartProducts()) {
                CartProductDto cartProductDto = new CartProductDto();
                cartProductDto.setCartProductId(cartProduct.getCartProductId());
                cartProductDto.setSalesQuantity(cartProduct.getSalesQuantity());
                cartProductDto.setLineAmount(cartProduct.getLineAmount());
                cartProductDto.setSalesPrice(cartProduct.getSalesPrice());
                cartProductDto.setCartId(cartProduct.getCart().getCartId());
                cartProductDto.setProductId(cartProduct.getProductId());
                productDtoList.add(cartProductDto);
            }

            cartDto.setCustomerName(cart.getCustomerName());
            cartDto.setTotalAmount(cart.getTotalAmount());
            cartDto.setCartProducts(productDtoList);
            cartDto.setPaid(cart.isPaid());
            return cartDto;
        }
    }

    public CartDto convertToCartDto(CartSaveRequestDto cartSaveRequestDto) {
        if (cartSaveRequestDto == null) {
            return null;
        } else {
            CartDto cartDto = new CartDto();
            cartDto.setCustomerName(cartSaveRequestDto.getCustomerName());
            return cartDto;
        }
    }

    public Cart convertToCart(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        } else {
            Cart cart = new Cart();
            cart.setCartId(cartDto.getCartId());
            cart.setCustomerName(cartDto.getCustomerName());
            cart.setTotalAmount(cartDto.getTotalAmount());
            return cart;
        }
    }

    @Override
    public List<CartDto> convertToCartDtoList(List<Cart> carts) {
        if (carts == null) {
            return null;
        } else {
            List<CartDto> cartDtoList = new ArrayList<>();
            Iterator var3 = carts.iterator();
            while (var3.hasNext()) {
                Cart cart = (Cart) var3.next();
                cartDtoList.add(this.convertToCartDto(cart));
            }
            return cartDtoList;
        }
    }
}
