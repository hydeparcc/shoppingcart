package com.hydeparcc.productlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydeparcc.productlist.model.Product;
import com.hydeparcc.productlist.repository.ProductRepository;

@Service
public class ProductServiceImplementation implements ProductService {
	
	@Autowired
	ProductRepository productRepo;

    @Override
    public List<Product> getAllProducts() {
    	
    	List<Product> productList = productRepo.findAll();
    	
        return productList;
    }

}
