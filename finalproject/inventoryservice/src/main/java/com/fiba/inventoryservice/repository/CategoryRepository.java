package com.fiba.inventoryservice.repository;

import com.fiba.inventoryservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Tevfik Kadan
 * @created 08/11/2022 - 03:27
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM Category c WHERE c.categoryId = :categoryId AND c.categoryName = :categoryName")
    Optional<Category> isCategoryExist(@Param("categoryId") Long categoryId, @Param("categoryName") String categoryName);

    @Modifying
    @Transactional
    @Query("update Category c set c.categoryName = :categoryName where c.categoryId = :categoryId")
    void updateCategoryName(@Param("categoryId") Long categoryId, @Param("categoryName") String categoryName);
}
