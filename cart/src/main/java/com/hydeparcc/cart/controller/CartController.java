package com.hydeparcc.cart.controller;

import com.hydeparcc.cart.model.ApiResponse;
import com.hydeparcc.cart.model.Cart;
import com.hydeparcc.cart.service.CartService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/cart")
public class CartController {
	
	private static final Logger LOGGER = LogManager.getLogger(CartController.class);
	
    @Autowired
    CartService cartService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getCartItems(@RequestHeader(value="X-Guest-User", required=true) String randomUser){
    	LOGGER.info("Getting Cart Items for guest =======> "+ randomUser);
        try {
            List<Cart> cartItems = cartService.getAllCartItems(randomUser);

            if(cartItems.size() > 0) {
                ApiResponse response = new ApiResponse();
                response.setCartItems(cartItems);
                return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
            } else {
            	LOGGER.info("Cart Empty for guest =======> "+ randomUser);
                return new ResponseEntity<ApiResponse>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
        	LOGGER.error("Exception: "+ e.getMessage() +" getting Cart Items for guest =======> "+ randomUser);
            return new ResponseEntity<ApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addItem(@RequestBody Cart cart, @RequestHeader(value="X-Guest-User", required=true) String randomUser) {
        Cart savedItem = null;
        try {
            cart.setUserSessionId(randomUser);
            savedItem = cartService.saveItem(cart);
            if(savedItem == null) {
                return new ResponseEntity<Cart>(HttpStatus.BAD_REQUEST);
            }else {
                return new ResponseEntity<Cart>(savedItem, HttpStatus.OK);
            }
        }catch(Exception e) {
        	LOGGER.error("Exception: "+ e.getMessage() +" adding Cart Item for guest =======> "+ randomUser);
            return new ResponseEntity<Cart>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/delete")
    public ResponseEntity deleteItem(@RequestBody Cart cart, @RequestHeader(value="X-Guest-User", required=true) String randomUser) {

        try {
            cart.setUserSessionId(randomUser);
            boolean isDeleted = cartService.deleteItem(cart);
            if(isDeleted) {
                return new ResponseEntity(HttpStatus.OK);
            }else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
        	LOGGER.error("Exception: "+ e.getMessage() +" deleting Cart Item for guest =======> "+ randomUser);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
