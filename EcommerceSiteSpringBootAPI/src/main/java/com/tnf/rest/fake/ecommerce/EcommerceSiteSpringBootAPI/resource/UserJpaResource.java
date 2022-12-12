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

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.Address;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.address.AddressType;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.AddressDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.UserDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos.PartialAddressDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos.PartialUserDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.AddressRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.AddressTypeRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.RoleRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.UserRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.EmailAddress;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.RoleType;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
	
	private UserRepository userRepository;
	
	private RoleRepository roleRepository;
	
	private AddressRepository addressRepository;
	
	private AddressTypeRepository addressTypeRepository;
	
	public UserJpaResource(UserRepository userRepository, RoleRepository roleRepository,
			AddressRepository addressRepository, AddressTypeRepository addressTypeRepository) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.addressRepository = addressRepository;
		this.addressTypeRepository = addressTypeRepository; 
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	//helper
	private User getUser(Integer id) {
		if (id == null)
			return null;
		
		Optional<User> user = userRepository.findById(id);
		
		if(user.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "User with the provided id is not found"
			);
		return user.get();
	}
	
	private RoleType getRoleType(Integer id) {
		if (id == null)
			return null;
		Optional<RoleType> role = roleRepository.findById(id);
		
		if(role.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "role with the provided id is not found"
			);
		
		return role.get();
	}
	
	private Address getAddress(Integer id) {
		if (id == null)
			return null;
		Optional<Address> address = addressRepository.findById(id);

		if(address.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "address with the provided id is not found"
			);
		
		return address.get();	
	}
	
	private AddressType getAddressType(Integer id) {
		if (id == null)
			return null;
		Optional<AddressType> addressType = addressTypeRepository.findById(id);
		
		if(addressType.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "address type with the provided id is not found"
			);
		
		return addressType.get();
	}
	
	// mapping
	@GetMapping("/users")
	public List<UserDto> retreiveAllUsers(){
		return userRepository.findAll().stream().map(user -> user.toUserDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/users/{id}")
	public UserDto retrieveUser(@PathVariable int id){
		User user = getUser(id);
		
		return user.toUserDto();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<UserDto> createUser( @Valid @RequestBody UserDto userDto){
		Integer roleId = userDto.getRoleTypeId();
		
		RoleType role = getRoleType(roleId);

		User user = userDto.toUser();
		
		user.setRoleType(role);
		
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getUserId())
				.toUri();  
		
		return ResponseEntity.created(location).build();
	}
	
	@PatchMapping("/users/{id}")
	public ResponseEntity<UserDto> updateUser( @PathVariable int id, @Valid @RequestBody PartialUserDto partialUserDto){
		
		Integer roleId = partialUserDto.getRoleTypeId();
		
		RoleType role = getRoleType(roleId);

		User user = getUser(id);
		
		User updatedUser = partialUserDto.toPartialUser(user);
		
		if(role != null)
			updatedUser.setRoleType(role);

		if(partialUserDto.getEmail() != null) {
			EmailAddress oldEmail = user.getEmail();
			
			EmailAddress updatedEmail = partialUserDto.getEmail().toEmailAddress(oldEmail);
			
			if (updatedEmail != null)
				updatedUser.setEmail(updatedEmail);
		}

		userRepository.save(updatedUser);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updatedUser.getUserId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}	
	
	// addresses
	@GetMapping("/users/{id}/addresses") 
	public List<AddressDto> retrieveAddressesForUser(@PathVariable int id){
		User user = getUser(id);
		
		return user.getAddresses().stream().map(address -> address.toAddressDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/users/{userId}/addresses/{addressId}") 
	public AddressDto retrieveAddress(@PathVariable int userId, @PathVariable int addressId){
		User user =getUser(userId);
		
		Address address = getAddress(addressId);
		
		if(!user.getAddresses().contains(address))
			throw new ResponseStatusException(
					  HttpStatus.FORBIDDEN, "This address does not belong to the user with the provided id"
			);
		
		return address.toAddressDto();
	}
	
	@PostMapping("/users/{id}/addresses") 
	public ResponseEntity<AddressDto> createAddresses(@PathVariable int id,@Valid @RequestBody AddressDto addressDto){
		User user = getUser(id);

		Integer addressTypeId = addressDto.getAddressTypeId();
		
		AddressType addressType = getAddressType(addressTypeId);
		
		Address address = addressDto.toAddress();
		
		address.setUser(user);
		
		address.setAddressType(addressType);
		
		Address savedAddress = addressRepository.save(address);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedAddress.getId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userId}/addresses/{addressId}") 
	public void deleteAddresses(@PathVariable int addressId, @PathVariable int userId){
		User user = getUser(userId);
		
		Address address = getAddress(addressId);
		
		if(!user.getAddresses().contains(address))
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "The address with the addressId is not found in the user's (with the provided userId) address list"
			);
		AddressType addressType = address.getAddressType();
		
		addressType.getAddresses().remove(address);
				
		user.getAddresses().remove(address);
		
		addressRepository.delete(address);
		
	}
	
	@PatchMapping("/users/{userId}/addresses/{addressId}")
	public ResponseEntity<AddressDto> updateAddress( @PathVariable int addressId, @PathVariable int userId, @Valid @RequestBody PartialAddressDto partialAddressDto){
		User user = getUser(userId);
		
		Address address = getAddress(addressId);
		
		if( address!= null && !user.getAddresses().contains(address))
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "The address with the addressId is not found in the user's (with the provided userId) address list"
			);

		AddressType addressType = getAddressType(partialAddressDto.getAddressTypeId());
		
		Address updateAddress = partialAddressDto.toPartialAddress(address);
		
		if (user!=null)
			updateAddress.setUser(user);
		
		if (addressType != null)
			updateAddress.setAddressType(addressType);
		
		addressRepository.save(updateAddress);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updateAddress.getId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
}
