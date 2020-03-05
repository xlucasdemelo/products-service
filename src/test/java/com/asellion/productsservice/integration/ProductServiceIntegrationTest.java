package com.asellion.productsservice.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.asellion.productsservice.ProductsServiceApplication;
import com.asellion.productsservice.model.ProductDTO;
import com.fasterxml.jackson.databind.ObjectMapper;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductsServiceApplication.class)
public class ProductServiceIntegrationTest {
	
	@Autowired
    MockMvc mockMvc;
	
	/**
	 * This will test the creation of a Product
	 * 
	 * @throws Exception
	 */
   @Test
   @Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
   public void sendPayload_ShouldCreateProduct() throws Exception {
       this.mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
               .content( "{ \"name\":\"Product55\", \"currentPrice\":\"200\" }" )
               .contentType(MediaType.APPLICATION_JSON)
               
       )
       .andExpect(status().is2xxSuccessful());
   }
   
   /**
    * Try to insert a product without value will throw a error
    * 
    * @throws Exception
    */
   @Test
   @Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
   public void sendPayload_ShouldFailWithoutMandatoryFields() throws Exception {
	   final ProductDTO product = new ProductDTO("Samsung Galaxy X", null, "Description");
	   
	   ObjectMapper mapper = new ObjectMapper();
	   
       this.mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
               .content( mapper.writeValueAsString(product))
               .contentType(MediaType.APPLICATION_JSON)
               
       )
       .andExpect(status().is4xxClientError());
   }
   
   /**
	 * This will test the Upadte of a Product
	 * 
	 * @throws Exception
	 */
  @Test
  @Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
  public void updateProduct_successCase() throws Exception {
	  final ProductDTO productDTO = new ProductDTO("Samsung Galaxy X", new BigDecimal("100"),"Description");
	  ObjectMapper mapper = new ObjectMapper();
	  
      this.mockMvc.perform(MockMvcRequestBuilders.put("/api/products/100")
              .content( mapper.writeValueAsString(productDTO) )
              .contentType(MediaType.APPLICATION_JSON)
              
      )
      .andExpect(status().is2xxSuccessful())
      .andExpect(content().json("{\"id\":100,\"name\":\"Samsung Galaxy X\",\"currentPrice\":100}"));
  }
  
  /**
	 * This will test the creation of a Product
	 * 
	 * @throws Exception
	 */
	@Test
	@Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void updateProduct_failWithoutMandatoryFields() throws Exception {
		  final ProductDTO productDTO = new ProductDTO("Samsung Galaxy X", null, "Description");
		  ObjectMapper mapper = new ObjectMapper();
		  
	    this.mockMvc.perform(MockMvcRequestBuilders.put("/api/products/100")
	            .content( mapper.writeValueAsString(productDTO) )
	            .contentType(MediaType.APPLICATION_JSON)
	            
	    )
	    .andExpect(status().is4xxClientError());
	}
	
	@Test
	@Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void updateProduct_failNonExistentProduct() throws Exception {
		  final ProductDTO productDTO = new ProductDTO("Samsung Galaxy X", new BigDecimal("100"), "Description");
		  ObjectMapper mapper = new ObjectMapper();
		  
	    this.mockMvc.perform(MockMvcRequestBuilders.put("/api/products/1")
	            .content( mapper.writeValueAsString(productDTO) )
	            .contentType(MediaType.APPLICATION_JSON)
	            
	    )
	    .andExpect(status().is4xxClientError());
	}
	
	@Test
	@Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void findAllProducts() throws Exception {
	    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
	    .andExpect( status().is2xxSuccessful());
	}
	
	/**
	 * Test find by id
	 * @throws Exception
	 */
	@Test
	@Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void findProductById() throws Exception {
	    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/products/100"))
	    .andExpect( status().is2xxSuccessful());
	}
	
	
	/**
	 * Test find by id With non existent ID
	 * @throws Exception
	 */
	@Test
	@Sql(scripts = "classpath:data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void findProductById_nonExistentProduct() throws Exception {
	    this.mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
	    .andExpect( status().is4xxClientError());
	}
}
