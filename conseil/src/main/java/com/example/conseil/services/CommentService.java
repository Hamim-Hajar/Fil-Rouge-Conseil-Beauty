package com.example.conseil.services;

import com.example.conseil.dto.CommentDto;
import com.example.conseil.entities.Comment;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Visiteur;
import com.example.conseil.mapper.CommentMapper;
import com.example.conseil.repository.CommentRepository;
import com.example.conseil.repository.RecipeRepository;
import com.example.conseil.repository.VisiteurRepository;
import com.example.conseil.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private VisiteurRepository visiteurRepository;

    @Autowired
    private CommentMapper commentMapper;


    public CommentDto addComment(Long recipeId, Long userId, String content) {
        Recipe recipe = findRecipeById(recipeId);
        Visiteur visiteur = findVisiteurById(userId);

        Comment comment = Comment.builder()
                .content(content)
                .timestamp(new Date(System.currentTimeMillis()))
                .visiteur(visiteur)
                .recipe(recipe)
                .build();

        return commentMapper.toDto(commentRepository.save(comment));
    }

    public List<CommentDto> getCommentsByRecipe(Long recipeId) {
        Recipe recipe = findRecipeById(recipeId);
        return commentRepository.findByRecipe(recipe).stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public CommentDto updateComment(Long commentId, String newContent) {
        Comment comment = findCommentById(commentId);
        comment.setContent(newContent);
        comment.setTimestamp(new Date(System.currentTimeMillis()));
        return commentMapper.toDto(commentRepository.save(comment));
    }

    private Recipe findRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));
    }

    private Visiteur findVisiteurById(Long userId) {
        return visiteurRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
    }
}