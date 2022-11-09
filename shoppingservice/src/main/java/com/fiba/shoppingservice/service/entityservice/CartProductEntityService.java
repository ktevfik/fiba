package com.fiba.shoppingservice.service.entityservice;

import com.fiba.shoppingservice.repository.CartProductRepository;
import org.springframework.stereotype.Service;

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
}
