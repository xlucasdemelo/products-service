package com.asellion.productsservice.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	private Long id;
	
	private String name;
	
	private BigDecimal currentPrice;
	
	private Calendar lastUpdated;
}
