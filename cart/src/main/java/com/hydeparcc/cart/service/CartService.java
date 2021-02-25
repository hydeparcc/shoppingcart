package com.hydeparcc.cart.service;

import com.hydeparcc.cart.model.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> getAllCartItems(String userSessionId);

    public Cart saveItem(Cart cart);

    public boolean deleteItem(Cart cart);
}
