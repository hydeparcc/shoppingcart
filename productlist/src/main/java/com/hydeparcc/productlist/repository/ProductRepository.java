package com.hydeparcc.productlist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydeparcc.productlist.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}
