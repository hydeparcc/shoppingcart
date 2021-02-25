package com.hydeparcc.products.service;

import com.hydeparcc.products.model.Product;
import com.hydeparcc.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
