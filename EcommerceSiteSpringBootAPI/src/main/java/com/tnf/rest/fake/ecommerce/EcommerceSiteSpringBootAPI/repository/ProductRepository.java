package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Discount;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;

public interface ProductRepository extends JpaRepository<Product,Integer> {

	List<Product> findAllByDiscount(Discount discount);
	

	List<Product> findAllByCategoriesIds(Integer categoryId);

	List<Product> findAllByTagIds(Integer tagId);
}
