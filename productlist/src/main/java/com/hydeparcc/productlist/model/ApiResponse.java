package com.hydeparcc.productlist.model;

import java.util.List;

public class ApiResponse {
	
	private List<Product> products;
	private String sessionId;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
