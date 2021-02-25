package com.hydeparcc.cart.model;

import java.util.List;

public class ApiResponse {
    List<Cart> cartItems;

    public List<Cart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<Cart> cartItems) {
        this.cartItems = cartItems;
    }
}
