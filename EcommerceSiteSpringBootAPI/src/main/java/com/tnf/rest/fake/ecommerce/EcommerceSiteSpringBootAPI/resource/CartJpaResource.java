package com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.resource;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.CartDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.dtos.CartItemDto;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders.Cart;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.orders.CartItem;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.products.Product;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.CartItemRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.CartRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.ProductRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.repository.UserRepository;
import com.tnf.rest.fake.ecommerce.EcommerceSiteSpringBootAPI.users.User;

import jakarta.validation.Valid;

@RestController
public class CartJpaResource {
	private CartRepository cartRepository;

	private CartItemRepository cartItemRepository;
	private UserRepository userRepository;
	
	private ProductRepository productRepository;
	
	
	public CartJpaResource(CartRepository cartRepository, CartItemRepository cartItemRepository,
			UserRepository userRepository, ProductRepository productRepository) {
		super();
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
	}
	// helpers
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
	
	private Cart getCartFromId(Integer id) {
		if (id == null)
			return null;
		Optional<Cart> cart = cartRepository.findById(id);
		
		if(cart.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Cart with the provided id is not found"
			);
		return cart.get();
	}
	
	private CartItem getCartItemFromId(Integer id) {
		if (id == null)
			return null;
		
		Optional<CartItem> cartItem = cartItemRepository.findById(id);
		
		if(cartItem.isEmpty()) {
			return null;
		}
		
		return cartItem.get();
	}
	
	private Product getProductFromId(Integer id) {
		if (id == null) {
			return null;
		}
		Optional<Product> product= productRepository.findById(id);
		
		if (product.isEmpty())
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Product with the id is not found"
			);
		return product.get();
	}
	
	private void deleteCartItem(Integer id) {
		cartItemRepository.deleteById(id);
	}
	
	public CartItem createCartItem(CartItemDto cartItemDto, Cart cart, Product product) {
		CartItem cartItem = cartItemDto.toCartItem();
		
		cartItem.setCart(cart);
		
		cartItem.setProduct(product);
		
		return cartItem;
		
	}

	public Double getPrice(Cart cart) {
		List<CartItem> cartItems = cartItemRepository.findAllByCart(cart);
		
		Double price = 0.0;
		
		for(int i = 0; i < cartItems.size(); i++) {
			price += cartItems.get(i).getProduct().getPrice() *cartItems.get(i).getQuantity();
		}
		
		return price;
	}
	
	//mappings
	@GetMapping("/users/{id}/carts")
	public List<CartDto> retrieveAllCartForUser(@PathVariable int id){
		User user = getUserFromId(id);
		
		return cartRepository.findAllByBuyer(user).stream().map(cart -> cart.toCartDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/users/{userId}/carts/{cartId}")
	public CartDto retrieveCart(@PathVariable int userId, @PathVariable int cartId){
		User user = getUserFromId(userId);
		
		Cart cart = getCartFromId(cartId);
		
		if (cart.getBuyer().getUserId() != user.getUserId()) {
			throw new ResponseStatusException(
					  HttpStatus.FORBIDDEN, "Cart does not belong to the user with the provided id"
			);			
		}
		
		CartDto cartDto	= cart.toCartDto();
		
		cartDto.setPrice(getPrice(cart));
		
		return cartDto;
	}
	
	@GetMapping("/users/{userId}/carts/{cartId}/items")
	public List<CartItemDto> retrieveAllItemsForCart(@PathVariable int userId, @PathVariable int cartId){
		User user = getUserFromId(userId);
		
		Cart cart = getCartFromId(cartId);
		
		if (cart.getBuyer().getUserId() != user.getUserId()) {
			throw new ResponseStatusException(
					  HttpStatus.FORBIDDEN, "Cart does not belong to the user with the provided id"
			);			
		}
		
		return cartItemRepository.findAllByCart(cart).stream().map(cartItem -> cartItem.toCartItemDto()).collect(Collectors.toList());
	}
	
	@GetMapping("/users/{userId}/carts/{cartId}/items/{itemId}")
	public List<CartItemDto> retrieveItemsForCart(@PathVariable int userId, @PathVariable int cartId,@PathVariable int itemId){
		User user = getUserFromId(userId);
		
		Cart cart = getCartFromId(cartId);
		
		if (cart.getBuyer().getUserId() != user.getUserId()) {
			throw new ResponseStatusException(
					  HttpStatus.FORBIDDEN, "Cart does not belong to the user with the provided id"
			);			
		}
		
		CartItem cartItem = getCartItemFromId(itemId);
		
		if (cartItem == null || cartItem.getCart() != cart) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "There is no item with this id in this cart"
			);
		}
		
		return cartItemRepository.findAllByCart(cart).stream().map(cartItems -> cartItems.toCartItemDto()).collect(Collectors.toList());
	}
	
	@PostMapping("/users/{id}/carts")
	public ResponseEntity<CartDto> createCart(@PathVariable int id, @Valid @RequestBody CartDto cartDto){
		User user = getUserFromId(id);
		
		Cart cart = cartDto.toCart();
		
		cart.setBuyer(user);
		
		Cart savedCart = cartRepository.save(cart);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedCart.getId())
				.toUri();   

		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/users/{userId}/carts/{cartId}/items")
	public ResponseEntity<CartDto> updateItemToCart(@PathVariable int userId, @PathVariable int cartId, @Valid @RequestBody CartItemDto cartItemDto){
		
		User user = getUserFromId(userId);
		
		Cart cart = getCartFromId(cartId);
		
		Integer quantity = cartItemDto.getQuantity();
		
		if (cart.getBuyer().getUserId() != user.getUserId()) {
			throw new ResponseStatusException(
					  HttpStatus.FORBIDDEN, "Cart does not belong to the user with the provided id"
			);			
		}

		if (quantity == 0) {
			deleteCartItem(cartItemDto.getCartItemId());
		}
		
		Product product = getProductFromId(cartItemDto.getProductId());
		
		Optional<CartItem> cartItem = cartItemRepository.findByProductAndCart(product, cart);
		CartItem newCartItem = null;
		
		URI location;
		
		if (cartItem.isEmpty() && quantity != 0) {
			newCartItem = createCartItem(cartItemDto, cart, product);
		}
		else {
			
			if(quantity == 0) {
				if (!cartItem.isEmpty())
					deleteCartItem(cartItem.get().getId());
			}
			else {
				newCartItem = cartItem.get();
				
				newCartItem.setQuantity(quantity);
			}
		}
		
		if (newCartItem != null) {
			CartItem savedCartItem = cartItemRepository.save(newCartItem);
			
			location = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedCartItem.getId())
					.toUri();   
				
		}
		else {
			// change this to carts
			location = ServletUriComponentsBuilder.fromCurrentRequest()
					.build()
					.toUri(); 
		}
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{userId}/carts/{cartId}")
	public void deleteCart(@PathVariable int userId, @PathVariable int cartId) {
		User user = getUserFromId(userId);
		
		Cart cart = getCartFromId(cartId);
		
		if (cart.getBuyer().getUserId() != user.getUserId()) {
			throw new ResponseStatusException(
					  HttpStatus.FORBIDDEN, "Cart does not belong to the user with the provided id"
			);			
		}
		
		cartRepository.deleteById(cartId);
	}
	
	@DeleteMapping("/users/{userId}/carts/{cartId}/items/{itemId}")
	public void deleteItem(@PathVariable int userId, @PathVariable int cartId, @PathVariable int itemId) {
		User user = getUserFromId(userId);
		
		Cart cart = getCartFromId(cartId);
		
		if (cart.getBuyer().getUserId() != user.getUserId()) {
			throw new ResponseStatusException(
					  HttpStatus.FORBIDDEN, "Cart does not belong to the user with the provided id"
			);			
		}
		
		CartItem cartItem = getCartItemFromId(itemId);
		
		if (cartItem == null || cartItem.getCart() != cart) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "There is no item with this id in this cart"
			);
		}
		
		cartItemRepository.deleteById(itemId);
	}	
}
