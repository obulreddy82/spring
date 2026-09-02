package com.rajtechnologies.springbootdatajpa.repository;

import com.rajtechnologies.springbootdatajpa.entity.Customer;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    @EntityGraph(attributePaths = "orders")
//    List<Customer> findAll();

//    @Query("""
//    SELECT DISTINCT c
//    FROM Customer c
//    LEFT JOIN FETCH c.orders
//""")
//    List<Customer> findAllWithOrders();
}
