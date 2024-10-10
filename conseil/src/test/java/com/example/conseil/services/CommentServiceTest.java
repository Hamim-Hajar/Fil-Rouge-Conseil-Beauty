package com.example.conseil.services;

import com.example.conseil.dto.CommentDto;
import com.example.conseil.entities.Comment;
import com.example.conseil.entities.Recipe;
import com.example.conseil.entities.Visiteur;
import com.example.conseil.exception.ResourceNotFoundException;
import com.example.conseil.mapper.CommentMapper;
import com.example.conseil.repository.CommentRepository;
import com.example.conseil.repository.RecipeRepository;
import com.example.conseil.repository.VisiteurRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class CommentServiceTest {
    @Mock
    private VisiteurRepository visiteurRepository;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAjouterCommentaire() {

        Long visiteurId = 1L;
        Long recipeId = 1L;

        Visiteur visiteur = new Visiteur();
        visiteur.setId(visiteurId);

        Recipe recipe = new Recipe();
        recipe.setId(recipeId);

        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Commentaire de test");
        commentDto.setRecipe_id(recipeId);


        when(visiteurRepository.findById(visiteurId)).thenReturn(Optional.of(visiteur));
        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));

        commentService.addCommentToRecipe(visiteurId, commentDto);


        verify(visiteurRepository).findById(visiteurId);
        verify(recipeRepository).findById(recipeId);


        verify(commentRepository).save(any(Comment.class));
    }
}