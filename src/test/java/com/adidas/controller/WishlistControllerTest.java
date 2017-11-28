package com.adidas.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.adidas.service.WishlistService;

@RunWith(SpringRunner.class)
@WebMvcTest(WishlistController.class)
public class WishlistControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mvc;	
	
    @MockBean
    private WishlistService service;	
	
    @Test
    public void test() throws Exception {
    	//when(service.searchInAPIByUserId(Mockito.anyString())).thenReturn("{id: 1}");
    	this.mvc.perform(post("/api/wishlist/search").param("keyword", "1").accept(MediaType.TEXT_PLAIN))
    			.andExpect(status().isOk()).andExpect(content().string("Honda Civic"));
    }	
	
}
