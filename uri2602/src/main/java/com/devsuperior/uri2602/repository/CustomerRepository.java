package com.devsuperior.uri2602.repository;

import com.devsuperior.uri2602.dto.CustomerMinDTO;
import com.devsuperior.uri2602.entities.Customer;
import com.devsuperior.uri2602.projections.CustomerMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(nativeQuery = true, value =
            "SELECT name FROM customers c WHERE UPPER(state) = UPPER(:state) ")
    List<CustomerMinProjection> searchByNameNative(String state);

    @Query("SELECT new com.devsuperior.uri2602.dto.CustomerMinDTO(customer.name)" +
            " FROM Customer customer WHERE UPPER(customer.state) = UPPER(:state)")
    List<CustomerMinDTO> searchByNameJpql(String state);

}
