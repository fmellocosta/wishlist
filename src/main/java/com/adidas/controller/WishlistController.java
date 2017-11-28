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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.helper.SearchEnum;
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

	@PostMapping("/wishlist/search")
    public String searchByUserId(@RequestParam("keyword") String keyword) {
		return wishlistService.searchItem(SearchEnum.USERID, keyword);
    }	
	
	@GetMapping("/wishlist/populate/{id}")
    public ResponseEntity<Object> searchByPostId(@PathVariable(value = "id") int itemId) {
		final Item item = wishlistService.searchItem(SearchEnum.ITEMID, itemId);
		wishlistRepository.save(item);
		return ResponseEntity.ok().build();
    }		
	
	@DeleteMapping("/wishlist/{id}")
	public ResponseEntity<Item> deleteFromWishlist(@PathVariable(value = "id") Long itemId) {
		Item item = wishlistRepository.findOne(itemId);
	    if (item == null) {
	        return ResponseEntity.notFound().build();
	    }
	    wishlistRepository.delete(item);
	    return ResponseEntity.ok().build();
	}	

	@PostMapping("/wishlist")
	public List<Item> insertInWishlist(@Valid @RequestBody Item item) {
		wishlistRepository.save(item);
		return wishlistRepository.findAll();
	}
	
	@GetMapping("/wishlist")
	public List<Item> retrieveWishlist() {
		return wishlistRepository.findAll();
	}
	
	@GetMapping("/wishlist/{id}")
	public Item retrieveItemFromWishlist(@PathVariable(value = "id") Long itemId) {
		return wishlistRepository.findOne(itemId);
	}	
	
}
