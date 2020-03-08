package com.asellion.productsservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asellion.productsservice.model.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
	public Optional<ApplicationUser> findByUsernameAndPassword(String username, String password);
	public Optional<ApplicationUser> findByUsername(String username);
}
