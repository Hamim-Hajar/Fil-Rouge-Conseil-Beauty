package com.example.conseil.controllers;

import com.example.conseil.dto.ArticleDto;
import com.example.conseil.entities.Article;
import com.example.conseil.entities.User;
import com.example.conseil.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//    @PostMapping("/add")
//    public ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto articleDto) {
//        ArticleDto addedArticle = articleService.addArticle(articleDto);
//        return new ResponseEntity<>(addedArticle, HttpStatus.CREATED);
//    }
    @PostMapping("/add")
    // Or whatever role is appropriate
    public ResponseEntity<ArticleDto> addArticle(@RequestBody ArticleDto articleDto,@RequestParam Long specialist_id) {
        ArticleDto addedArticle = articleService.addArticle(articleDto,specialist_id);
        return new ResponseEntity<>(addedArticle, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<ArticleDto>> getAllArticles() {
        List<ArticleDto> articles = articleService.getAllArticles();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDto> getArticleById(@PathVariable Long id) {
        ArticleDto article = articleService.getArticleById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
