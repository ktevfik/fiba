package com.fiba.inventoryservice.repositories;

import com.fiba.inventoryservice.entity.Category;
import com.fiba.inventoryservice.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;

/**
 * @author Tevfik Kadan
 * @created 09/11/2022 - 15:05
 */
@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
public class CategoryRepositoryTests {

    @Container
    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CategoryRepository categoryRepository;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name", postgreSQLContainer::getDriverClassName);
    }

    @Test
    public void it_should_add_category() {
        // given
        Category category = new Category();
        category.setCategoryName("Test Category");

        Object categoryId1 = this.testEntityManager.persistAndGetId(category);
        this.testEntityManager.flush();

        // when
        List<Category> categories = this.categoryRepository.findAll();

        // then
        then(categories).isNotEmpty();
        then(categories).hasSize(1);
        then(categories.get(0).getCategoryId()).isEqualTo(categoryId1);
        then(categories.get(0).getCategoryName()).isEqualTo("Test Category");
    }

    @Test
    public void it_should_find_category_by_id() {
        // given
        Category category = new Category();
        category.setCategoryName("Test Category");

        Object categoryId1 = this.testEntityManager.persistAndGetId(category);
        this.testEntityManager.flush();

        // when
        Category category1 = this.categoryRepository.findById((Long) categoryId1).orElse(null);

        // then
        then(category1).isNotNull();
        then(category1.getCategoryId()).isEqualTo(categoryId1);
        then(category1.getCategoryName()).isEqualTo("Test Category");
    }

    @Test
    public void it_should_find_all_categories() {
        // given
        Category category1 = new Category();
        category1.setCategoryName("Test Category 1");

        Category category2 = new Category();
        category2.setCategoryName("Test Category 2");

        Object categoryId1 = this.testEntityManager.persistAndGetId(category1);
        Object categoryId2 = this.testEntityManager.persistAndGetId(category2);
        this.testEntityManager.flush();

        // when
        List<Category> categories = this.categoryRepository.findAll();

        // then
        then(categories).isNotEmpty();
        then(categories).hasSize(2);
        then(categories.get(0).getCategoryId()).isEqualTo(categoryId1);
        then(categories.get(0).getCategoryName()).isEqualTo("Test Category 1");
        then(categories.get(1).getCategoryId()).isEqualTo(categoryId2);
        then(categories.get(1).getCategoryName()).isEqualTo("Test Category 2");
    }

    @Test
    public void it_should_update_category() {
        // given
        Category category = new Category();
        category.setCategoryName("Test Category");

        Object categoryId1 = this.testEntityManager.persistAndGetId(category);
        this.testEntityManager.flush();

        // when
        Category category1 = this.categoryRepository.findById((Long) categoryId1).orElse(null);
        assert category1 != null;
        category1.setCategoryName("Test Category 1");
        this.categoryRepository.save(category1);

        // then
        then(category1).isNotNull();
        then(category1.getCategoryId()).isEqualTo(categoryId1);
        then(category1.getCategoryName()).isEqualTo("Test Category 1");
    }

    @Test
    public void it_should_delete_category() {
        // given
        Category category = new Category();
        category.setCategoryName("Test Category");

        Object categoryId1 = this.testEntityManager.persistAndGetId(category);
        this.testEntityManager.flush();

        // when
        Category category1 = this.categoryRepository.findById((Long) categoryId1).orElse(null);
        assert category1 != null;
        this.categoryRepository.delete(category1);

        // then
        then(this.categoryRepository.findById((Long) categoryId1).orElse(null)).isNull();
    }
}
