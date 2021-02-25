package com.hydeparcc.products.controller;

import com.hydeparcc.products.model.ApiResponse;
import com.hydeparcc.products.model.Product;
import com.hydeparcc.products.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/product")
public class ProductListController {
    @Autowired
    ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getProductList(@RequestHeader(value="X-Guest-User") String randomUser){

        try {
            List<Product> productList = productService.getAllProducts();
            if(productList.size() > 0) {
                ApiResponse response = new ApiResponse();
                response.setProducts(productList);
                return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<ApiResponse>(HttpStatus.NO_CONTENT);
            }
        }catch(Exception e) {
            return new ResponseEntity<ApiResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
