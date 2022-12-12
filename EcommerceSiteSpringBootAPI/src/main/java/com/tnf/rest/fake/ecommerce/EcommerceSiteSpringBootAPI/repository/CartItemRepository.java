package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders.Cart;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders.CartItem;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;

public interface CartItemRepository extends JpaRepository<CartItem,Integer> {
	
	List<CartItem> findAllByCart(Cart cart);
	Optional<CartItem> findByProductAndCart(Product product, Cart cart);
}
