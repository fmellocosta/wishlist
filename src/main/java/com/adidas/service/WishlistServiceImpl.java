package com.adidas.service;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adidas.helper.SearchEnum;
import com.adidas.model.Item;
import com.adidas.repository.WishlistRepository;

@Service("wishlistService")
public class WishlistServiceImpl implements WishlistService {

	@Autowired
	WishlistRepository repository;

	@Autowired
	RestTemplateService rtService;	
	
	private static final String MOCK_REST_API = "https://jsonplaceholder.typicode.com/posts";
	
	@Override
	public String searchItem(SearchEnum searchEnum, String value) {
		StringBuilder uri = new StringBuilder(MOCK_REST_API);
		if (!StringUtils.isNullOrEmpty(value)) {
			uri.append(uriBuilder(searchEnum));
		}
		RestTemplate restTemplate = new RestTemplate(rtService.clientHttpRequestFactory());
		String response = null;
		try {
			response = restTemplate.getForObject(uri.toString(), String.class, value);
		} catch (Exception e) {
			System.out.println(e);
		}				
        return response;		
	}	

	@Override
	public Item searchItem(SearchEnum searchEnum, int value) {
		StringBuilder uri = new StringBuilder(MOCK_REST_API);
		if (value != 0) {
			uri.append(uriBuilder(searchEnum));		
		}
		RestTemplate restTemplate = new RestTemplate(rtService.clientHttpRequestFactory());
		Item response = null;
		try {
			response = restTemplate.getForObject(uri.toString(), Item.class, value);
		} catch (Exception e) {
			System.out.println(e);
		}				
        return response;		
	}	
	
	private String uriBuilder(SearchEnum searchEnum) {
		String suffix = "";
		if (SearchEnum.ITEMID.equals(searchEnum)) {
			suffix = "/{value}";
		} else if (SearchEnum.USERID.equals(searchEnum)) {
			suffix = "?userId={value}";
		}
		return suffix;
	}
	
}