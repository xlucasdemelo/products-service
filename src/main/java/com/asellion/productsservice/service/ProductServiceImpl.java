package com.asellion.productsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asellion.productsservice.exception.ProductException;
import com.asellion.productsservice.exception.ProductNotFoundException;
import com.asellion.productsservice.model.Product;
import com.asellion.productsservice.model.ProductDTO;
import com.asellion.productsservice.repository.ProductRepository;
import com.asellion.productsservice.util.ProductMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public List<Product> listProducts(){
		return null;
	}
	
	@Override
	public Product getProductById(long id) {
		return null;
	}
	
	@Override
	public Product updateProduct(long id, ProductDTO productDTO) throws ProductException {
		log.info("Updating Id: " + id);
		final Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException() );
		
		this.mapper.mergeDTOToEntity(productDTO, product);
		
		return this.productRepository.save(product);
	}
	
	@Override
	public Product insertProduct(ProductDTO productDTO) {
		log.info("Saving ");
		
		return this.productRepository.save(this.mapper.dtoToEntity(productDTO));
	}
}
