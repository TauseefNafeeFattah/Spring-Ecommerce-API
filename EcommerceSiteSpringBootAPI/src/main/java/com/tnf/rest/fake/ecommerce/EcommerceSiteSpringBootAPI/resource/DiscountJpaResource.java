package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.DiscountDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.ProductDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos.PartialDiscountDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Discount;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.DiscountRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.ProductRepository;
import jakarta.validation.Valid;

@RestController
public class DiscountJpaResource {
	
	private ProductRepository productRepository;
	
	private DiscountRepository discountRepository;

	public DiscountJpaResource(ProductRepository productRepository,DiscountRepository discountRepository) {
		super();
		this.productRepository = productRepository;
		this.discountRepository = discountRepository;
	}

	// getter setter
	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public DiscountRepository getDiscountRepository() {
		return discountRepository;
	}

	public void setDiscountRepository(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	// helper
	private Discount getDiscount(Integer id) {
		if(id == null)
			return null;
		
		Optional<Discount> discount = discountRepository.findById(id);
		
		if (discount.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Discount not with the provided id is not found"
			);
		return discount.get();
	}

	private Discount getPartialDiscount(PartialDiscountDto partialDiscountDto, Discount discount) {
		Discount newDiscount = partialDiscountDto.toPartialDiscount(discount);
		
		return newDiscount;
	}
	
	// mappings
	@GetMapping("/discounts")
	public List<DiscountDto> retrieveAllDiscounts(){
		return discountRepository.findAll().stream().map(discount -> discount.toDiscountDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/discounts/active/{status}")
	public List<DiscountDto> retrieveDiscountsStatus(@PathVariable boolean status){
		return discountRepository.findAllByActive(status).stream().map(discount -> discount.toDiscountDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/discounts/percentage/{amount}")
	public List<DiscountDto> retrieveDiscountsPercentage(@PathVariable double amount){
		double roundedAmount = (double) Math.round(amount * 100) / 100;
		
		return discountRepository.findAllByPercentage(roundedAmount).stream().map(discount -> discount.toDiscountDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/discounts/{id}")
	public DiscountDto retrieveDiscounts(@PathVariable int id){
		Discount discount = getDiscount(id);

		return discount.toDiscountDto();
	}
	
	@GetMapping("/discounts/{id}/products")
	public List<ProductDto> retrieveDiscountProducts(@PathVariable int id){
		List<Product> products = productRepository.findAllByDiscount(getDiscount(id));
		
		return products.stream().map(product -> product.toProductDto()).collect(Collectors.toList());
	}
	
	@DeleteMapping("/discounts/{id}")
	public void deleteDiscount(@PathVariable int id) {
		Discount discount = getDiscount(id);
		
		List<Product> products = productRepository.findAllByDiscount(discount);
		
		for(int i = 0; i <products.size(); i++) {
			products.get(i).setDiscountId(null);
		}
		discountRepository.deleteById(id);
	}
	
	@DeleteMapping("/discounts/{id}/products")
	public void deleteDiscountFromProducts(@PathVariable int id) {
		Discount discount = getDiscount(id);
		List<Product> products = productRepository.findAllByDiscount(discount);
		
		for (int i = 0; i < products.size(); i++) {
			products.get(i).setDiscountId(null);
		}
	}
	
	@PostMapping("/discounts")
	public ResponseEntity<DiscountDto> createDiscount( @Valid @RequestBody DiscountDto discountDto){
		
		Discount discount = discountDto.toDiscount();
		
		double roundedPercentage = (double) Math.round(discount.getPercentage() * 100) / 100;
		
		discount.setPercentage(roundedPercentage);
		
		Discount savedDiscount = discountRepository.save(discount);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedDiscount.getId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@PatchMapping("/discounts/{id}")
	public ResponseEntity<DiscountDto> updateDiscount( @PathVariable int id, @Valid @RequestBody PartialDiscountDto partialDiscountDto){
		Discount discount = getDiscount(id);

		Discount updatedDiscount = getPartialDiscount(partialDiscountDto, discount);
	
		discountRepository.save(updatedDiscount);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updatedDiscount.getId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
}
