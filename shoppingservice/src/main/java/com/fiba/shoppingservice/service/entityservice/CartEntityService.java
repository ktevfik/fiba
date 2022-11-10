package com.fiba.shoppingservice.service.entityservice;

import com.fiba.shoppingservice.converter.Cart.CartMapper;
import com.fiba.shoppingservice.converter.CartProduct.CartProductMapper;
import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;
import com.fiba.shoppingservice.dto.inventory.ProductDto;
import com.fiba.shoppingservice.entity.Cart;
import com.fiba.shoppingservice.entity.CartProduct;
import com.fiba.shoppingservice.enums.ErrorMessage;
import com.fiba.shoppingservice.exception.exceptions.CartNotFoundException;
import com.fiba.shoppingservice.exception.exceptions.CustomerAlreadyExistException;
import com.fiba.shoppingservice.exception.exceptions.ProductNotFoundException;
import com.fiba.shoppingservice.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:52
 */
@Service
public class CartEntityService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CartRepository cartRepository;

    private final CartProductEntityService cartProductEntityService;

    public CartEntityService(CartRepository cartRepository, CartProductEntityService cartProductEntityService) {
        this.cartRepository = cartRepository;
        this.cartProductEntityService = cartProductEntityService;
    }

    public CartDto saveCart(Cart cart) {
        log.info("Cart saving process started");
        if (existByName(cart.getCustomerName())) {
            log.error(ErrorMessage.CUSTOMER_ALREADY_EXIST.getMessage());
            throw new CustomerAlreadyExistException(ErrorMessage.CUSTOMER_ALREADY_EXIST);
        }

        Cart savedCart = cartRepository.save(cart);
        log.info("Cart saved successfully: {}", savedCart);
        CartDto savedCartDto = CartMapper.INSTANCE.convertToCartDto(savedCart);

        log.info("Cart saving process finished");
        return savedCartDto;

    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(long id) {
        log.info("Get cart by id process started");
        if (!existById(id)) {
            log.error(ErrorMessage.CART_NOT_FOUND.getMessage());
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }

        Optional<Cart> cart = cartRepository.findById(id);
        log.info("Cart found successfully: {}", cart.get());

        log.info("Get cart by id process finished");
        return cart.get();
    }

    public CartDto addProductToCart(Cart cart, CartProduct cartProduct) {
        log.info("Add product to cart process started");
        CartProduct savedCartProduct = cartProductEntityService.saveCartProduct(cartProduct);

        cart.getCartProducts().add(savedCartProduct);

        cart.setTotalAmount(cart.getTotalAmount().add(savedCartProduct.getLineAmount()));

        Cart savedCart = cartRepository.save(cart);
        log.info("Cart saved successfully: {}", savedCart);

        CartDto savedCartDto = CartMapper.INSTANCE.convertToCartDto(savedCart);

        log.info("Add product to cart process finished");
        return savedCartDto;

    }

    public void removeProductFromCart(Cart cart, CartProduct cartProduct) {
        log.info("Remove product from cart process started");
        cartRepository.save(cart);
        log.info("CartProduct removed from cart successfully: {}", cartProduct);

        cartProductEntityService.deleteCartProductById(cartProduct.getCartProductId());
        log.info("Remove product from cart process finished");
    }

    public CartDto removeAllCartProductsAndCheckoutCart(Cart cart, List<CartProduct> cartProducts) {
        log.info("Checkout cart process started");

        Cart savedCart = cartRepository.save(cart);
        log.info("Cart saved successfully: {}", savedCart);

        for (CartProduct cartProduct : cartProducts) {
            cartProductEntityService.deleteCartProductById(cartProduct.getCartProductId());
        }

        CartDto savedCartDto = CartMapper.INSTANCE.convertToCartDto(savedCart);

        log.info("Checkout cart process finished");
        return savedCartDto;
    }

    public CartProductDto getCartProductById(long cartProductId) {
        log.info("Get cart product by id process started");
        CartProduct cartProduct = cartProductEntityService.findCartProductById(cartProductId);

        CartProductDto cartProductDto = CartProductMapper.INSTANCE.convertToCartProductDto(cartProduct);
        log.info("Cart product found successfully: {}", cartProductDto);

        log.info("Get cart product by id process finished");
        return cartProductDto;
    }

    public ProductDto getProductById(Long id) {
        log.info("Get product by id process started");
        try {
            String url = "http://localhost:8081/api/inventory/products/" + id;
            RestTemplate restTemplate = new RestTemplate();
            ProductDto productDto = restTemplate.getForObject(url, ProductDto.class);
            log.info("Product found successfully: {}", productDto);
            return productDto;
        } catch (Exception e) {
            log.error(ErrorMessage.PRODUCT_NOT_FOUND.getMessage());
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }
    }

    public void deleteCartById(long id) {
        log.info("Delete cart by id process started");
        if (!existById(id)) {
            log.error(ErrorMessage.CART_NOT_FOUND.getMessage());
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }

        cartRepository.deleteById(id);
        log.info("Cart deleted successfully: {}", id);

        log.info("Delete cart by id process finished");
    }

    public boolean existById(long id) {
        return cartRepository.existsById(id);
    }

    public boolean existByName(String name) {
        Optional<Cart> cart = cartRepository.findByName(name);
        return cart.isPresent();
    }

}
