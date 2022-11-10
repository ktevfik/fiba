package com.fiba.shoppingservice.service.entityservice;

import com.fiba.shoppingservice.entity.CartProduct;
import com.fiba.shoppingservice.enums.ErrorMessage;
import com.fiba.shoppingservice.exception.exceptions.CartProductNotFoundException;
import com.fiba.shoppingservice.repository.CartProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:53
 */
@Service
public class CartProductEntityService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CartProductRepository cartProductRepository;

    public CartProductEntityService(CartProductRepository cartProductRepository) {
        this.cartProductRepository = cartProductRepository;
    }

    public CartProduct findCartProductById(Long id) {
        if (!existById(id)) {
            log.error(ErrorMessage.CART_PRODUCT_NOT_FOUND.getMessage());
            throw new CartProductNotFoundException(ErrorMessage.CART_PRODUCT_NOT_FOUND);
        }

        CartProduct cartProduct = cartProductRepository.findById(id).get();

        log.info("CartProduct found by id: " + id);
        return cartProduct;
    }

    public boolean existById(Long id) {
        return cartProductRepository.existsById(id);
    }

    public CartProduct saveCartProduct(CartProduct cartProduct) {
        CartProduct savedCartProduct = cartProductRepository.save(cartProduct);

        log.info("CartProduct saved cart successfully: {}", savedCartProduct);
        return savedCartProduct;
    }

    public CartProduct getCartProductByCartIdAndCartProductId(long cartId, long cartProductId) {
        Optional<CartProduct> cartProduct = cartProductRepository.findCartProductByCartIdAndCartProductId(cartId, cartProductId);

        if (!cartProduct.isPresent()) {
            log.error(ErrorMessage.CART_PRODUCT_NOT_FOUND.getMessage());
            throw new CartProductNotFoundException(ErrorMessage.CART_PRODUCT_NOT_FOUND);
        }

        log.info("CartProduct found successfully: {}", cartProduct.get());
        return cartProduct.get();
    }

    public void deleteCartProductById(Long cartProductId) {
        if (!existById(cartProductId)) {
            log.error(ErrorMessage.CART_PRODUCT_NOT_FOUND.getMessage());
            throw new CartProductNotFoundException(ErrorMessage.CART_PRODUCT_NOT_FOUND);
        }

        log.info("CartProduct deleted successfully: {}", cartProductId);
        cartProductRepository.deleteById(cartProductId);
    }
}
