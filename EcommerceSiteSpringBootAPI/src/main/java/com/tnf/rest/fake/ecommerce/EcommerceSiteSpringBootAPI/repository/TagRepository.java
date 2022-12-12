package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Tag;

public interface TagRepository extends JpaRepository<Tag,Integer> {

}
