package com.fiba.shoppingservice.repository;

import com.fiba.shoppingservice.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:50
 */
@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {


    @Query("SELECT cp FROM CartProduct cp WHERE cp.cart.cartId = :cartId AND cp.productId = :productId")
    Optional<CartProduct> findCartProductByCartIdAndProductId(@Param("cartId") long cartId, @Param("productId") long productId);

}
