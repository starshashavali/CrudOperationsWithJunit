package com.getinfy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.getinfy.entity.ProductEntity;

public interface ProductRepo extends JpaRepository<ProductEntity, Integer>{

}
