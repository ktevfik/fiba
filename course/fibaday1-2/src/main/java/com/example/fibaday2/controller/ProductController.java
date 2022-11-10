package com.example.fibaday2.controller;

import com.example.fibaday2.entity.Product;
import com.example.fibaday2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 10:32
 */
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @GetMapping("/product/add")
    @ResponseBody
    public String insertProduct() {
        Product product = new Product(1, "Cep Telefonu", 5000);
        productRepository.save(product);
        return "Product repository save method applied successfully: ID: " + product.getProductId() + " Product Name: " + product.getProductName() + " Product Sales Price: " + product.getSalesPrice();
    }

    @GetMapping("/product/get")
    @ResponseBody
    public String getProduct() {
        Optional<Product> optional = productRepository.findById(1L);
        if (optional.isPresent()) {
            Product product = optional.get();
            return "Product repository findById method applied successfully: ID: " + product.getProductId() + " Product Name: " + product.getProductName() + " Product Sales Price: " + product.getSalesPrice();
        }
        return "Product repository findById method applied successfully: No product found with ID: 1";
    }

    @GetMapping("/product/list")
    @ResponseBody
    public String listProducts() {
        Iterable<Product> products = productRepository.findAll();
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (Product product : products) {
            stringBuilder.append("Product repository findAll method applied successfully: ID: ").append(product.getProductId()).append(" Product Name: ").append(product.getProductName()).append(" Product Sales Price: ").append(product.getSalesPrice()).append("<br>");
            count++;
        }
        if (count == 0) {
            stringBuilder.append("Product repository findAll method applied successfully: No product found");
        }
        return stringBuilder.toString();
    }

    @GetMapping("/product/delete")
    @ResponseBody
    public String deleteProduct() {
        if (!productRepository.existsById(1L)) {
            return "Product repository deleteById method applied successfully: No product found with ID: 1";
        }
        productRepository.deleteById(1L);
        return "Product repository deleteById method applied successfully: Product with ID: 1 deleted";
    }
}
