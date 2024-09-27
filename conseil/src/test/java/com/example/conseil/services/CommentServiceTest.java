//package com.example.conseil.services;
//
//import com.example.conseil.dto.CommentDto;
//import com.example.conseil.entities.Comment;
//import com.example.conseil.entities.Recipe;
//import com.example.conseil.entities.Visiteur;
//import com.example.conseil.exception.ResourceNotFoundException;
//import com.example.conseil.mapper.CommentMapper;
//import com.example.conseil.repository.CommentRepository;
//import com.example.conseil.repository.RecipeRepository;
//import com.example.conseil.repository.VisiteurRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import static org.mockito.Mockito.when;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//import static org.mockito.ArgumentMatchers.any;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CommentServiceTest {
//
//        @Mock
//        private RecipeRepository recipeRepository;
//
//        @Mock
//        private VisiteurRepository visiteurRepository;
//
//        @Mock
//        private CommentRepository commentRepository;
//
//        @Mock
//        private CommentMapper commentMapper;
//
//        @InjectMocks
//        private CommentService commentService;
//
//        @Test
//        void addComment_Success() {
//            // Arrange
//            Long recipeId = 1L;
//            Long userId = 1L;
//            String content = "Test comment";
//
//            Recipe recipe = new Recipe();
//            recipe.setId(recipeId);
//
//            Visiteur visiteur = new Visiteur();
//            visiteur.setId(userId);
//
//            Comment comment = new Comment();
//            comment.setId(1L);
//            comment.setContent(content);
//            comment.setTimestamp(LocalDateTime.now());
//            comment.setVisiteur(visiteur);
//            comment.setRecipe(recipe);
//
//            CommentDto commentDto = new CommentDto();
//            commentDto.setId(1L);
//            commentDto.setContent(content);
//
//            when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
//            when(visiteurRepository.findById(userId)).thenReturn(Optional.of(visiteur));
//            when(commentRepository.save(any(Comment.class))).thenReturn(comment);
//            when(commentMapper.toDto(comment)).thenReturn(commentDto);
//
//            // Act
//            CommentDto result = commentService.addComment(recipeId, userId, content);
//
//            // Assert
//            assertNotNull(result);
//            assertEquals(commentDto.getId(), result.getId());
//            assertEquals(commentDto.getContent(), result.getContent());
//
//            verify(recipeRepository).findById(recipeId);
//            verify(visiteurRepository).findById(userId);
//            verify(commentRepository).save(any(Comment.class));
//            verify(commentMapper).toDto(comment);
//        }
//
//        @Test
//        void addComment_RecipeNotFound() {
//            // Arrange
//            Long recipeId = 1L;
//            Long userId = 1L;
//            String content = "Test comment";
//
//            when(recipeRepository.findById(recipeId)).thenReturn(Optional.empty());
//
//            // Act & Assert
//            assertThrows(ResourceNotFoundException.class, () ->
//                    commentService.addComment(recipeId, userId, content)
//            );
//
//            verify(recipeRepository).findById(recipeId);
//            verifyNoInteractions(visiteurRepository, commentRepository, commentMapper);
//        }
//
//        @Test
//        void addComment_UserNotFound() {
//            // Arrange
//            Long recipeId = 1L;
//            Long userId = 1L;
//            String content = "Test comment";
//
//            Recipe recipe = new Recipe();
//            recipe.setId(recipeId);
//
//            when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(recipe));
//            when(visiteurRepository.findById(userId)).thenReturn(Optional.empty());
//
//            // Act & Assert
//            assertThrows(ResourceNotFoundException.class, () ->
//                    commentService.addComment(recipeId, userId, content)
//            );
//
//            verify(recipeRepository).findById(recipeId);
//            verify(visiteurRepository).findById(userId);
//            verifyNoInteractions(commentRepository, commentMapper);
//        }
//
//    @Test
//    void getCommentsByRecipe() {
//    }
//
//    @Test
//    void deleteComment() {
//    }
//
//    @Test
//    void updateComment() {
//    }
//}