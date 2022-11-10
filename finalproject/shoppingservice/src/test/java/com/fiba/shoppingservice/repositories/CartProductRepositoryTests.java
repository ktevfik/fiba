package com.fiba.shoppingservice.repositories;

import com.fiba.shoppingservice.entity.CartProduct;
import com.fiba.shoppingservice.repository.CartProductRepository;
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
 * @created 10/11/2022 - 06:27
 */
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartProductRepositoryTests {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CartProductRepository cartProductRepository;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }

    @Test
    public void it_should_create_cart_product() {
        // given
        CartProduct cartProduct = new CartProduct();
        cartProduct.setSalesQuantity(1);
        cartProduct.setSalesPrice(BigDecimal.valueOf(100));
        cartProduct.setLineAmount(cartProduct.getSalesPrice().multiply(BigDecimal.valueOf(cartProduct.getSalesQuantity())));

        Object id = this.testEntityManager.persistAndGetId(cartProduct);
        this.testEntityManager.flush();

        // when
        List<CartProduct> cartProducts = cartProductRepository.findAll();

        // then
        then(cartProducts).isNotNull();
        then(cartProducts.size()).isEqualTo(1);
        then(cartProducts.get(0).getCartProductId()).isEqualTo(id);
        then(cartProducts.get(0).getSalesQuantity()).isEqualTo(cartProduct.getSalesQuantity());
        then(cartProducts.get(0).getSalesPrice()).isEqualTo(cartProduct.getSalesPrice());
        then(cartProducts.get(0).getLineAmount()).isEqualTo(cartProduct.getLineAmount());
    }

    @Test
    public void it_should_find_cart_product_by_id() {
        // given
        CartProduct cartProduct = new CartProduct();
        cartProduct.setSalesQuantity(1);
        cartProduct.setSalesPrice(BigDecimal.valueOf(100));
        cartProduct.setLineAmount(cartProduct.getSalesPrice().multiply(BigDecimal.valueOf(cartProduct.getSalesQuantity())));

        Object id = this.testEntityManager.persistAndGetId(cartProduct);
        this.testEntityManager.flush();

        // when
        CartProduct cartProductFound = this.cartProductRepository.findById((Long) id).orElse(null);

        // then
        then(cartProductFound).isNotNull();
        then(cartProductFound.getCartProductId()).isEqualTo(id);
        then(cartProductFound.getProductId()).isEqualTo(cartProduct.getProductId());
        then(cartProductFound.getSalesPrice()).isEqualTo(cartProduct.getSalesPrice());
        then(cartProductFound.getSalesQuantity()).isEqualTo(cartProduct.getSalesQuantity());
        then(cartProductFound.getLineAmount()).isEqualTo(cartProduct.getLineAmount());
    }

    @Test
    public void it_should_delete_cart_product_by_id() {
        // given
        CartProduct cartProduct = new CartProduct();
        cartProduct.setSalesQuantity(1);
        cartProduct.setSalesPrice(BigDecimal.valueOf(100));
        cartProduct.setLineAmount(cartProduct.getSalesPrice().multiply(BigDecimal.valueOf(cartProduct.getSalesQuantity())));

        Object id = this.testEntityManager.persistAndGetId(cartProduct);
        this.testEntityManager.flush();

        // when
        this.cartProductRepository.deleteById((Long) id);
        CartProduct cartProductFound = this.cartProductRepository.findById((Long) id).orElse(null);

        // then
        then(cartProductFound).isNull();
    }
}
