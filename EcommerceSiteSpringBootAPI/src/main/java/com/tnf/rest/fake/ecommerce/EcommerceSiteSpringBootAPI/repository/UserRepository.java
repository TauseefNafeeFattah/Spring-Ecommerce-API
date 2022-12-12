package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

public interface UserRepository extends JpaRepository<User,Integer> {

}
