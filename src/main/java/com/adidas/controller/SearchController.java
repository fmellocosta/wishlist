package com.adidas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adidas.service.WishlistService;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private WishlistService wishlistService;
	
	@PostMapping("/search")
    public String searchByUserId(@RequestParam("keyword") String searchValue) {
		return wishlistService.searchInAPIByUserId(searchValue);
    }	
	
}
