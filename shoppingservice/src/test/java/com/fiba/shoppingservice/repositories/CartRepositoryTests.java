package com.fiba.shoppingservice.repositories;

import com.fiba.shoppingservice.entity.Cart;
import com.fiba.shoppingservice.entity.CartProduct;
import com.fiba.shoppingservice.repository.CartRepository;
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
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 06:16
 */
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartRepositoryTests {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CartRepository cartRepository;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }

    @Test
    public void it_should_create_cart() {
        // given
        Cart cart = new Cart();
        cart.setCustomerName("Tevfik Kadan");
        cart.setTotalAmount(BigDecimal.ZERO);

        Object savedCart = testEntityManager.persistAndGetId(cart);
        testEntityManager.flush();

        // when
        Cart foundCart = cartRepository.findById(cart.getCartId()).orElse(null);

        // then
        then(foundCart).isNotNull();
        then(foundCart.getTotalAmount()).isEqualTo(BigDecimal.ZERO);
        then(foundCart.getCartId()).isEqualTo(savedCart);
        then(foundCart.getCustomerName()).isEqualTo(cart.getCustomerName());
    }

    @Test
    public void it_should_find_cart_by_customer_name() {
        // given
        Cart cart = new Cart();
        cart.setCustomerName("Tevfik Kadan");
        cart.setTotalAmount(BigDecimal.ZERO);

        testEntityManager.persist(cart);
        testEntityManager.flush();

        // when
        Cart foundCart = cartRepository.findByName(cart.getCustomerName()).orElse(null);

        // then
        then(foundCart).isNotNull();
        then(foundCart.getTotalAmount()).isEqualTo(BigDecimal.ZERO);
        then(foundCart.getCartId()).isEqualTo(cart.getCartId());
        then(foundCart.getCustomerName()).isEqualTo(cart.getCustomerName());
    }

    @Test
    public void it_should_add_cart_product_to_cart() {
        // given
        Cart cart = new Cart();
        cart.setCustomerName("Tevfik Kadan");
        cart.setTotalAmount(BigDecimal.ZERO);
        List<CartProduct> cartProducts = new ArrayList<>();


        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setSalesPrice(BigDecimal.TEN);
        cartProduct.setSalesQuantity(2);
        cartProduct.setLineAmount(BigDecimal.valueOf(20));
        cartProduct.setProductId(1L);

        cartProducts.add(cartProduct);
        cart.setCartProducts(cartProducts);

        Object cartId = testEntityManager.persistAndGetId(cart);
        testEntityManager.flush();

        // when
        Cart foundCart = cartRepository.findByName(cart.getCustomerName()).orElse(null);

        // then
        then(foundCart).isNotNull();
        then(foundCart.getTotalAmount()).isEqualTo(BigDecimal.ZERO);
        then(foundCart.getCartId()).isEqualTo(cart.getCartId());
        then(foundCart.getCustomerName()).isEqualTo(cart.getCustomerName());
        then(foundCart.getCartProducts().size()).isEqualTo(1);
        then(foundCart.getCartProducts().get(0).getCart().getCartId()).isEqualTo(cartId);
        then(foundCart.getCartProducts().get(0).getProductId()).isEqualTo(1L);
        then(foundCart.getCartProducts().get(0).getSalesPrice()).isEqualTo(BigDecimal.TEN);
        then(foundCart.getCartProducts().get(0).getSalesQuantity()).isEqualTo(2);
        then(foundCart.getCartProducts().get(0).getLineAmount()).isEqualTo(BigDecimal.valueOf(20));
    }

    @Test
    public void it_should_delete_cart() {
        // given
        Cart cart = new Cart();
        cart.setCustomerName("Tevfik Kadan");
        cart.setTotalAmount(BigDecimal.ZERO);

        Object savedCart = testEntityManager.persistAndGetId(cart);
        testEntityManager.flush();

        // when
        cartRepository.deleteById(cart.getCartId());
        Cart foundCart = cartRepository.findById(cart.getCartId()).orElse(null);

        // then
        then(foundCart).isNull();
    }
}
