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

//    @Transactional
//    public CommentDto addComment(Long visiteur_id, Long articl_id, String content) {
//        Visiteur visiteur = visiteurRepository.findById(visiteur_id)
//                .orElseThrow(() -> new RuntimeException("Visiteur non trouvé"));
//        Article article = articleRepository.findById(articl_id)
//                .orElseThrow(() -> new RuntimeException("Article non trouvé"));
//
//
//        Comment comment = Comment.builder()
//                .content(content)
//                .timestamp(new Date(System.currentTimeMillis()))
//                .visiteur(visiteur)
//                .article(article)
//                .build();
//
//        // Enregistrer le commentaire dans la base de données
//        Comment savedComment = commentRepository.save(comment);
//
//        // Mapper le commentaire enregistré en DTO
//        return CommentMapper.MAPPER.commentToCommentDto(savedComment);
//    }
//public void addComment(Long visiteur_id, Long id, CommentDto commentDto, CommentType type) {
//
//    Visiteur visiteur = visiteurRepository.findById(visiteur_id)
//            .orElseThrow(() -> new ResourceNotFoundException("Visiteur not found"));
//    Comment comment = commentMapper.commentDtoToComment(commentDto);
//    comment.setVisiteur(visiteur);
//    if (type == CommentType.ARTICLE) {
//        Article article = articleRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Article not found"));
//        comment.setTimestamp(new Date(System.currentTimeMillis()));
//        comment.setArticle(article);
//    } else if (type == CommentType.RECIPE) {
//        Recipe recipe = recipeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));
//        comment.setRecipe(recipe);
//        comment.setTimestamp(new Date(System.currentTimeMillis()));
//    }
//    commentRepository.save(comment);
//}

//    public void addCommentToArticle(Long visiteur_id, CommentDtoAr commentDtoAr) {
//        // Find the visitor
//        Visiteur visiteur = visiteurRepository.findById(visiteur_id)
//                .orElseThrow(() -> new ResourceNotFoundException("Visiteur not found"));
//        // Map CommentDtoAr to Comment
//        Comment comment = new Comment();
//        comment.setContent(commentDtoAr.getContent());
//        comment.setTimestamp(new Date(System.currentTimeMillis()));
//        comment.setVisiteur(visiteur);
//        // Find the article
//        Article article = articleRepository.findById(commentDtoAr.getArticl_id())
//                .orElseThrow(() -> new ResourceNotFoundException("Article not found"));
//        // Set the associated article
//        comment.setArticle(article);
//        commentRepository.save(comment);
//    }

    public void addCommentToRecipe(Long visiteur_id, CommentDto commentDto) {
        // Find the visitor
        Visiteur visiteur = visiteurRepository.findById(visiteur_id)
                .orElseThrow(() -> new ResourceNotFoundException("Visiteur not found"));
        // Map CommentDto to Comment
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setTimestamp(new Date(System.currentTimeMillis()));
        comment.setVisiteur(visiteur);
        // Find the recipe
        Recipe recipe = recipeRepository.findById(commentDto.getRecipe_id())
                .orElseThrow(() -> new ResourceNotFoundException("Recipe not found"));
        // Set the associated recipe
        comment.setRecipe(recipe);
        // Save the comment
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
    private Article findArticleById(Long articl_id) {
        return articleRepository.findById(articl_id).orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + articl_id));
    }

    private Visiteur findVisiteurById(Long visiteur_id) {
        return visiteurRepository.findById(visiteur_id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + visiteur_id));
    }

    private Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
    }
}