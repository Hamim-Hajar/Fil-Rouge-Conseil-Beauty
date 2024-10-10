package com.example.conseil.services;

import com.example.conseil.dto.CommentDto;
import com.example.conseil.dto.CommentDtoAr;
import com.example.conseil.entities.Article;
import com.example.conseil.entities.Comment;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Visiteur;
import com.example.conseil.enums.CommentType;
import com.example.conseil.mapper.CommentMapper;
import com.example.conseil.repository.ArticleRepository;
import com.example.conseil.repository.CommentRepository;
import com.example.conseil.repository.RecipeRepository;
import com.example.conseil.repository.VisiteurRepository;
import com.example.conseil.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VisiteurRepository visiteurRepository;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private RecipeRepository recipeRepository;


    public void addCommentToRecipe(Long visiteur_id, CommentDto commentDto) {
        Visiteur visiteur = visiteurRepository.findById(visiteur_id)
                .orElseThrow(() -> new ResourceNotFoundException("Visiteur not found"));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setTimestamp(new Date(System.currentTimeMillis()));
        comment.setVisiteur(visiteur);
        // Find the recipe
        Recipe recipe = recipeRepository.findById(commentDto.getRecipe_id())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));

        comment.setRecipe(recipe);
        commentRepository.save(comment);
    }

    public List<CommentDto> getCommentsByRecipe(Long recipeId) {
        Recipe recipe = findRecipeById(recipeId);
        return commentRepository.findByRecipe(recipe).stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public CommentDto updateComment(Long commentId, String newContent) {
        Comment comment = findCommentById(commentId);
        comment.setContent(newContent);
        comment.setTimestamp(new Date(System.currentTimeMillis()));
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }

    private Recipe findRecipeById(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found with id: " + recipeId));
    }
    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
    }
}