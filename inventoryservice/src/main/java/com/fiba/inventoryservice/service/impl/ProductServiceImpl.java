package com.fiba.inventoryservice.service.impl;

import com.fiba.inventoryservice.repository.ProductRepository;
import com.fiba.inventoryservice.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:26
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
