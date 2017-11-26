package com.adidas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.model.Wishlist;
import com.adidas.repository.WishlistRepository;

@RestController
@RequestMapping("/api")
public class WishlistController {

    @Autowired
    WishlistRepository wishlistRepository;		
	
	@GetMapping("/wishlists")
	public List<Wishlist> getAllWishlsits() {
	    return wishlistRepository.findAll();
	}    
    
}
