package com.fiba.shoppingservice.repository;

import com.fiba.shoppingservice.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 20:49
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("SELECT c FROM Cart c WHERE c.customerName = :customerName")
    Optional<Cart> findByName(@Param("customerName") String customerName);
}
