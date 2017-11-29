package com.adidas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.model.Item;
import com.adidas.repository.WishlistRepository;
import com.adidas.service.WishlistService;

@RestController
@RequestMapping("/api")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;	
	
    @Autowired
    private WishlistRepository wishlistRepository;		

	@GetMapping("/wishlist")
	public List<Item> retrieveWishlist() {
		return wishlistRepository.findAll();
	}
    	
	@GetMapping("/wishlist/populate/{id}")
    public ResponseEntity<Object> insertInWishlist(@PathVariable(value = "id") String searchValue) {
		final Item item = wishlistService.searchInAPIByItemId(searchValue);
	    if (item == null) {
	        return ResponseEntity.notFound().build();
	    }		
		wishlistRepository.save(item);
		return ResponseEntity.ok().build();
    }		

	@PostMapping("/wishlist")
	public List<Item> insertInWishlist(@Valid @RequestBody Item item) {
		wishlistRepository.save(item);
		return wishlistRepository.findAll();
	}	
	
	@DeleteMapping("/wishlist/remove/{id}")
	public ResponseEntity<Item> deleteFromWishlist(@PathVariable(value = "id") Long itemId) {
		Item item = wishlistRepository.findOne(itemId);
	    if (item == null) {
	        return ResponseEntity.notFound().build();
	    }
	    wishlistRepository.delete(item);
	    return ResponseEntity.ok().build();
	}	
		
	@GetMapping("/wishlist/{id}")
	public Item retrieveItemFromWishlist(@PathVariable(value = "id") Long itemId) {
		return wishlistRepository.findOne(itemId);
	}	
	
}
