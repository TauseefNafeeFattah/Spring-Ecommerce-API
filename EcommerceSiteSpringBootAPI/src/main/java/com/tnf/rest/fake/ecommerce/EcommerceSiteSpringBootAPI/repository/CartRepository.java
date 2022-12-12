package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders.Cart;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

public interface CartRepository extends JpaRepository<Cart,Integer> {
	
	List<Cart> findAllByBuyer(User user);
	
}
