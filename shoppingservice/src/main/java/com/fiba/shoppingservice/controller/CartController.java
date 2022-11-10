package com.fiba.shoppingservice.controller;

import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.dto.cart.SaveProductToCartDto;
import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;
import com.fiba.shoppingservice.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @PostMapping("/carts/create")
    public ResponseEntity<?> createCart(@RequestBody CartSaveRequestDto cartSaveRequestDto) {
        CartDto cartDto = cartService.createCart(cartSaveRequestDto);

        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @GetMapping("/carts")
    public ResponseEntity<?> getAllCarts() {
        List<CartDto> cartDtos = cartService.getAllCarts();

        return new ResponseEntity<>(cartDtos, HttpStatus.OK);
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<?> getCartById(@PathVariable long id) {
        CartDto cartDto = cartService.getCartById(id);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @GetMapping("/carts/products/{cartProductId}")
    public ResponseEntity<?> getCartProductById(@PathVariable long cartProductId) {
        CartProductDto cartProductDto = cartService.getCartProductById(cartProductId);

        return new ResponseEntity<>(cartProductDto, HttpStatus.OK);
    }

    @PostMapping("/carts/add")
    public ResponseEntity<?> addProductToCart(@RequestBody SaveProductToCartDto saveProductToCartDto) {
        CartDto cartDto = cartService.addProductToCart(saveProductToCartDto);

        return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
    }

    @PostMapping("/carts/checkout/{cartId}")
    public ResponseEntity<?> checkoutCart(@PathVariable long cartId) {
        CartDto cartDto = cartService.checkoutCart(cartId);

        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<?> deleteCartById(@PathVariable long id) {
        cartService.deleteCartById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/carts/{cartId}/remove/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable long cartId, @PathVariable long productId) {
        cartService.removeProductFromCart(cartId, productId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
