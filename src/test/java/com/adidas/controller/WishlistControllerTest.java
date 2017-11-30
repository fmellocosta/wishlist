package com.adidas.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.adidas.model.Item;
import com.adidas.repository.WishlistRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WishlistControllerTest {
 
    @Autowired
    private WishlistRepository wishlistRepository;
    
    @Before
    public void setUp() {
    	
    	Item mockedItem = new Item();
    	mockedItem.setId((long) 1);
    	mockedItem.setUrl("Text url");
    	
    	Item mockedItem2 = new Item();
    	mockedItem.setId((long) 2);
    	mockedItem2.setUrl("Text url 2");    	
    	
    	wishlistRepository.save(mockedItem);
    	wishlistRepository.save(mockedItem2);    	
    	
    }
    
    @Test
    public void whenFindItems_thenReturnAllItems() {
    	assertThat(wishlistRepository.findAll().size(), is(2));
    }
    
    @Test
    public void whenSearchOneItem_thenReturnOneItem() {
    	Item item = wishlistRepository.findOne((long) 3);
    	assertThat(item.getUrl(), is("Text url"));
    } 
    
    @Test
    public void whenDeleteItem_thenReturnLessOneItem() {
    	Item item = wishlistRepository.findOne((long) 2);
    	wishlistRepository.delete(item);
    	assertThat(wishlistRepository.findAll().size(), is(1));
    }   
    
}