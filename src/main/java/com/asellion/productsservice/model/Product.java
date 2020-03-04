package com.asellion.productsservice.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	private BigDecimal currentPrice;
	
	private Calendar lastUpdated;
	
	public Product(String name, BigDecimal currentPrice) {
		this.name = name;
		this.currentPrice = currentPrice;
	}
	
	@PrePersist
	private void initializeCurrentPrice() {
		this.lastUpdated = Calendar.getInstance();
	}
}
