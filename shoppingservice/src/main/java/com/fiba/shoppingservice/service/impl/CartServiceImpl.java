package com.fiba.shoppingservice.service.impl;

import com.fiba.shoppingservice.converter.Cart.CartMapper;
import com.fiba.shoppingservice.dto.cart.CartDto;
import com.fiba.shoppingservice.dto.cart.CartSaveRequestDto;
import com.fiba.shoppingservice.dto.cart.SaveProductToCartDto;
import com.fiba.shoppingservice.dto.cartproduct.CartProductDto;
import com.fiba.shoppingservice.dto.inventory.ProductDto;
import com.fiba.shoppingservice.entity.Cart;
import com.fiba.shoppingservice.entity.CartProduct;
import com.fiba.shoppingservice.service.CartService;
import com.fiba.shoppingservice.service.entityservice.CartEntityService;
import com.fiba.shoppingservice.service.entityservice.CartProductEntityService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:55
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartEntityService cartEntityService;

    private final CartProductEntityService cartProductEntityService;

    public CartServiceImpl(CartEntityService cartEntityService, CartProductEntityService cartProductEntityService) {
        this.cartEntityService = cartEntityService;
        this.cartProductEntityService = cartProductEntityService;
    }

    @Override
    public CartDto createCart(CartSaveRequestDto cartSaveRequestDto) {
        CartDto cartDto = CartMapper.INSTANCE.convertToCartDto(cartSaveRequestDto);

        Cart cart = CartMapper.INSTANCE.convertToCart(cartDto);
        // initialize savedCartDto with starter values
        // totalAmount - cartProducts - isPaid
        cart.setPaid(false);
        cart.setTotalAmount(BigDecimal.ZERO);
        cart.setCartProducts(new ArrayList<>());

        CartDto savedCart = cartEntityService.saveCart(cart);

        return savedCart;
    }

    @Override
    public List<CartDto> getAllCarts() {
        List<Cart> carts = cartEntityService.getAllCarts();

        List<CartDto> cartDtoList = CartMapper.INSTANCE.convertToCartDtoList(carts);

        return cartDtoList;
    }

    @Override
    public CartDto getCartById(long id) {
        Cart cart = cartEntityService.getCartById(id);

        CartDto cartDto = CartMapper.INSTANCE.convertToCartDto(cart);

        return cartDto;
    }

    @Override
    public void deleteCartById(long id) {
        cartEntityService.deleteCartById(id);
    }

    @Override
    public CartDto addProductToCart(SaveProductToCartDto saveProductToCartDto) {
        Cart cart = cartEntityService.getCartById(saveProductToCartDto.getCartId());

        if (cart.isPaid()) {
            cart.setPaid(false);
        }

        Long productId = saveProductToCartDto.getProductId();

        int quantity = saveProductToCartDto.getSalesQuantity();

        ProductDto productDto = cartEntityService.getProductById(productId);

        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProductId(productDto.getProductId());
        cartProduct.setSalesPrice(productDto.getSalesPrice());
        cartProduct.setSalesQuantity(quantity);
        cartProduct.setLineAmount(productDto.getSalesPrice().multiply(BigDecimal.valueOf(quantity)));

        CartDto cartDto = cartEntityService.addProductToCart(cart, cartProduct);

        return cartDto;
    }

    @Override
    public void removeProductFromCart(long cartId, long cartProductId) {
        Cart cart = cartEntityService.getCartById(cartId);

        CartProduct cartProduct = cartProductEntityService.getCartProductByCartIdAndCartProductId(cartId, cartProductId);

        cart.getCartProducts().remove(cartProduct);

        cart.setTotalAmount(cart.getTotalAmount().subtract(cartProduct.getLineAmount()));

        cartEntityService.removeProductFromCart(cart, cartProduct);
    }

    @Override
    public CartDto checkoutCart(long cartId) {
        Cart cart = cartEntityService.getCartById(cartId);

        List<CartProduct> cartProducts = cart.getCartProducts();

        cart.setCartProducts(new ArrayList<>());

        cart.setTotalAmount(BigDecimal.ZERO);

        cart.setPaid(true);

        return cartEntityService.removeAllCartProductsAndCheckoutCart(cart, cartProducts);
    }

    @Override
    public CartProductDto getCartProductById(long cartProductId) {
        return cartEntityService.getCartProductById(cartProductId);
    }
}
