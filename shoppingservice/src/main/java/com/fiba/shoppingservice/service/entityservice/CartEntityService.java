package com.fiba.shoppingservice.service.entityservice;

import com.fiba.shoppingservice.converter.Cart.CartMapper;
import com.fiba.shoppingservice.converter.CartProduct.CartProductMapper;
import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.SaveProductToCartDto;
import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;
import com.fiba.shoppingservice.dto.inventory.ProductDto;
import com.fiba.shoppingservice.entity.Cart;
import com.fiba.shoppingservice.entity.CartProduct;
import com.fiba.shoppingservice.enums.ErrorMessage;
import com.fiba.shoppingservice.exception.exceptions.CartNotFoundException;
import com.fiba.shoppingservice.exception.exceptions.CustomerAlreadyExistException;
import com.fiba.shoppingservice.exception.exceptions.ProductNotFoundException;
import com.fiba.shoppingservice.repository.CartRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:52
 */
@Service
public class CartEntityService {

    private final CartRepository cartRepository;

    private final CartProductEntityService cartProductEntityService;

    public CartEntityService(CartRepository cartRepository, CartProductEntityService cartProductEntityService) {
        this.cartRepository = cartRepository;
        this.cartProductEntityService = cartProductEntityService;
    }

    public CartDto saveCart(CartDto cartDto) {
        if (existByName(cartDto.getCustomerName())) {
            throw new CustomerAlreadyExistException(ErrorMessage.CUSTOMER_ALREADY_EXIST);
        }

        Cart cart = CartMapper.INSTANCE.convertToCart(cartDto);
        // initialize savedCartDto with starter values
        // totalAmount - cartProducts - isPaid
        cart.setPaid(false);
        cart.setTotalAmount(BigDecimal.ZERO);
        cart.setCartProducts(new ArrayList<>());

        Cart savedCart = cartRepository.save(cart);

        CartDto savedCartDto = CartMapper.INSTANCE.convertToCartDto(savedCart);

        return savedCartDto;

    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart getCartById(long id) {
        if (!existById(id)) {
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }

        Optional<Cart> cart = cartRepository.findById(id);

        return cart.get();
    }

    public CartDto addProductToCart(SaveProductToCartDto saveProductToCartDto) {
        Cart cart = getCartById(saveProductToCartDto.getCartId());

        if (cart.isPaid()) {
            cart.setPaid(false);
        }

        Long productId = saveProductToCartDto.getProductId();

        int quantity = saveProductToCartDto.getSalesQuantity();

        ProductDto productDto = getProductById(productId);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProductId(productDto.getProductId());
        cartProduct.setSalesPrice(productDto.getSalesPrice());
        cartProduct.setSalesQuantity(quantity);
        cartProduct.setLineAmount(productDto.getSalesPrice().multiply(BigDecimal.valueOf(quantity)));

        CartProduct savedCartProduct = cartProductEntityService.saveCartProduct(cartProduct);

        cart.getCartProducts().add(savedCartProduct);

        cart.setTotalAmount(cart.getTotalAmount().add(savedCartProduct.getLineAmount()));

        Cart savedCart = cartRepository.save(cart);

        CartDto savedCartDto = CartMapper.INSTANCE.convertToCartDto(savedCart);

        return savedCartDto;

    }

    public void removeProductFromCart(long cartId, long productId) {
        Cart cart = getCartById(cartId);

        ProductDto productDto = getProductById(productId);

        CartProduct cartProduct = cartProductEntityService.getCartProductByCartIdAndProductId(cartId, productId);

        cart.getCartProducts().remove(cartProduct);

        cart.setTotalAmount(cart.getTotalAmount().subtract(cartProduct.getLineAmount()));

        cartRepository.save(cart);

        cartProductEntityService.deleteCartProductById(cartProduct.getCartProductId());

    }

    public CartDto removeAllCartProductsAndCheckoutCart(long cartId) {
        Cart cart = getCartById(cartId);

        List<CartProduct> cartProducts = cart.getCartProducts();

        cart.setCartProducts(new ArrayList<>());

        cart.setTotalAmount(BigDecimal.ZERO);

        cart.setPaid(true);

        Cart savedCart = cartRepository.save(cart);

        for (CartProduct cartProduct : cartProducts) {
            cartProductEntityService.deleteCartProductById(cartProduct.getCartProductId());
        }

        CartDto savedCartDto = CartMapper.INSTANCE.convertToCartDto(savedCart);

        return savedCartDto;
    }

    public CartProductDto getCartProductById(long cartProductId) {
        CartProduct cartProduct = cartProductEntityService.findCartProductById(cartProductId);

        CartProductDto cartProductDto = CartProductMapper.INSTANCE.convertToCartProductDto(cartProduct);

        return cartProductDto;
    }

    public ProductDto getProductById(Long id) {
        try {
            String url = "http://localhost:8081/api/inventory/products/" + id;
            RestTemplate restTemplate = new RestTemplate();
            ProductDto productDto = restTemplate.getForObject(url, ProductDto.class);
            return productDto;
        } catch (Exception e) {
            throw new ProductNotFoundException(ErrorMessage.PRODUCT_NOT_FOUND);
        }
    }

    public boolean existById(long id) {
        return cartRepository.existsById(id);
    }

    public boolean existByName(String name) {
        Optional<Cart> cart = cartRepository.findByName(name);
        return cart.isPresent();
    }

    public void deleteCartById(long id) {
        if (!existById(id)) {
            throw new CartNotFoundException(ErrorMessage.CART_NOT_FOUND);
        }

        cartRepository.deleteById(id);
    }
}
