package com.example.conseil.controllers;

import com.example.conseil.dto.CommentDto;
import com.example.conseil.entities.Comment;
import com.example.conseil.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/add")
    public ResponseEntity<CommentDto> addComment(
            @RequestParam Long recipeId,
            @RequestParam Long userId,
            @RequestParam String content) {
        CommentDto commentDto = commentService.addComment(recipeId, userId, content);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
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

