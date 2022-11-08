package com.fiba.inventoryservice.repository;

import com.fiba.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:26
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
