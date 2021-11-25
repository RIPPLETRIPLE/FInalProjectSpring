package com.example.finalprojectspring.model.repo;

import com.example.finalprojectspring.model.entity.Order;
import com.example.finalprojectspring.model.entity.Product;
import com.example.finalprojectspring.model.entity.User;
import com.example.finalprojectspring.model.entity.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findOrderByUserAndStatusAndProduct(User user, OrderStatus status, Product product);
    List<Order> findOrdersByUserAndStatus(User user, OrderStatus status);
}