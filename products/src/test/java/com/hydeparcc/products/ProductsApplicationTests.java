package com.hydeparcc.products;

import com.hydeparcc.products.model.Product;
import com.hydeparcc.products.repository.ProductRepository;
import com.hydeparcc.products.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ProductsApplicationTests {

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
