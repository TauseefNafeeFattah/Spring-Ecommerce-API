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

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.RoleTypeDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.UserDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.partialDtos.PartialRoleTypeDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.RoleRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.RoleType;
import jakarta.validation.Valid;

@RestController
public class RoleJpaResource {
	
	private RoleRepository roleRepository;
	
	public RoleJpaResource(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	public RoleRepository getRoleRepository() {
		return roleRepository;
	}

	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	//helper
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
	
	// mapping
	@GetMapping("/roles")
	public List<RoleTypeDto> retreiveAllRoles(){
		return roleRepository.findAll().stream().map(role -> role.toRoleTypeDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/roles/{id}")
	public RoleTypeDto retrieveRole(@PathVariable int id){
		RoleType role = getRoleType(id);
		
		return role.toRoleTypeDto();
	}
	
	@GetMapping("/roles/{id}/users")
	public List<UserDto> retreiveAllUsersForRole(@PathVariable int id){
		RoleType role = getRoleType(id);
		
		return role.getUsers().stream().map(user -> user.toUserDto()).collect(Collectors.toList());
	}
	
	@DeleteMapping("/roles/{id}")
	public void deleteRole(@PathVariable int id) {
		roleRepository.deleteById(id);
	}
	
	@PostMapping("/roles")
	public ResponseEntity<RoleTypeDto> createRole(@Valid @RequestBody RoleTypeDto roleTypeDto){
		
		RoleType roleType = roleTypeDto.toRoleType();
		
		RoleType savedRoleType = roleRepository.save(roleType);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedRoleType.getTypeId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
	
	@PatchMapping("/roles/{id}")
	public ResponseEntity<RoleTypeDto> updateRoleType( @PathVariable int id, @Valid @RequestBody PartialRoleTypeDto partialRoleTypeDto){
		RoleType roleType = getRoleType(id);

		RoleType updateRoleType = partialRoleTypeDto.toPartialRoleType(roleType);
		
		// might break;
		roleRepository.save(updateRoleType);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(updateRoleType.getTypeId())
				.toUri();   
		
		return ResponseEntity.created(location).build();
	}
}
