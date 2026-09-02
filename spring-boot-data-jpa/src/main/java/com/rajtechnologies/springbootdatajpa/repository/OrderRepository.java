package com.rajtechnologies.springbootdatajpa.repository;

import com.rajtechnologies.springbootdatajpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
