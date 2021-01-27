package com.hydeparcc.cart.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hydeparcc.cart.model.Cart;
import com.hydeparcc.cart.repository.CartRepository;

@Service
public class CartServiceImplementation implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public List<Cart> getAllCartItems(String userSessionId) {
		List<Cart> cartItems = cartRepository.findAllCartItems(userSessionId);
		return cartItems;
	}

	@Override
	public Cart saveItem(Cart cart) {

		Cart savedCartItem = null;

		if(cartRepository.existsCartItemCustomQuery(cart.getUserSessionId(), cart.getProductId())) {
			Optional<Cart> productInCart = Optional.of(cartRepository.findCartItem(cart.getUserSessionId(), cart.getProductId()));
			if(productInCart.isPresent()) {
				int productQuantityInCart = productInCart.get().getProductId();
				productQuantityInCart = productQuantityInCart + cart.getQuantity();
				productInCart.get().setQuantity(productQuantityInCart);
				savedCartItem = cartRepository.save(productInCart.get());				
			}

		}else {
			savedCartItem = cartRepository.save(cart);
		}

		return savedCartItem;
	}

	@Override
	public boolean deleteItem(Cart cart) {
		if(cartRepository.existsCartItemCustomQuery(cart.getUserSessionId(), cart.getProductId())) {
			Optional<Cart> productInCart = Optional.of(cartRepository.findCartItem(cart.getUserSessionId(), cart.getProductId()));
			if(productInCart.isPresent()) {
				int productQuantityInCart = productInCart.get().getQuantity();
				if(productQuantityInCart > 0 && productQuantityInCart > cart.getQuantity()) {
					productQuantityInCart = productQuantityInCart - cart.getQuantity();
					productInCart.get().setQuantity(productQuantityInCart);
					cartRepository.save(productInCart.get());
					return true;
				} else {
					cartRepository.deleteCartItem(cart.getUserSessionId(), cart.getProductId());
					return true;
				}
			} else {
				return false;	
			}
		}
		return false;
	}
}
