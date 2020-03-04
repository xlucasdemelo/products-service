package com.asellion.productsservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asellion.productsservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	public Optional<Product> findById(Long id);
}
