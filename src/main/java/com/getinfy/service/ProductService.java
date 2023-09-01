package com.getinfy.service;

import java.util.List;

import com.getinfy.entity.ProductEntity;

public interface ProductService {
	
	
	 boolean saveproduct(ProductEntity prodentity);
	 
	 public ProductEntity Getproduct(Integer id);
	 
	 public boolean deleteProduct(Integer id);
	 
	 public List<ProductEntity> getProducts();
		 
	 

}
