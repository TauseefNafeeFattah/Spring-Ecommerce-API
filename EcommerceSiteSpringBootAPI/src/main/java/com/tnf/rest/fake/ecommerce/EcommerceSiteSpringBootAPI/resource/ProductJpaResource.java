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

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.ProductDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos.PartialProductDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Category;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Discount;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Tag;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.CategoryRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.DiscountRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.ProductRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.TagRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.UserRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

import jakarta.validation.Valid;

@RestController
public class ProductJpaResource {
	
	private UserRepository userRepository;
	
	private ProductRepository productRepository;
	
	private CategoryRepository categoryRepository;
	
	private DiscountRepository discountRepository;
	
	
	private TagRepository tagRepository;

	public ProductJpaResource(UserRepository userRepository, ProductRepository productRepository,
			CategoryRepository categoryRepository, DiscountRepository discountRepository, TagRepository tagRepository) {
		super();
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
		this.discountRepository = discountRepository;
		this.tagRepository = tagRepository;
	}

	// getter setter
	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public CategoryRepository getCategoryRepository() {
		return categoryRepository;
	}

	public void setCategoryRepository(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public DiscountRepository getDiscountRepository() {
		return discountRepository;
	}

	public void setDiscountRepository(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	// helper
	
	private User getUserProduct(Integer id) {
		if (id == null)
			return null;
		
		User user = getUserFromId(id);
		
		if(user.getRoleType().getCanSell() == false)
			throw new ResponseStatusException(
					  HttpStatus.FORBIDDEN, "This user can not sell products"
			);
		return user;
	}

	private User getUserFromId(Integer id) {
		if (id == null)
			return null;
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "User with the provided id is not found"
			);
		return user.get();
	}
	
	private void validateTags(List<Integer> tagIds) {
		for(int i = 0; i < tagIds.size(); i++) {
			getTagFromId(tagIds.get(i));
		}
	}
	
	private void validateCategories(List<Integer> categoryIds) {
		for(int i = 0; i < categoryIds.size(); i++) {
			getCategoryFromId(categoryIds.get(i));
		}
	}
	
	private Category getCategoryFromId(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		
		if (category.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Category with the provided id is not found"
			);
		
		return category.get();
	}
	
	private Tag getTagFromId(Integer id) {
		Optional<Tag> tag = tagRepository.findById(id);
		
		if (tag.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Tag with the provided id is not found"
			);
		
		return tag.get();
	}
	
	private Product getProductFromId(Integer id) {
		Optional<Product> product= productRepository.findById(id);
		
		if (product.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Product with the provided id is not found"
			);
		return product.get();
	}
	
	private Discount getDiscount(Integer id) {
		if(id == null)
			return null;
		
		Optional<Discount> discount = discountRepository.findById(id);
		
		if (discount.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Discount with the provided is not found"
			);
		return discount.get();
	}

	private Product getPartialProduct(PartialProductDto partialProductDto, Product product) {
		Product newProduct = partialProductDto.toPartialProduct(product);
		
		if (partialProductDto.getCategories() != null && partialProductDto.getCategories().isEmpty() == false) {
			validateCategories(partialProductDto.getCategories());

			newProduct.setCategoriesIds(partialProductDto.getCategories());
		}
		
		if (partialProductDto.getTags() != null && partialProductDto.getTags().isEmpty() == false) {
			
			validateTags(partialProductDto.getTags());

			newProduct.setTagIds(partialProductDto.getTags());
		}
		
		if (partialProductDto.getDiscountId() != null) {
			Discount discount = getDiscount(partialProductDto.getDiscountId());
			
			if(discount != null)
				newProduct.setDiscountId(discount.getId());			
		}
		
		if(partialProductDto.getSellerId() != null) {
			User seller = getUserProduct(partialProductDto.getSellerId());
			
			if(seller != null)
				newProduct.setSellerId(seller.getUserId());
		}
		
		return newProduct;
	}
	
	public void deleteProductById(int id) {
		productRepository.deleteById(id);
	}
	
	// mappings	
	@GetMapping("/products")
	public List<ProductDto> retrieveAllProducts(){
		return productRepository.findAll().stream().map(product -> product.toProductDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/products/{id}")
	public ProductDto retrieveProduct(@PathVariable int id){
		Product product = getProductFromId(id);
		
		return product.toProductDto();
	}
	
	@DeleteMapping("/products/{id}")
	public void deleteProducts(@PathVariable int id) {

		deleteProductById(id);
	}

	@PostMapping("/products")
	public ResponseEntity<ProductDto> createProduct( @Valid @RequestBody ProductDto productDto){
		
		Product product = productDto.toProduct();
		
		User seller = getUserProduct(productDto.getSellerId());
		
		product.setSellerId(seller.getUserId());
		
		validateCategories(productDto.getCategories());
		
		product.setCategoriesIds(productDto.getCategories());
		
		validateTags(productDto.getTags());
		
		product.setTagIds(productDto.getTags());
		
		Discount discount = getDiscount(productDto.getDiscountId());
		
		product.setDiscountId(discount.getId());
		
		Product savedProduct = productRepository.save(product);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedProduct.getProductId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@PatchMapping("/products/{id}")
	public ResponseEntity<ProductDto> updateProduct( @PathVariable int id, @Valid @RequestBody PartialProductDto partialProductDto){
		
		Product product = getProductFromId(id);

		Product updatedProduct = getPartialProduct(partialProductDto, product);
		
		productRepository.save(updatedProduct);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updatedProduct.getProductId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
}
