package com.asellion.productsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asellion.productsservice.exception.ProductException;
import com.asellion.productsservice.exception.ProductNotFoundException;
import com.asellion.productsservice.model.Product;
import com.asellion.productsservice.model.ProductDTO;
import com.asellion.productsservice.repository.ProductRepository;
import com.asellion.productsservice.util.ProductConstants;
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
		return this.productRepository.findAll();
	}
	
	@Override
	public Product getProductById(long id) throws ProductNotFoundException {
		log.info(ProductConstants.RETRIEVING_ALL_PRODUCTS_FROM_DATABASE);
		return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException() );
	}
	
	@Override
	public Product updateProduct(long id, ProductDTO productDTO) throws ProductException {
		log.info(ProductConstants.UPDATING_PRODUCT_WITH_ID + id);
		final Product product = this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException() );
		
		this.mapper.mergeDTOToEntity(productDTO, product);
		
		return this.productRepository.save(product);
	}
	
	@Override
	public Product insertProduct(ProductDTO productDTO) {
		log.info(ProductConstants.SAVING_PRODUCT);
		return this.productRepository.save(this.mapper.dtoToEntity(productDTO));
	}
}
