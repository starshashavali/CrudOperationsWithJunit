package com.getinfy.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.getinfy.entity.ProductEntity;
import com.getinfy.service.ProductService;

@RestController
public class ProductRest {
	@Autowired
	private ProductService productService;

	@PostMapping("/saveProduct")
	private ResponseEntity<String> saveProduct(@RequestBody ProductEntity entity) {

		boolean saveproduct = productService.saveproduct(entity);

		if (saveproduct) {

			return new ResponseEntity<String>("Record saved", HttpStatus.CREATED);

		}
		return new ResponseEntity<String>("Some Issue", HttpStatus.BAD_REQUEST);

	}

	@GetMapping("/getProduct/{id}")
	private ResponseEntity<ProductEntity> getProduct(@PathVariable Integer id) {

		ProductEntity getproduct = productService.Getproduct(id);
		if (getproduct != null) {
			return new ResponseEntity<ProductEntity>(getproduct, HttpStatus.OK);

		}
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/deleteProduct/{id}")
	private ResponseEntity<String> deleteProduct(@PathVariable Integer id) {

		boolean deleteProduct = productService.deleteProduct(id);
		if (deleteProduct) {
			return new ResponseEntity<String>("product deleted", HttpStatus.OK);

		}
		return new ResponseEntity<String>("product not deleted", HttpStatus.BAD_REQUEST);

	}
	
	
	@GetMapping("/getProducts")
	private ResponseEntity<List<ProductEntity>> getProducts() {

		 List<ProductEntity> products = productService.getProducts();
		 
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<List<ProductEntity>>(products,HttpStatus.OK);

	}
	
}
