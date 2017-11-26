package com.adidas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/search")
	public String search() {
		return "find";
	}

	@RequestMapping("/wishlist")
	public String wishlist() {
		return "mywishlist";
	}	
	
}