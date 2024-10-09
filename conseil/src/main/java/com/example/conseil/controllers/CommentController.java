package com.example.conseil.controllers;

import com.example.conseil.dto.CommentDto;
import com.example.conseil.dto.CommentDtoAr;
import com.example.conseil.entities.Article;
import com.example.conseil.entities.Comment;
import com.example.conseil.entities.Visiteur;
import com.example.conseil.enums.CommentType;
import com.example.conseil.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {


    @Autowired
    private CommentService commentService;

//    @PostMapping("/add")
//    public ResponseEntity<CommentDto> addComment(@RequestParam Long visiteur_id,
//                                                 @RequestParam Long articl_id,
//                                                 @RequestParam String content) {
//        CommentDto createdComment = commentService.addComment(visiteur_id, articl_id, content);
//        return ResponseEntity.ok(createdComment);
//    }
// Ajouter un commentaire à une recette
//@PostMapping("/recipes/{recipe_id}")
//public ResponseEntity<String> addCommentToRecipe(
//        @RequestParam Long visiteur_id,
//        @PathVariable Long recipe_id,
//        @RequestBody CommentDto commentDto) {
//    commentService.addComment(visiteur_id, recipe_id, commentDto, CommentType.RECIPE);
//    return ResponseEntity.ok("Comment added to recipe successfully");
//}
//
//    // Ajouter un commentaire à un article
//    @PostMapping("/articles/{articl_id}")
//    public ResponseEntity<String> addCommentToArticle(
//            @RequestParam Long visiteur_id,
//            @PathVariable Long articl_id,
//            @RequestBody CommentDto commentDto) {
//
//        // Appel au service pour ajouter un commentaire de type ARTICLE
//        commentService.addComment(visiteur_id, articl_id, commentDto, CommentType.ARTICLE);
//        return ResponseEntity.ok("Comment added to article successfully");
//    }

//    @PostMapping("/com/article")
//    public ResponseEntity<Void> addArticleComment(@RequestParam Long visiteur_id, @RequestBody CommentDtoAr commentDtoAr) {
//        commentService.addCommentToArticle(visiteur_id, commentDtoAr);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    @PostMapping("/com/recipe")
    public ResponseEntity<Void> addRecipeComment(@RequestParam Long visiteur_id, @RequestBody CommentDto commentDto) {
        commentService.addCommentToRecipe(visiteur_id, commentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/recipe/{recipeId}")
    public ResponseEntity<List<CommentDto>> getCommentsByRecipe(@PathVariable Long recipeId) {
        List<CommentDto> comments = commentService.getCommentsByRecipe(recipeId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

