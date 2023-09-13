package com.devsuperior.uri2621.repositories;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.projections.ProductMinProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2621.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // SQL NATIVO
    @Query(nativeQuery = true, value = "SELECT prod.name FROM products prod\n" +
            "INNER JOIN providers prov ON prod.id_providers = prov.id\n" +
            "WHERE prod.amount \n" +
            "BETWEEN 10 AND 20 AND prov.name LIKE upper('P%')")
    List<ProductMinProjection> listNames();

    @Query(value = "SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(prod.name) FROM Product prod\n" +
            "WHERE prod.amount \n" +
            "BETWEEN 10 AND 20 AND prod.provider.name LIKE upper('P%')")
    List<ProductMinDTO> listaNamesJPQL();

}
