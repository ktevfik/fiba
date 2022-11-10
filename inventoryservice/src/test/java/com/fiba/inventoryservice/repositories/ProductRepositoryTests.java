package com.fiba.inventoryservice.repositories;

import com.fiba.inventoryservice.entity.Category;
import com.fiba.inventoryservice.entity.Product;
import com.fiba.inventoryservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 14:29
 */
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTests {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private ProductRepository productRepository;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }

    @Test
    public void it_should_add_product() {
        // given
        Product product = new Product();
        product.setProductName("Test Product");
        product.setSalesPrice(BigDecimal.valueOf(100));

        Object productId1 = this.testEntityManager.persistAndGetId(product);
        this.testEntityManager.flush();

        // when
        List<Product> products = this.productRepository.findAll();

        // then
        then(products).isNotEmpty();
        then(products).hasSize(1);
        then(products.get(0).getProductId()).isEqualTo(productId1);
        then(products.get(0).getProductName()).isEqualTo("Test Product");
        then(products.get(0).getSalesPrice()).isEqualTo(BigDecimal.valueOf(100));

    }

    @Test
    public void it_should_find_products_by_category_id() {
        // given
        Category category = new Category();
        category.setCategoryName("Test Category");

        Category category2 = new Category();
        category2.setCategoryName("Test Category 2");

        Object categoryId1 = this.testEntityManager.persistAndGetId(category);
        Object categoryId2 = this.testEntityManager.persistAndGetId(category2);

        Product product1 = new Product();
        product1.setProductName("Test Product 1");
        product1.setSalesPrice(BigDecimal.valueOf(100));
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setProductName("Test Product 2");
        product2.setSalesPrice(BigDecimal.valueOf(200));
        product2.setCategory(category);

        Product product3 = new Product();
        product3.setProductName("Test Product 3");
        product3.setSalesPrice(BigDecimal.valueOf(300));
        product3.setCategory(category2);

        Object id1 = this.testEntityManager.persistAndGetId(product1);
        Object id2 = this.testEntityManager.persistAndGetId(product2);
        Object id3 = this.testEntityManager.persistAndGetId(product3);
        this.testEntityManager.flush();


        // when
        List<Product> products = this.productRepository.findProductsByCategoryId(category.getCategoryId());
        List<Product> products2 = this.productRepository.findProductsByCategoryId(category2.getCategoryId());

        // then
        then(categoryId1).isNotNull();
        then(categoryId2).isNotNull();
        then(id1).isNotNull();
        then(id2).isNotNull();
        then(id3).isNotNull();

        then(products).isNotEmpty();
        then(products).hasSize(2);
        then(products.get(0).getProductId()).isEqualTo(id1);
        then(products.get(0).getProductName()).isEqualTo("Test Product 1");
        then(products.get(0).getSalesPrice()).isEqualTo(BigDecimal.valueOf(100));
        then(products.get(0).getCategory().getCategoryId()).isEqualTo(categoryId1);

        then(products.get(1).getProductId()).isEqualTo(id2);
        then(products.get(1).getProductName()).isEqualTo("Test Product 2");
        then(products.get(1).getSalesPrice()).isEqualTo(BigDecimal.valueOf(200));
        then(products.get(1).getCategory().getCategoryId()).isEqualTo(categoryId1);

        then(products2).isNotEmpty();
        then(products2).hasSize(1);
        then(products2.get(0).getProductId()).isEqualTo(id3);
        then(products2.get(0).getProductName()).isEqualTo("Test Product 3");
        then(products2.get(0).getSalesPrice()).isEqualTo(BigDecimal.valueOf(300));
        then(products2.get(0).getCategory().getCategoryId()).isEqualTo(categoryId2);

    }

    @Test
    public void it_should_find_product_by_id() {
        // given
        Product product = new Product();
        product.setProductName("Test Product");
        product.setSalesPrice(BigDecimal.valueOf(100));

        Object productId1 = this.testEntityManager.persistAndGetId(product);
        this.testEntityManager.flush();

        // when
        Product product1 = this.productRepository.findById((Long) productId1).orElse(null);

        // then
        then(product1).isNotNull();
        then(product1.getProductId()).isEqualTo(productId1);
        then(product1.getProductName()).isEqualTo("Test Product");
        then(product1.getSalesPrice()).isEqualTo(BigDecimal.valueOf(100));
    }

    @Test
    public void it_should_find_products() {

        //given
        Product product1 = new Product();
        product1.setProductName("Test Product 1");
        product1.setSalesPrice(BigDecimal.valueOf(150));
        Product product2 = new Product();
        product2.setProductName("Test Product 2");
        product2.setSalesPrice(BigDecimal.valueOf(1000));

        Object id1 = this.testEntityManager.persistAndGetId(product1);
        Object id2 = this.testEntityManager.persistAndGetId(product2);
        this.testEntityManager.flush();

        //when
        List<Product> products = this.productRepository.findAll();

        //then
        then(products).isNotEmpty();
        Product p1 = products.get(0);
        Product p2 = products.get(1);
        then(p1.getProductId()).isEqualTo(id1);
        then(p2.getProductId()).isEqualTo(id2);
    }

    @Test
    public void it_should_delete_product_by_id() {
        // given
        Product product = new Product();
        product.setProductName("Test Product");
        product.setSalesPrice(BigDecimal.valueOf(100));

        Object productId1 = this.testEntityManager.persistAndGetId(product);
        this.testEntityManager.flush();

        // when
        this.productRepository.deleteById((Long) productId1);

        // then
        Product product1 = this.productRepository.findById((Long) productId1).orElse(null);
        then(product1).isNull();
    }
}
