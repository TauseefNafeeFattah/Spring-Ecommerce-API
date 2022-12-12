package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {

}
