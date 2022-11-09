package com.fiba.shoppingservice.repository;

import com.fiba.shoppingservice.entity.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:50
 */
@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long> {

}
