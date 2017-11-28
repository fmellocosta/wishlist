package com.adidas.service;

import com.adidas.helper.SearchEnum;
import com.adidas.model.Item;

public interface WishlistService {
	String searchItem(SearchEnum searchEnum, String value);
	Item searchItem(SearchEnum searchEnum, int value);
}