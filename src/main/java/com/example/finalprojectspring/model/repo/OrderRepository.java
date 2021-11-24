package com.example.finalprojectspring.model.repo;

import com.example.finalprojectspring.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}