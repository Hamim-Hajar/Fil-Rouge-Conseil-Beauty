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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<ArticleDto> addArticle(@RequestParam String titre,
                                                 @RequestParam String contenu,
                                                 @RequestParam Long specialist_id,
                                                 @RequestPart("image") MultipartFile image
                                                 ) throws IOException {

        byte[] imageBytes =  image.getBytes();

        ArticleDto articleDto = ArticleDto.builder()
                .titre(titre)
                .contenu(contenu)
                .build();

        ArticleDto addedArticle = articleService.addArticle(articleDto,specialist_id ,imageBytes );
        return new ResponseEntity<>(addedArticle, HttpStatus.CREATED);
    }


    @GetMapping("/get_all_article")
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
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArticle(
            @PathVariable Long id,
            @RequestParam String titre,
            @RequestParam String contenu,
            @RequestParam Long specialist_id,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        // Input validation
        if (titre == null || titre.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Title cannot be null or empty.");
        }
        if (contenu == null || contenu.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Content cannot be null or empty.");
        }
        if (specialist_id == null) {
            return ResponseEntity.badRequest().body("Specialist ID cannot be null.");
        }

        byte[] imageBytes = null;
        try {
            // Convert image to byte array if provided
            if (image != null && !image.isEmpty()) {
                imageBytes = image.getBytes();
            }

            // Create the ArticleDto for the update
            ArticleDto articleDto = ArticleDto.builder()
                    .titre(titre)
                    .contenu(contenu)
                    .build();

            // Call the service to update the article
            ArticleDto updatedArticle = articleService.updateArticle(id, articleDto, imageBytes);
            return ResponseEntity.ok(updatedArticle);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the image.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred.");
        }
    }
}
