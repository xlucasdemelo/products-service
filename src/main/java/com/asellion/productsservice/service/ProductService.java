package com.asellion.productsservice.service;

import java.util.List;

import com.asellion.productsservice.exception.ProductException;
import com.asellion.productsservice.exception.ProductNotFoundException;
import com.asellion.productsservice.model.Product;
import com.asellion.productsservice.model.ProductDTO;

/**
 * Implementation of {@link ProductService}
 * 
 * Class that held the business logic for product methods
 * 
 * @author lucas
 *
 */
public interface ProductService {
	
	/**
	 * This emthod will list all products in the database
	 * 
	 * @return
	 */
	List<Product> listProducts();
	
	/**
	 * This method will return a product by its ID
	 * 
	 * @param id
	 * @return
	 */
	Product getProductById(long id) throws ProductNotFoundException;
	
	/**
	 * This method will update a existent Product register 
	 * 
	 * @param productDTO
	 * @return
	 */
	Product updateProduct(long id, ProductDTO productDTO) throws ProductException;
	
	/**
	 * This method will create a new Product 
	 * 
	 * @param productDTO
	 * @return
	 */
	Product insertProduct(ProductDTO productDTO);
	
}
