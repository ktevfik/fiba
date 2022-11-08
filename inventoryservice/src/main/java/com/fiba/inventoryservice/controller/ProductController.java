package com.fiba.inventoryservice.controller;

import com.fiba.inventoryservice.dto.product.ProductDto;
import com.fiba.inventoryservice.dto.product.ProductSaveRequestDto;
import com.fiba.inventoryservice.service.ProductService;
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
 * @created 08/11/2022 - 03:29
 */
@RestController
@RequestMapping("/api/inventory/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<ProductDto> productDtoList = productService.getProducts();

        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        ProductDto productDto = productService.getProductById(id);

        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable Long id) {
        List<ProductDto> productDtoList = productService.getProductsByCategoryId(id);

        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody ProductSaveRequestDto productSaveRequestDto) {
        ProductDto productDto = productService.createProduct(productSaveRequestDto);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
