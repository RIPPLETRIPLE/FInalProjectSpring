package com.example.finalprojectspring.model.repo;

import com.example.finalprojectspring.model.entity.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Product.Size, Long> {
}