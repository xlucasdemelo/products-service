package com.asellion.productsservice.integration;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Base64;

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
   public void sendPaylaod_ShouldCreateProduct() throws Exception {
       this.mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
               .content( "{ \"name\":\"Product55\", \"currentPrice\":\"200\" }" )
               .contentType(MediaType.APPLICATION_JSON)
               
       )
       .andExpect(status().is2xxSuccessful())
       .andExpect(content().json("{\"id\":102,\"name\":\"Product55\",\"currentPrice\":200}"));
   }
}
