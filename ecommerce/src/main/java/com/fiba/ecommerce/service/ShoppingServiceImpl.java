package com.fiba.ecommerce.service;

import com.fiba.ecommerce.enums.ErrorMessage;
import com.fiba.ecommerce.exception.exceptions.CartNotFoundException;
import com.fiba.ecommerce.exception.exceptions.CustomerAlreadyExistException;
import com.fiba.ecommerce.models.shopping.cart.CartDto;
import com.fiba.ecommerce.models.shopping.cartproduct.CartProductDto;
import com.fiba.ecommerce.models.shopping.cartproduct.SaveProductToCartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @author Tevfik Kadan
 * @created 10/11/2022 - 14:34
 */
@Service
public class ShoppingServiceImpl implements ShoppingService {
    @Override
    public CartDto createCart() {
        try {
            String url = "http://localhost:8082/api/shopping/carts/create";

            RestTemplate restTemplate = new RestTemplate();

            CartDto cartDto = new CartDto();
            cartDto.setCustomerName("Mustafa Tevfik Kadan");


            ResponseEntity<CartDto> responseEntity = restTemplate.postForEntity(url, cartDto, CartDto.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new CustomerAlreadyExistException(ErrorMessage.CUSTOMER_ALREADY_EXIST);
        }
    }

    @Override
    public List<CartDto> getCarts() {

        String url = "http://localhost:8082/api/shopping/carts";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<CartDto[]> responseEntity = restTemplate.getForEntity(url, CartDto[].class);

        List<CartDto> cartDtoList = List.of(Objects.requireNonNull(responseEntity.getBody()));

        return cartDtoList;
    }

    @Override
    public CartDto getCartById(Long id) {
        try {
            String url = "http://localhost:8082/api/shopping/carts/" + id;

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<CartDto> responseEntity = restTemplate.getForEntity(url, CartDto.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }
    }

    @Override
    public void deleteCart(Long id) {
        try {
            String url = "http://localhost:8082/api/shopping/carts/" + id;

            RestTemplate restTemplate = new RestTemplate();

            restTemplate.delete(url);
        } catch (Exception e) {
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }
    }

    @Override
    public List<CartProductDto> getProductsByCartId(Long cartId) {
        try {
            String url = "http://localhost:8082/api/shopping/carts/" + cartId + "/products";

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<CartProductDto[]> responseEntity = restTemplate.getForEntity(url, CartProductDto[].class);

            List<CartProductDto> cartProductDtoList = List.of(Objects.requireNonNull(responseEntity.getBody()));

            return cartProductDtoList;
        } catch (Exception e) {
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }
    }

    @Override
    public CartDto addProductToCart() {
        try {
            String url = "http://localhost:8082/api/shopping/carts/add";

            RestTemplate restTemplate = new RestTemplate();
            CartDto cartDto = createCartForAddProductToCart();
            SaveProductToCartDto cartProductDto = new SaveProductToCartDto();
            cartProductDto.setCartId(cartDto.getCartId());
            cartProductDto.setProductId(703L);
            cartProductDto.setSalesQuantity(5);

            ResponseEntity<CartDto> responseEntity = restTemplate.postForEntity(url, cartProductDto, CartDto.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }
    }

    @Override
    public CartDto checkoutCart(Long cartId) {
        try {
            String url = "http://localhost:8082/api/shopping/carts/checkout/" + cartId;

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<CartDto> responseEntity = restTemplate.getForEntity(url, CartDto.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }
    }

    @Override
    public void deleteProductFromCart(Long cartId, Long cartProductId) {
        try {
            String url = "http://localhost:8082/api/shopping/carts/" + cartId + "/remove/" + cartProductId;

            RestTemplate restTemplate = new RestTemplate();

            restTemplate.delete(url);
        } catch (Exception e) {
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }
    }

    public CartDto createCartForAddProductToCart() {
        try {
            String url = "http://localhost:8082/api/shopping/carts/create";

            RestTemplate restTemplate = new RestTemplate();

            CartDto cartDto = new CartDto();
            cartDto.setCustomerName("Mustafa Test Kadan");


            ResponseEntity<CartDto> responseEntity = restTemplate.postForEntity(url, cartDto, CartDto.class);

            return responseEntity.getBody();
        } catch (Exception e) {
            throw new CustomerAlreadyExistException(ErrorMessage.CUSTOMER_ALREADY_EXIST);
        }
    }

}

