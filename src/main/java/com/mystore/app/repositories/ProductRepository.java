package com.mystore.app.repositories;

import com.mystore.app.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    List<Product> findByStockQuantityBetween(double minQuantity, double maxQuantiy);

    Page<Product> findAll(Pageable pageable);

    List<Product> findByCategory(String category);
}
