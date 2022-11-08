package com.fiba.inventoryservice.repository;

import com.fiba.inventoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:27
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
