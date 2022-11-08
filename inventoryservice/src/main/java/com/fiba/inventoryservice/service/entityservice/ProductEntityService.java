package com.fiba.inventoryservice.service.entityservice;

import com.fiba.inventoryservice.dto.category.CategoryDto;
import com.fiba.inventoryservice.dto.product.ProductDto;
import com.fiba.inventoryservice.entity.Category;
import com.fiba.inventoryservice.entity.Product;
import com.fiba.inventoryservice.enums.ErrorMessage;
import com.fiba.inventoryservice.exception.exceptions.CategoryNotFoundException;
import com.fiba.inventoryservice.exception.exceptions.ProductAlreadyExistException;
import com.fiba.inventoryservice.exception.exceptions.ProductNotFoundException;
import com.fiba.inventoryservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 23:18
 */
@Service
public class ProductEntityService {

    private final ProductRepository productRepository;

    private final CategoryEntityService categoryEntityService;

    public ProductEntityService(ProductRepository productRepository, CategoryEntityService categoryEntityService) {
        this.productRepository = productRepository;
        this.categoryEntityService = categoryEntityService;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> findProductsByCategoryId(Long id) {
        try {
            return productRepository.findProductsByCategoryId(id);
        } catch (Exception e) {
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }
    }

    public Product getByIdWithControl(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        Product product;
        try {
            product = productOptional.get();
        } catch (Exception e) {
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }
        return product;
    }

    public Product save(ProductDto productDto) {
        CategoryDto categoryDto = productDto.getCategoryDto();

        boolean categoryExist = categoryEntityService.isCategoryExist(categoryDto.getCategoryId(), categoryDto.getCategoryName());

        if (!categoryExist) {
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }

        Optional<Product> productOptional = productRepository.findProductByProductNameAndCategoryId(productDto.getProductName(), categoryDto.getCategoryId());

        if (productOptional.isPresent()) {
            throw new ProductAlreadyExistException(ErrorMessage.PRODUCT_ALREADY_EXIST);
        }

        Category category = categoryEntityService.getByIdWithControl(categoryDto.getCategoryId());

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setSalesPrice(productDto.getSalesPrice());
        product.setCategory(category);

        return productRepository.save(product);
    }


    public void delete(Product product) {
        productRepository.delete(product);
    }

}
