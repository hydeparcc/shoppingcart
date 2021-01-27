package com.hydeparcc.cart.service;

import java.util.List;

import com.hydeparcc.cart.model.Cart;

public interface CartService {

	public List<Cart> getAllCartItems(String userSessionId);
	
	public Cart saveItem(Cart cart);

	public boolean deleteItem(Cart cart);
	
	

}
