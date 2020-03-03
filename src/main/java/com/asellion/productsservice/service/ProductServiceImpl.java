package com.asellion.productsservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asellion.productsservice.model.Product;
import com.asellion.productsservice.model.ProductDTO;
import com.asellion.productsservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> listProducts(){
		return null;
	}
	
	@Override
	public Product getProductById(long id) {
		return null;
	}
	
	@Override
	public Product updateProduct(ProductDTO productDTO) {
		return null;
	}
	
	@Override
	public Product insertProduct(ProductDTO productDTO) {
		return null;
	}
}
