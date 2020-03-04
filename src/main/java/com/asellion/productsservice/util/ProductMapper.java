package com.asellion.productsservice.util;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asellion.productsservice.model.Product;
import com.asellion.productsservice.model.ProductDTO;


@Component
public class ProductMapper {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public Product dtoToEntity(ProductDTO dto) {
		return this.modelMapper.map(dto, Product.class);
	}
	
	public void mergeDTOToEntity(ProductDTO dto, Product product) {
		this.modelMapper.map(dto, product);
	}
}
