package com.fiba.shoppingservice.controller;

import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:53
 */
@RestController
@RequestMapping("/api/shopping")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart/create")
    public ResponseEntity<?> createCart(@RequestBody CartSaveRequestDto cartSaveRequestDto) {
        CartDto cartDto = cartService.createCart(cartSaveRequestDto);

        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

}
