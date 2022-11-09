package com.fiba.shoppingservice.repository;

import com.fiba.shoppingservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:49
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}
