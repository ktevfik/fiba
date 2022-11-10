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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 23:18
 */
@Service
public class ProductEntityService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

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
        log.info("Get By CategoryId process started for CategoryId: {}", id);

        if (!categoryEntityService.existById(id)) {
            log.error(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE.getMessage());
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }

        log.info("Get By CategoryId process finished for CategoryId: {}", id);
        return productRepository.findProductsByCategoryId(id);
    }

    public Product getByIdWithControl(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        Product product;
        try {
            product = productOptional.get();
            log.info("Product found for ProductId: {}", id);
        } catch (Exception e) {
            log.error(ErrorMessage.PRODUCT_NOT_FOUND_MESSAGE.getMessage());
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }
        return product;
    }

    public Product save(ProductDto productDto) {
        CategoryDto categoryDto = productDto.getCategoryDto();

        boolean categoryExist = categoryEntityService.isCategoryExist(categoryDto.getCategoryId(), categoryDto.getCategoryName());

        if (!categoryExist) {
            log.error(ErrorMessage.CATEGORY_NOT_FOUND_MESSAGE.getMessage());
            throw new CategoryNotFoundException(ErrorMessage.CATEGORY_NOT_FOUND);
        }

        Optional<Product> productOptional = productRepository.findProductByProductNameAndCategoryId(productDto.getProductName(), categoryDto.getCategoryId());

        if (productOptional.isPresent()) {
            log.error(ErrorMessage.PRODUCT_ALREADY_EXIST_MESSAGE.getMessage());
            throw new ProductAlreadyExistException(ErrorMessage.PRODUCT_ALREADY_EXIST);
        }

        Category category = categoryEntityService.getByIdWithControl(categoryDto.getCategoryId());

        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setSalesPrice(productDto.getSalesPrice());
        product.setCategory(category);

        return productRepository.save(product);
    }

    public boolean existById(Long id) {
        return productRepository.existsById(id);
    }


    public void delete(Long id) {
        productRepository.deleteProductById(id);
    }

}
