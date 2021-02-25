package com.hydeparcc.products.model;

import java.util.List;

public class ApiResponse {
    List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
