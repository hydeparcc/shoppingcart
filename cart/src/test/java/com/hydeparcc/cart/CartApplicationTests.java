package com.hydeparcc.cart;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.hydeparcc.cart.model.Cart;
import com.hydeparcc.cart.repository.CartRepository;
import com.hydeparcc.cart.service.CartService;

@SpringBootTest
class CartApplicationTests {

	@Autowired
	private CartService cartService;

	/**
	 * Mocking the repository instead of calling actual repository
	 */
	@MockBean
	private CartRepository cartRepository;

	@Test
	public void testGetAllItems() {

		String userSessionId = "testSession";
		BigDecimal price = new BigDecimal(199);

		Cart cart  = new Cart();
		cart.setId(1);
		cart.setProductId(11);
		cart.setProductName("Test Phone");
		cart.setPrice(price);
		cart.setQuantity(1);
		cart.setUserSessionId(userSessionId);

		Cart cart2  = new Cart();
		cart2.setId(2);
		cart2.setProductId(22);
		cart2.setProductName("Test Phone 2");
		cart2.setPrice(price);
		cart2.setQuantity(2);
		cart.setUserSessionId(userSessionId);

		List<Cart> cartList = new ArrayList<Cart>();
		cartList.add(cart);
		cartList.add(cart2);

		Mockito.when(cartRepository.findAllCartItems(userSessionId)).thenReturn(cartList);

		assertThat(cartService.getAllCartItems(userSessionId)).isEqualTo(cartList);

	}

	@Test
	public void testSaveItem() {

		String userSessionId = "testSession";
		BigDecimal price = new BigDecimal(199);

		Cart cart  = new Cart();
		cart.setId(1);
		cart.setProductId(11);
		cart.setProductName("Test Phone");
		cart.setPrice(price);
		cart.setQuantity(1);
		cart.setUserSessionId(userSessionId);

		Mockito.when(cartRepository.existsCartItemCustomQuery(userSessionId, cart.getProductId())).thenReturn(true);
		Mockito.when(cartRepository.findCartItem(userSessionId, cart.getProductId())).thenReturn(cart);
		Mockito.when(cartRepository.save(cart)).thenReturn(cart);

		assertThat(cartService.saveItem(cart)).isEqualTo(cart);

	}

	@Test
	public void testDeleteItemCartQuantityMore() {

		String userSessionId = "testSession";
		BigDecimal price = new BigDecimal(199);

		Cart cart  = new Cart();
		cart.setId(1);
		cart.setProductId(11);
		cart.setProductName("Test Phone");
		cart.setPrice(price);
		cart.setQuantity(2);
		cart.setUserSessionId(userSessionId);

		Cart cart2  = new Cart();
		cart2.setId(1);
		cart2.setProductId(11);
		cart2.setProductName("Test Phone");
		cart2.setPrice(price);
		cart2.setQuantity(1);
		cart2.setUserSessionId(userSessionId);

		Mockito.when(cartRepository.existsCartItemCustomQuery(cart2.getUserSessionId(), cart2.getProductId())).thenReturn(true);
		Mockito.when(cartRepository.findCartItem(userSessionId, cart2.getProductId())).thenReturn(cart);
		Mockito.when(cartRepository.save(cart)).thenReturn(cart);

		assertThat(cartService.deleteItem(cart2)).isEqualTo(true);

	}

	@Test
	public void testDeleteItemCartQuantityEqual() {

		String userSessionId = "testSession";
		BigDecimal price = new BigDecimal(199);

		Cart cart  = new Cart();
		cart.setId(1);
		cart.setProductId(11);
		cart.setProductName("Test Phone");
		cart.setPrice(price);
		cart.setQuantity(1);
		cart.setUserSessionId(userSessionId);

		Cart cart2  = new Cart();
		cart2.setId(1);
		cart2.setProductId(11);
		cart2.setProductName("Test Phone");
		cart2.setPrice(price);
		cart2.setQuantity(1);
		cart2.setUserSessionId(userSessionId);

		Mockito.when(cartRepository.existsCartItemCustomQuery(cart2.getUserSessionId(), cart2.getProductId())).thenReturn(true);
		Mockito.when(cartRepository.findCartItem(userSessionId, cart2.getProductId())).thenReturn(cart);
		doNothing().when(cartRepository).deleteCartItem(cart2.getUserSessionId(), cart2.getProductId());

		assertThat(cartService.deleteItem(cart2)).isEqualTo(true);

	}

}
