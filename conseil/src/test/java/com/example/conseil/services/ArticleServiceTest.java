package com.example.conseil.services;
import com.example.conseil.dto.ArticleDto;
import com.example.conseil.entities.Article;
import com.example.conseil.entities.Specialist;
import com.example.conseil.exception.ResourceNotFoundException;
import com.example.conseil.mapper.ArticleMapper;
import com.example.conseil.repository.ArticleRepository;
import com.example.conseil.repository.SpecialistRepository;
import com.example.conseil.services.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

@Mock
private ArticleRepository articleRepository;

@Mock
private SpecialistRepository specialistRepository;

@Mock
private ArticleMapper articleMapper;

@InjectMocks
private ArticleService articleService;

private Article article;
private ArticleDto articleDto;
private Specialist specialist;

@BeforeEach
public void setUp() {
    article = new Article();
    article.setId(1L);
    article.setTitre("Test Title");
    article.setContenu("Test Content");
    article.setDatePublication(new Date(System.currentTimeMillis()));

    articleDto = new ArticleDto();
    articleDto.setId(1L);
    articleDto.setTitre("Test Title DTO");
    articleDto.setContenu("Test Content DTO");

    specialist = new Specialist();
    specialist.setId(1L);
    specialist.setFullName("Dr. John Doe");
}

// Test du scénario de succès pour l'ajout d'un article
@Test
public void testAddArticle_Success() {
    when(articleMapper.articleDtoToArticle(articleDto)).thenReturn(article);
    when(specialistRepository.findById(1L)).thenReturn(Optional.of(specialist));
    when(articleRepository.save(article)).thenReturn(article);
    when(articleMapper.articleToArticleDto(article)).thenReturn(articleDto);

    ArticleDto result = articleService.addArticle(articleDto, 1L, new byte[]{});

    assertNotNull(result);
    assertEquals(articleDto.getTitre(), result.getTitre());
    verify(articleRepository, times(1)).save(article);
    verify(articleMapper, times(1)).articleDtoToArticle(articleDto);
}

// Test du cas d'exception lorsque le spécialiste n'est pas trouvé
@Test
public void testAddArticle_SpecialistNotFound() {
    when(specialistRepository.findById(1L)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
        articleService.addArticle(articleDto, 1L, new byte[]{});
    });

    assertEquals("Specialist not found with id: " + articleDto.getSpecialistId(), exception.getMessage());
    verify(articleRepository, never()).save(article);
}

// Test du scénario de succès pour la récupération de tous les articles
@Test
public void testGetAllArticles_Success() {
    when(articleRepository.findAll()).thenReturn(Arrays.asList(article));
    when(articleMapper.articleToArticleDto(article)).thenReturn(articleDto);

    List<ArticleDto> result = articleService.getAllArticles();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(articleDto.getTitre(), result.get(0).getTitre());
    verify(articleRepository, times(1)).findAll();
}

// Test du scénario de succès pour la récupération d'un article par ID
@Test
public void testGetArticleById_Success() {
    when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
    when(articleMapper.articleToArticleDto(article)).thenReturn(articleDto);

    ArticleDto result = articleService.getArticleById(1L);

    assertNotNull(result);
    assertEquals(articleDto.getTitre(), result.getTitre());
    verify(articleRepository, times(1)).findById(1L);
}

// Test du cas d'exception lorsque l'article n'est pas trouvé
@Test
public void testGetArticleById_ArticleNotFound() {
    when(articleRepository.findById(1L)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
        articleService.getArticleById(1L);
    });

    assertEquals("Article not found with id: 1", exception.getMessage());
}

// Test du scénario de succès pour la suppression d'un article
@Test
public void testDeleteArticle_Success() {
    when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

    articleService.deleteArticle(1L);

    verify(articleRepository, times(1)).delete(article);
}

// Test du cas d'exception lors de la suppression d'un article non trouvé
@Test
public void testDeleteArticle_ArticleNotFound() {
    when(articleRepository.findById(1L)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
        articleService.deleteArticle(1L);
    });

    assertEquals("Article not found with id: 1", exception.getMessage());
    verify(articleRepository, never()).delete(article);
}

// Test du scénario de succès pour la mise à jour d'un article
@Test
public void testUpdateArticle_Success() {
    when(articleRepository.findById(1L)).thenReturn(Optional.of(article));
    when(articleRepository.save(article)).thenReturn(article);
    when(articleMapper.articleToArticleDto(article)).thenReturn(articleDto);

    ArticleDto result = articleService.updateArticle(1L, articleDto, new byte[]{});

    assertNotNull(result);
    assertEquals(articleDto.getTitre(), result.getTitre());
    verify(articleRepository, times(1)).save(article);
}

// Test du cas d'exception lors de la mise à jour d'un article non trouvé
@Test
public void testUpdateArticle_ArticleNotFound() {
    when(articleRepository.findById(1L)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
        articleService.updateArticle(1L, articleDto, new byte[]{});
    });

    assertEquals("Article not found with id: 1", exception.getMessage());
}
}