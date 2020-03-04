package com.asellion.productsservice.model;

import java.math.BigDecimal;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductDTO {
	
	private String name;
	
	private BigDecimal currentPrice;
	
	public Product toEntity() {
		return new Product(this.name, this.currentPrice);
	}
}
