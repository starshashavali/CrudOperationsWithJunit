package com.getinfy.serviceimpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.getinfy.entity.ProductEntity;
import com.getinfy.repo.ProductRepo;
import com.getinfy.service.ProductService;

@Service
public class ProductServiceimpl implements ProductService {

	@Autowired
	ProductRepo prodrepo;

	@Override
	public boolean saveproduct(ProductEntity prodentity) {

		if (prodentity == null) {

			return false;
		}

		prodrepo.save(prodentity);
		return true;

	}

	@Override
	public ProductEntity Getproduct(Integer id) {
		Optional<ProductEntity> findById = prodrepo.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		}

		return null;
	}

	@Override
	public boolean deleteProduct(Integer id) {

		boolean existsById = prodrepo.existsById(id);

		if (existsById) {
			prodrepo.deleteById(id);
			return true;
		}

		return false;
	}
	@Override
	public List<ProductEntity> getProducts() {
		List<ProductEntity> products = prodrepo.findAll();
		if(products.isEmpty()) {
			return Collections.emptyList();
		}
		return products;
	}

}
