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

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.CategoryDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.ProductDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos.PartialCategoryDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Category;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.CategoryRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.DiscountRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.ProductRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.TagRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.UserRepository;
import jakarta.validation.Valid;

@RestController
public class CategoryJpaResource {
	
	private ProductRepository productRepository;
	
	private CategoryRepository categoryRepository;

	public CategoryJpaResource(UserRepository userRepository, ProductRepository productRepository,
			CategoryRepository categoryRepository, DiscountRepository discountRepository, TagRepository tagRepository) {
		super();
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}

	// getter setter

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	// helper

	private Category getPartialCategory(PartialCategoryDto paritalCategoryDto, Category category) {
		Category newCategory = paritalCategoryDto.toPartialCategory(category);
		
		return newCategory;
	}
	
	private Category getCategoryFromId(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		
		if (category.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Category is not found with the provided id"
			);
		
		return category.get();
	}
	
	// mappings
	
	@GetMapping("/categories")
	public List<CategoryDto> retrieveAllCategories(){
		return categoryRepository.findAll().stream().map(category -> category.toCategoryDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/categories/{id}")
	public CategoryDto retrieveCategory(@PathVariable int id){
		Category category = getCategoryFromId(id);
		
		return category.toCategoryDto();
	}
	
	@GetMapping("/categories/{id}/products")
	public List<ProductDto> retrieveCategoryProducts(@PathVariable int id){
		Category category = getCategoryFromId(id);
		
		return productRepository.findAllByCategoriesIds(category.getId()).stream().map(product -> product.toProductDto()).collect(Collectors.toList());
	}
	
	@DeleteMapping("/categories/{id}")
	public void deleteCategories(@PathVariable int id) {
		categoryRepository.deleteById(id);
	}
	
	@DeleteMapping("/categories/{id}/products")
	public void deleteCategoryFromProducts(@PathVariable int id) {
		
		Category category = getCategoryFromId(id);
		
		List<Product> products = productRepository.findAllByCategoriesIds(category.getId());
		
		for (int i = 0; i < products.size(); i++) {
			products.get(i).getCategoriesIds().remove(category.getId());
		}
	}
	
	@PostMapping("/categories")
	public ResponseEntity<CategoryDto> createCategory( @Valid @RequestBody CategoryDto categoryDto){
		
		Category category = categoryDto.toCategory();
		
		Category savedCategory = categoryRepository.save(category);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCategory.getId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@PatchMapping("/categories/{id}")
	public ResponseEntity<CategoryDto> updateCategory( @PathVariable int id, @Valid @RequestBody PartialCategoryDto partialCategoryDto){
		Category category = getCategoryFromId(id);
		
		Category updatedCategory = getPartialCategory(partialCategoryDto, category);
		
		categoryRepository.save(updatedCategory);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updatedCategory.getId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
}
