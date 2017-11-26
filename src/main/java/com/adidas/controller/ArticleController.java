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
import org.springframework.web.bind.annotation.RestController;

import com.adidas.model.Article;
import com.adidas.repository.ArticleRepository;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;	
	
	@GetMapping("/articles")
	public List<Article> getAllArticles() {
	    return articleRepository.findAll();
	}
	
	@PostMapping("/articles")
	public Article createArticle(@Valid @RequestBody Article article) {
	    return articleRepository.save(article);
	}
	
	@GetMapping("/articles/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Long articleId) {
		Article article = articleRepository.findOne(articleId);
	    if(article == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(article);
	}
	
	@DeleteMapping("/articles/{id}")
	public ResponseEntity<Article> deleteArticle(@PathVariable(value = "id") Long articleId) {
		Article article = articleRepository.findOne(articleId);
	    if (article == null) {
	        return ResponseEntity.notFound().build();
	    }
	    articleRepository.delete(article);
	    return ResponseEntity.ok().build();
	}	
	
}