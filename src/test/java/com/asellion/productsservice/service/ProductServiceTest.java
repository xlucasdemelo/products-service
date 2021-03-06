package com.asellion.productsservice.service;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.asellion.productsservice.exception.ProductException;
import com.asellion.productsservice.exception.ProductNotFoundException;
import com.asellion.productsservice.model.Product;
import com.asellion.productsservice.model.ProductDTO;
import com.asellion.productsservice.repository.ProductRepository;
import com.asellion.productsservice.util.ProductMapper;

/**
 * Class that will test the logic of Service layer
 * 
 * @author lucas
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	
	@Spy
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Mock
	private ProductRepository productRepository;
	
	@Mock
	private ProductMapper productMapper;
	
	private Product getSampleProduct() {
		return new Product(100L, "Iphone11", new BigDecimal("700"), Calendar.getInstance(), "Description");
	}
	
	/**
	 * 
	 */
	@Test
	public void testInsertnewProduct_successCase(){
		Mockito.when(this.productMapper.dtoToEntity(any(ProductDTO.class))).thenReturn(this.getSampleProduct());
		Mockito.when(this.productRepository.save(any(Product.class))).thenReturn(this.getSampleProduct());
		
		final ProductDTO productDTO = new ProductDTO("Iphone12", new BigDecimal("800"), "Description");
		final Product product = this.productService.insertProduct(productDTO);
		
		assertThat(product, Matchers.notNullValue());
		assertThat(product.getId(), Matchers.notNullValue());
		assertThat(product.getCurrentPrice(), Matchers.notNullValue());
		assertThat(product.getLastUpdated(), Matchers.notNullValue());
		assertThat(product.getDescription(), Matchers.notNullValue());
	}
	
	/**
	 * 
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testInsertnewProduct_mustFailWithoutMandatoryFields(){
		Product product = this.getSampleProduct();
		product.setCurrentPrice(null);
		
		Mockito.when(this.productMapper.dtoToEntity(any(ProductDTO.class))).thenReturn(product);
		Mockito.when(this.productRepository.save(any(Product.class))).thenThrow(ConstraintViolationException.class);
		
		final ProductDTO productDTO = new ProductDTO("Iphone12", null, "Description");
		product = this.productService.insertProduct(productDTO);
		
		assertThat(product, IsNull.nullValue());
	}
	
	/**
	 * 
	 */
	@Test
	public void testInsertnewProduct_mustFailWithNullObject(){
		final Product product = this.productService.insertProduct(null);
		assertThat(product, IsNull.nullValue());
	}
	
	@Test
	public void testUpdateProduct_successCase() throws ProductException{
		Mockito.when(this.productRepository.findById((any(Long.class))) ).thenReturn( Optional.of(this.getSampleProduct()) );
		Mockito.when(this.productRepository.save(any(Product.class))).thenReturn(new Product(100L, "Iphone12", new BigDecimal("800"), Calendar.getInstance(), "Description2" ));
		
		final ProductDTO productDTO = new ProductDTO("Iphone12", new BigDecimal("800"), "Description2");
		final Product product = this.productService.updateProduct(100L, productDTO);
		
		assertThat(product, Matchers.notNullValue());
		assertThat(product.getId(), Matchers.notNullValue());
		assertThat(product.getName(), Matchers.equalTo("Iphone12"));
		assertThat(product.getCurrentPrice(), Matchers.notNullValue());
		assertThat(product.getCurrentPrice(), Matchers.equalTo(new BigDecimal("800")));
		assertThat(product.getLastUpdated(), Matchers.notNullValue());
		assertThat(product.getDescription(), Matchers.notNullValue());
		assertThat(product.getDescription(), Matchers.equalTo("Description2"));
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void testUpdateProduct_mustFailWithNonExistentId() throws ProductException{
		Mockito.when(this.productRepository.findById(any(Long.class)) ).thenReturn(Optional.empty());
		final ProductDTO productDTO = new ProductDTO("Iphone12", new BigDecimal("800"), "Description");
		this.productService.updateProduct(101L, productDTO);
	}
	
	@Test
	public void testListAllProjects_mustReturnAll(){
		List<Product> products = new ArrayList<Product>();
		products.add(this.getSampleProduct());
		
		Mockito.when(this.productRepository.findAll() ).thenReturn(products);
		
		products = this.productService.listProducts();
		
		assertThat(products, Matchers.notNullValue());
		assertThat(products, Matchers.hasSize(1));
	}
	
	@Test
	public void testListProjectByIdReturnOne() throws ProductNotFoundException{
		Mockito.when(this.productRepository.findById((any(Long.class))) ).thenReturn( Optional.of(this.getSampleProduct()) );
		
		final Product product = this.productService.getProductById(100L);
		
		assertThat(product, Matchers.notNullValue());
	}
	
	@Test(expected = ProductNotFoundException.class)
	public void testListProjectById_nonExistentId() throws ProductNotFoundException{
		Mockito.when(this.productRepository.findById((any(Long.class))) ).thenReturn( Optional.empty() );
		this.productService.getProductById(100L);
	}
}
