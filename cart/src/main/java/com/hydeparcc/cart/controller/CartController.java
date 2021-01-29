package com.hydeparcc.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hydeparcc.cart.model.ApiResponse;
import com.hydeparcc.cart.model.Cart;
import com.hydeparcc.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService cartService;

	@GetMapping("/list")
	public ResponseEntity<ApiResponse> getCartItems(@RequestHeader (value = "sessionid", required = true) String sessionId){

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("sessionid", sessionId);

		try {
			List<Cart> cartItems = cartService.getAllCartItems(sessionId);

			if(cartItems.size() > 0) {
				ApiResponse response = new ApiResponse();
				response.setCartItems(cartItems);
				response.setSessionId(sessionId);
				return new ResponseEntity<ApiResponse>(response, responseHeaders, HttpStatus.OK);
			} else {
				return new ResponseEntity<ApiResponse>(responseHeaders, HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<ApiResponse>(responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR); 
		}

	}

	@PostMapping("/add")
	public ResponseEntity<Cart> addItem(@RequestBody Cart cart, @RequestHeader (value = "sessionid", required = true) String sessionId) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("sessionid", sessionId);
		Cart savedItem = null;
		try {
			cart.setUserSessionId(sessionId);
			savedItem = cartService.saveItem(cart);
			if(savedItem == null) {
				return new ResponseEntity<Cart>(responseHeaders, HttpStatus.BAD_REQUEST);
			}else {
				return new ResponseEntity<Cart>(savedItem, responseHeaders, HttpStatus.OK);
			}
		}catch(Exception e) {
			return new ResponseEntity<Cart>(responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@PostMapping("/delete")
	public ResponseEntity deleteItem(@RequestBody Cart cart, @RequestHeader (value = "sessionid", required = true) String sessionId) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("sessionid", sessionId);
		try {
			cart.setUserSessionId(sessionId);
			boolean isDeleted = cartService.deleteItem(cart);
			if(isDeleted) {
				return new ResponseEntity(responseHeaders, HttpStatus.OK);
			}else {
				return new ResponseEntity(responseHeaders, HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity(responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
