package com.fiba.ecommerce.controller;

import com.fiba.ecommerce.models.SuccessMessage;
import com.fiba.ecommerce.service.ShoppingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 14:33
 */
@Controller
@RequestMapping("/ecommerce/shopping")
public class ShoppingController {

    private final ShoppingService shoppingService;

    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }

    @RequestMapping(value = "/carts/create", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> createCart() {
        return new ResponseEntity<>(shoppingService.createCart(), null, 200);
    }

    @RequestMapping(value = "/carts/get/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getCarts() {
        return new ResponseEntity<>(shoppingService.getCarts(), null, 200);
    }

    @RequestMapping(value = "/carts/get/cart/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getCartById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(shoppingService.getCartById(id), null, 200);
    }

    @RequestMapping(value = "/carts/delete/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long id) {
        shoppingService.deleteCart(id);
        return new ResponseEntity<>(new SuccessMessage(), null, 200);
    }

    @RequestMapping(value = "/carts/{cartId}/products", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getProductsByCartId(@PathVariable("cartId") Long cartId) {
        return new ResponseEntity<>(shoppingService.getProductsByCartId(cartId), null, 200);
    }

    @RequestMapping(value = "/carts/add/product", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProductToCart() {

        return new ResponseEntity<>(shoppingService.addProductToCart(), null, 200);
    }

    @RequestMapping(value = "/carts/checkout/{cartId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> checkoutCart(@PathVariable("cartId") Long cartId) {
        return new ResponseEntity<>(shoppingService.checkoutCart(cartId), null, 200);
    }

    @RequestMapping(value = "/carts/{cartId}/products/delete/{productId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> deleteProductFromCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
        shoppingService.deleteProductFromCart(cartId, productId);
        return new ResponseEntity<>(new SuccessMessage(), null, 200);
    }

}
