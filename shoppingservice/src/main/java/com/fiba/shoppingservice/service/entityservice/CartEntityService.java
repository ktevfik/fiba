package com.fiba.shoppingservice.service.entityservice;

import com.fiba.shoppingservice.converter.CartMapper;
import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.entity.Cart;
import com.fiba.shoppingservice.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:52
 */
@Service
public class CartEntityService {

    private final CartRepository cartRepository;

    public CartEntityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }


    public CartDto saveCart(CartDto cartDto) {

        Cart cart = CartMapper.INSTANCE.convertToCart(cartDto);
        // initialize savedCartDto with starter values
        // totalAmount - cartProducts - isPaid
        cart.setPaid(false);
        cart.setTotalAmount(BigDecimal.ZERO);
        cart.setCartProducts(new ArrayList<>());

        Cart savedCart = cartRepository.save(cart);

        CartDto savedCartDto = CartMapper.INSTANCE.convertToCartDto(savedCart);

        // to do converter liste cevirirken duzgun calısmıyor

        return savedCartDto;

    }
}
