package com.getinfy.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getinfy.Rest.ProductRest;
import com.getinfy.entity.ProductEntity;
import com.getinfy.service.ProductService;

@WebMvcTest(value = ProductRest.class)
public class RestControllerTest {

	@Autowired
	MockMvc mockMvc; // reuest perform

	@MockBean
	ProductService productService;

	private static String convertData(ProductEntity entity) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(entity);
		return writeValueAsString;
	}

	@Test
	void SaveProductTest() throws Exception {

		// method return type argument boolean
		ProductEntity entity = new ProductEntity(1, "Mouse", 230.0);

		when(productService.saveproduct(entity)).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.post("/saveProduct").contentType(MediaType.APPLICATION_JSON)
				.content(convertData(entity))).andExpect(MockMvcResultMatchers.status().isCreated());

	}

	@Test
	void SaveProductNegativeTest() throws Exception {

		// method return type argument boolean
		ProductEntity entity = new ProductEntity(1, "Mouse", 230.0);

		when(productService.saveproduct(entity)).thenReturn(false);

		mockMvc.perform(MockMvcRequestBuilders.post("/saveProduct").contentType(MediaType.APPLICATION_JSON)
				.content(convertData(entity))).andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	@Test
	void getProductTest() throws Exception {

		ProductEntity product = new ProductEntity(1, "Mouse", 890.0);
		int id = 1;

		when(productService.Getproduct(1)).thenReturn(product);

		mockMvc.perform(MockMvcRequestBuilders.get("/getProduct/" + id).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void getProductTestinvalidproduct() throws Exception {

		int id = -1;
		when(productService.Getproduct(-1)).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders.get("/getProduct/" + id).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());

	}
	//delete test cases----------------------------------------------------------------------------
	@Test
	void deleteProduct() throws Exception {
		int id=2;
		
		when(productService.deleteProduct(id)).thenReturn(true);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProduct/"+id)).andExpect(status().isOk());
		
	}
	@Test
	void deleteNegativeProduct() throws Exception {
		int id=5;
		
		when(productService.deleteProduct(id)).thenReturn(false);
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/deleteProduct/"+id)).andExpect(status().isBadRequest());
		
	}
	
	@Test
	void getProducts() throws Exception {
		
		List<ProductEntity> products=new ArrayList<>();
		products.add(new ProductEntity(1, "kumar", 330.0));
		products.add(new ProductEntity(1, "siva", 340.0));
		when(productService.getProducts()).thenReturn(products);
		mockMvc.perform(MockMvcRequestBuilders.get("/getProducts")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		
		
	}
	
	@Test
	void getInvalidProducts() throws Exception {
		
		
		when(productService.getProducts()).thenReturn(Collections.EMPTY_LIST);
		mockMvc.perform(MockMvcRequestBuilders.get("/getProducts")).andExpect(status().isBadRequest());
		
	}
	
	
	
	
	
}
