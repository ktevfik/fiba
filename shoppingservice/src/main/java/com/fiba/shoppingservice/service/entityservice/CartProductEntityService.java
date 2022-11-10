package com.fiba.shoppingservice.service.entityservice;

import com.fiba.shoppingservice.entity.CartProduct;
import com.fiba.shoppingservice.enums.ErrorMessage;
import com.fiba.shoppingservice.exception.exceptions.CartProductNotFoundException;
import com.fiba.shoppingservice.repository.CartProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:53
 */
@Service
public class CartProductEntityService {

    private final CartProductRepository cartProductRepository;

    public CartProductEntityService(CartProductRepository cartProductRepository) {
        this.cartProductRepository = cartProductRepository;
    }

    public CartProduct findCartProductById(Long id) {
        if (!existById(id)) {
            throw new CartProductNotFoundException(ErrorMessage.CART_PRODUCT_NOT_FOUND);
        }

        CartProduct cartProduct = cartProductRepository.findById(id).get();

        return cartProduct;
    }

    public boolean existById(Long id) {
        return cartProductRepository.existsById(id);
    }

    public CartProduct saveCartProduct(CartProduct cartProduct) {
        CartProduct savedCartProduct = cartProductRepository.save(cartProduct);

        return savedCartProduct;
    }

    public CartProduct getCartProductByCartIdAndCartProductId(long cartId, long cartProductId) {
        Optional<CartProduct> cartProduct = cartProductRepository.findCartProductByCartIdAndCartProductId(cartId, cartProductId);

        if (!cartProduct.isPresent()) {
            throw new CartProductNotFoundException(ErrorMessage.CART_PRODUCT_NOT_FOUND);
        }

        return cartProduct.get();
    }

    public void deleteCartProductById(Long cartProductId) {
        cartProductRepository.deleteById(cartProductId);
    }
}
