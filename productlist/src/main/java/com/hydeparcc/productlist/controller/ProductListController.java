package com.hydeparcc.productlist.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hydeparcc.productlist.model.Product;
import com.hydeparcc.productlist.service.ProductService;
import com.hydeparcc.productlist.model.ApiResponse;

@RestController
@RequestMapping("/")
public class ProductListController {
	
	@Autowired
	ProductService productService;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<ApiResponse> getProductList(@RequestHeader (value = "sessionid", required = true) String sessionId){
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("sessionid", sessionId);
				
		try {
			List<Product> productList = productService.getAllProducts();
			if(productList.size() > 0) {
				ApiResponse response = new ApiResponse();
				response.setProducts(productList);
				response.setSessionId(sessionId);
				return new ResponseEntity<ApiResponse>(response, responseHeaders, HttpStatus.OK);
			} else {
				return new ResponseEntity<ApiResponse>(responseHeaders, HttpStatus.NO_CONTENT);
			}
		}catch(Exception e) {
			return new ResponseEntity<ApiResponse>(responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
