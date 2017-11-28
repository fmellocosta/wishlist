package com.adidas.service;

import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.adidas.helper.SearchEnum;
import com.adidas.model.Item;

@Service("wishlistService")
public class WishlistService {

	@Autowired
	RestTemplateService rtService;	
	
	private static final String MOCK_REST_API = "https://jsonplaceholder.typicode.com/posts";
	
	public String searchInAPIByUserId(String value) {
		String response = null;
		try {
			StringBuilder uri = new StringBuilder(MOCK_REST_API);
			if (!StringUtils.isNullOrEmpty(value)) {
				uri.append(uriBuilder(SearchEnum.USERID));
			}
			RestTemplate restTemplate = new RestTemplate(rtService.clientHttpRequestFactory());
			response = restTemplate.getForObject(uri.toString(), String.class, value);
		} catch (Exception e) {
			System.out.println(e);
		}
        return response;
	}	

	public Item searchInAPIByItemId(String value) {
		Item response = null;
		try {
			StringBuilder uri = new StringBuilder(MOCK_REST_API);
			if (!StringUtils.isNullOrEmpty(value)) {
				uri.append(uriBuilder(SearchEnum.ITEMID));		
			}
			RestTemplate restTemplate = new RestTemplate(rtService.clientHttpRequestFactory());
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