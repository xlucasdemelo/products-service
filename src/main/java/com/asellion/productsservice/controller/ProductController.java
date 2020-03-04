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
import com.asellion.productsservice.model.Product;
import com.asellion.productsservice.model.ProductDTO;
import com.asellion.productsservice.service.ProductService;

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
	
	@GetMapping
	public ResponseEntity<List<Product>> listProducts(){
		final List<Product> products = this.productService.listProducts();
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id){
		final Product product = this.productService.getProductById(id);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct( @PathVariable("id") Long id, @RequestBody(required = true) ProductDTO productDTO ) throws ProductException{
		final Product product = this.productService.updateProduct(id, productDTO);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> insertProduct( @RequestBody(required = true) ProductDTO productDTO ){
		final Product product = this.productService.insertProduct(productDTO);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
}
