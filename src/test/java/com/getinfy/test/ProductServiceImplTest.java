package com.getinfy.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.getinfy.entity.ProductEntity;
import com.getinfy.repo.ProductRepo;
import com.getinfy.serviceimpl.ProductServiceimpl;

@SpringBootTest 
public class ProductServiceImplTest {
	
	
	
	@Mock         //inject the mock object of repository
	ProductRepo productRepo;
	
	@InjectMocks    //inject the service
	ProductServiceimpl productService;

	@Test
	void getProducts() {
		
		List<ProductEntity> products=new ArrayList<>();
		products.add(new ProductEntity(1,"Mouse", 220.0));
		products.add(new ProductEntity(2,"Keyboard", 250.0));
		when(productService.getProducts()).thenReturn(products);
		 productRepo.findAll();
		assertEquals(2, products.size());
		
		
	}
	
	@Test
	void getnegativeProducts() {
		
		List<ProductEntity> products=new ArrayList<>();
		
		when(productRepo.findAll()).thenReturn(products);
		 List<ProductEntity> findAll = productService.getProducts();
		assertEquals(products, findAll);
		
		
	}
	
	@Test
	void saveProduct() {
	    ProductEntity product=new ProductEntity(1,"Mouse",230.0);
		productService.saveproduct(product);
		
		verify(productRepo).save(product);
		
	}
	
	@Test
	void saveNegativeProduct() {
	    ProductEntity product=null;
		productService.saveproduct(product);
		
		
		
	}
	
	
}
