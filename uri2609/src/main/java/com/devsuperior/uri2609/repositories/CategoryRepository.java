package com.devsuperior.uri2609.repositories;

import com.devsuperior.uri2609.dto.CategorySumDTO;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2609.entities.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(nativeQuery = true, value = "SELECT cat.name, sum(amount) FROM products prod\n" +
            "INNER JOIN categories cat ON prod.id_categories = cat.id\n" +
            "GROUP BY prod.id_categories, cat.name")
    List<CategorySumProjection> searchProductForCategory();

    @Query(value = "SELECT new com.devsuperior.uri2609.dto.CategorySumDTO(prod.category.name, sum(prod.amount))  FROM Product prod\n" +
            "GROUP BY prod.category.id, prod.category.name")
    List<CategorySumDTO> searchProductForCategoryJPQL();

}
