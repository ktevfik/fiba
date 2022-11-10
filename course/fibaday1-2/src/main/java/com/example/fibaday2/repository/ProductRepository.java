package com.example.fibaday2.repository;

import com.example.fibaday2.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tevfik Kadan
 * @created 06/11/2022 - 10:29
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
