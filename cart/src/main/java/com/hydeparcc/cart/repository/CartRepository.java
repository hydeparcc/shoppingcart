package com.hydeparcc.cart.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hydeparcc.cart.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	@Query("select c from Cart c where lower(c.userSessionId) = lower(:userSessionId)")
	public List<Cart> findAllCartItems(@Param("userSessionId") String userSessionId);
	
	@Query("select case when count(c)> 0 then true else false end from Cart c where lower(:userSessionId) = lower(c.userSessionId) and :productId = c.productId")
	boolean existsCartItemCustomQuery(@Param("userSessionId") String userSessionId, @Param("productId") int productId);
	
	@Query("select c from Cart c where lower(c.userSessionId) = lower(:userSessionId) and c.productId = :productId")
	public Cart findCartItem(@Param("userSessionId") String userSessionId, @Param("productId") int productId);
	
	@Transactional
	@Modifying
	@Query("delete from Cart c where lower(c.userSessionId) = lower(:userSessionId) and c.productId = :productId")
	public void deleteCartItem(@Param("userSessionId") String userSessionId, @Param("productId") int productId);

}
