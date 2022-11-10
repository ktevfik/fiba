package com.fiba.shoppingservice.service.impl;

import com.fiba.shoppingservice.converter.Cart.CartMapper;
import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.dto.cart.SaveProductToCartDto;
import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;
import com.fiba.shoppingservice.entity.Cart;
import com.fiba.shoppingservice.service.CartService;
import com.fiba.shoppingservice.service.entityservice.CartEntityService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<CartDto> getAllCarts() {
        List<Cart> carts = cartEntityService.getAllCarts();

        List<CartDto> cartDtoList = CartMapper.INSTANCE.convertToCartDtoList(carts);

        return cartDtoList;
    }

    @Override
    public CartDto getCartById(long id) {
        Cart cart = cartEntityService.getCartById(id);

        CartDto cartDto = CartMapper.INSTANCE.convertToCartDto(cart);

        return cartDto;
    }

    @Override
    public void deleteCartById(long id) {
        cartEntityService.deleteCartById(id);
    }

    @Override
    public CartDto addProductToCart(SaveProductToCartDto saveProductToCartDto) {
        CartDto cartDto = cartEntityService.addProductToCart(saveProductToCartDto);

        return cartDto;
    }

    @Override
    public void removeProductFromCart(long cartId, long productId) {
        cartEntityService.removeProductFromCart(cartId, productId);
    }

    @Override
    public CartDto checkoutCart(long cartId) {
        return cartEntityService.removeAllCartProductsAndCheckoutCart(cartId);
    }

    @Override
    public CartProductDto getCartProductById(long cartProductId) {
        return cartEntityService.getCartProductById(cartProductId);
    }
}
