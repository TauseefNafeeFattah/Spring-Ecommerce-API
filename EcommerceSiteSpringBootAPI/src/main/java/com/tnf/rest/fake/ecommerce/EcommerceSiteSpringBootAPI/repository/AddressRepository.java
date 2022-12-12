package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
