package com.hydeparcc.cart.model;

import java.util.List;


public class ApiResponse {
	
	private List<Cart> cartItems;
	private String sessionId;

	public List<Cart> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<Cart> cartItems) {
		this.cartItems = cartItems;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	

}
