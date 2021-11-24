package com.example.finalprojectspring.model.repo;

import com.example.finalprojectspring.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Product.Category, Long> {
}