package com.example.finalprojectspring.model.repo;

import com.example.finalprojectspring.model.entity.Product;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("FROM Product WHERE concat(category.id, '') LIKE ?1 AND concat(color.id, '') LIKE ?2 AND concat(size.id, '') LIKE ?3 AND concat(sex, '') LIKE ?4")
    List<Product> findWithFilter(String categoryPattern, String colorPattern, String sizePattern, String sexPattern, PageRequest pageRequest);
}