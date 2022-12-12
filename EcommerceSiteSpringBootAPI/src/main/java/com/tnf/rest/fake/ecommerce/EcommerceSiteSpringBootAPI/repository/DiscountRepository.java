package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Discount;

public interface DiscountRepository extends JpaRepository<Discount,Integer> {

	List<Discount> findAllByActive(Boolean activeStatus);
	List<Discount> findAllByPercentage(Double amount);
	
}
