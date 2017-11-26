package com.adidas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.adidas.model.Wishlist;

@RepositoryRestResource
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
 
}