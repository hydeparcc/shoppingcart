package com.hydeparcc.productlist;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hydeparcc.productlist.model.Product;
import com.hydeparcc.productlist.repository.ProductRepository;
import com.hydeparcc.productlist.service.ProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductlistApplicationTests {

	@Autowired
	private ProductService productService;

	/**
	 * Mocking the repository instead of calling actual repository
	 */
	@MockBean
	private ProductRepository productRepository;


	@Test
	public void testGetAllProducts() {

		BigDecimal price = new BigDecimal(199);

		Product product  = new Product();
		product.setId(1);
		product.setName("Test Phone");
		product.setDesc("It is a Test Phone");
		product.setPrice(price);

		Product product2  = new Product();
		product2.setId(1);
		product2.setName("Test Phone");
		product2.setDesc("It is a Test Phone");
		product2.setPrice(price);

		List<Product> productList = new ArrayList<Product>();
		productList.add(product);
		productList.add(product2);

		Mockito.when(productRepository.findAll()).thenReturn(productList);

		assertThat(productService.getAllProducts()).isEqualTo(productList);

	}

}
