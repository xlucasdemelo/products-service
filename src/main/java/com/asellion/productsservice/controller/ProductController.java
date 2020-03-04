package com.asellion.productsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asellion.productsservice.exception.ProductException;
import com.asellion.productsservice.exception.ProductNotFoundException;
import com.asellion.productsservice.model.Product;
import com.asellion.productsservice.model.ProductDTO;
import com.asellion.productsservice.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This class is the controller that exposes public endpoints 
 * 		in order to the application could be consumed
 * 
 * @author lucas
 *
 */
@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * 
	 * @return
	 */
	@ApiOperation("List All products from database")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return a list of products")
	})
	@GetMapping
	public ResponseEntity<List<Product>> listProducts(){
		final List<Product> products = this.productService.listProducts();
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws ProductNotFoundException
	 */
	@ApiOperation("Get One product from the database via id")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return one product with the specified id"),
		@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database")
	})
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException{
		final Product product = this.productService.getProductById(id);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id
	 * @param productDTO
	 * @return
	 * @throws ProductException
	 */
	@ApiOperation("Update the product with ID")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return the updated product"),
		@ApiResponse(code = 404, message = "It will return a NOT_FOUND if the id is not found on the database"),
		@ApiResponse(code = 400, message = "It will return a BAD_REQUEST if the required fields are missing")
	})
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct( @PathVariable("id") Long id, @RequestBody(required = true) ProductDTO productDTO ) throws ProductException{
		final Product product = this.productService.updateProduct(id, productDTO);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param productDTO
	 * @return
	 */
	@ApiOperation("Insert a new product in database")
	@ApiResponses({
		@ApiResponse(code = 200, message = "It will return the new product"),
		@ApiResponse(code = 400, message = "It will return a BAD_REQUEST if the required fields are missing")
	})
	@PostMapping
	public ResponseEntity<Product> insertProduct( @RequestBody(required = true) ProductDTO productDTO ){
		final Product product = this.productService.insertProduct(productDTO);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
