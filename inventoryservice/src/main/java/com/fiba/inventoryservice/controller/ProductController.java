package com.fiba.inventoryservice.controller;

import com.fiba.inventoryservice.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:29
 */
@RestController
@RequestMapping("/api/inventory/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
