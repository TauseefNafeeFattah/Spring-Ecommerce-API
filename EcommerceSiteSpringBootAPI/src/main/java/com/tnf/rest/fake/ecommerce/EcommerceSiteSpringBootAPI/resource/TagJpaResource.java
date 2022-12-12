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
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.TagDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos.PartialTagDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Tag;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.ProductRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.TagRepository;

import jakarta.validation.Valid;

@RestController
public class TagJpaResource {
	
	private ProductRepository productRepository;
	
	private TagRepository tagRepository;

	public TagJpaResource(ProductRepository productRepository, TagRepository tagRepository) {
		super();
		this.productRepository = productRepository;
		this.tagRepository = tagRepository;
	}

	// getter setter
	public TagRepository getTagRepository() {
		return tagRepository;
	}

	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	
	// helper
	private Tag getTagFromId(Integer id) {
		Optional<Tag> tag = tagRepository.findById(id);
		
		if (tag.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Tag with the provided id is not found id"
			);
		
		return tag.get();
	}
	
	private Tag getPartialTag(PartialTagDto partialTagDto, Tag tag) {
		Tag newTag = partialTagDto.toPartialTag(tag);
		
		return newTag;
	}
	
	// mappings
	@GetMapping("/tags")
	public List<TagDto> retrieveAllTags(){
		return tagRepository.findAll().stream().map(tag -> tag.toTagDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/tags/{id}")
	public TagDto retrieveTag(@PathVariable int id){
		Tag tag = getTagFromId(id);
		
		return tag.toTagDto();
	}
	
	@GetMapping("/tags/{id}/products")
	public List<ProductDto> retrieveTagProducts(@PathVariable int id){
		Tag tag = getTagFromId(id);
		
		return productRepository.findAllByTagIds(tag.getId()).stream().map(product -> product.toProductDto()).collect(Collectors.toList());
	}
	
	@DeleteMapping("/tags/{id}")
	public void deleteTags(@PathVariable int id) {
		
		tagRepository.deleteById(id);
	}
	
	@DeleteMapping("/tags/{id}/products")
	public void deleteTagsFromProducts(@PathVariable int id) {
		
		Tag tag = getTagFromId(id);
		
		List<Product> products = productRepository.findAllByTagIds(tag.getId());
		
		for (int i = 0; i < products.size(); i++) {
			products.get(i).getTagIds().remove(tag.getId());
		}
	}
	
	@PostMapping("/tags")
	public ResponseEntity<TagDto> createTag( @Valid @RequestBody TagDto tagDto){
		
		Tag tag = tagDto.toTag();
		
		Tag savedTag = tagRepository.save(tag);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedTag.getId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@PatchMapping("/tags/{id}")
	public ResponseEntity<TagDto> updateTag( @PathVariable int id, @Valid @RequestBody PartialTagDto partialTagDto){
		Tag tag = getTagFromId(id);

		Tag updatedTag = getPartialTag(partialTagDto, tag);
		
		tagRepository.save(updatedTag);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updatedTag.getId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
}
