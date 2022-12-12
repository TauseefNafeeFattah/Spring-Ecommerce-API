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

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.AddressType;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.AddressTypeDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos.PartialAddressTypeDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.AddressTypeRepository;

import jakarta.validation.Valid;


@RestController
public class AddressTypeJpaResource {
	
	private AddressTypeRepository addressTypeRepository;

	public AddressTypeJpaResource(AddressTypeRepository addressTypeRepository) {
		super();
		this.addressTypeRepository = addressTypeRepository;
	}
	//helper
	private AddressType getAddressType(Integer id) {

		Optional<AddressType> addressType = addressTypeRepository.findById(id);
		
		if(addressType.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "address type with the id is not found"
			);
		
		return addressType.get();
	}
	
	//mapping
	@GetMapping("/addressTypes")
	public List<AddressTypeDto> retrieveAllAddressType(){
		return addressTypeRepository.findAll().stream().map(addressType -> addressType.toAddressTypeDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/addressTypes/{id}")
	public AddressTypeDto retrieveAddressType(@PathVariable int id){
		AddressType addressType = getAddressType(id);
		
		return addressType.toAddressTypeDto();
	}
	
	@DeleteMapping("/addressTypes/{id}")
	public void deleteAddressType(@PathVariable int id) {
		addressTypeRepository.deleteById(id);
	}
	
	@PostMapping("/addressTypes")
	public ResponseEntity<AddressTypeDto> createAddressType(@Valid @RequestBody AddressTypeDto addressTypeDto){
		
		AddressType addressType = addressTypeDto.toAddressType();
		
		AddressType savedAddressType = addressTypeRepository.save(addressType);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedAddressType.getAddressTypeId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@PatchMapping("/addressTypes/{id}")
	public ResponseEntity<AddressTypeDto> updateAddressType( @PathVariable int id, @Valid @RequestBody PartialAddressTypeDto partialAddressTypeDto){
		AddressType addressType = getAddressType(id);

		AddressType updatedaddressType = partialAddressTypeDto.toPartialAddressType(addressType);
		
		addressTypeRepository.save(updatedaddressType);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updatedaddressType.getAddressTypeId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
}
