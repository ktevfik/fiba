package com.fiba.ecommerce.controller;

import com.fiba.ecommerce.models.SuccessMessage;
import com.fiba.ecommerce.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 13:12
 */
@Controller
@RequestMapping("/ecommerce/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @RequestMapping(value = "/categories/get/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getCategories() {
        return new ResponseEntity<>(inventoryService.getCategories(), null, 200);
    }

    @RequestMapping(value = "/categories/create", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> createCategory() {
        return new ResponseEntity<>(inventoryService.createCategory(), null, 200);
    }

    @RequestMapping(value = "/categories/get/category/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(inventoryService.getCategoryById(id), null, 200);
    }

    @RequestMapping(value = "/categories/delete/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        inventoryService.deleteCategory(id);
        return new ResponseEntity<>(new SuccessMessage(), null, 200);
    }

    @RequestMapping(value = "/categories/update/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateCategory(@PathVariable("id") Long id) {
        inventoryService.updateCategory(id);
        return new ResponseEntity<>(new SuccessMessage(), null, 200);
    }

    @RequestMapping(value = "/products/get/all", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getProducts() {
        return new ResponseEntity<>(inventoryService.getProducts(), null, 200);
    }

    @RequestMapping(value = "/products/create", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> createProduct() {
        return new ResponseEntity<>(inventoryService.createProduct(), null, 200);
    }

    @RequestMapping(value = "/products/delete/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        inventoryService.deleteProduct(id);
        return new ResponseEntity<>(new SuccessMessage(), null, 200);
    }

    @RequestMapping(value = "/products/get/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(inventoryService.getProductById(id), null, 200);
    }

    @RequestMapping(value = "/products/category/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getProductsByCategoryId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(inventoryService.getProductsByCategoryId(id), null, 200);
    }
}
