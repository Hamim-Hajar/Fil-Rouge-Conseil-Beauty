package com.example.conseil.services;
import com.example.conseil.dto.ArticleDto;
import com.example.conseil.entities.Article;
import com.example.conseil.entities.Specialist;
import com.example.conseil.exception.ResourceNotFoundException;
import com.example.conseil.mapper.ArticleMapper;
import com.example.conseil.repository.ArticleRepository;
import com.example.conseil.repository.SpecialistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceTest {
@Mock
private ArticleRepository articleRepository;

@Mock
private SpecialistRepository specialistRepository;

@Mock
private ArticleMapper articleMapper;

@InjectMocks
private ArticleService articleService;

@BeforeEach
void setUp() {
    MockitoAnnotations.openMocks(this);
}

@Test
void testAddArticle() {
    // Arrange
    ArticleDto articleDto = new ArticleDto();
    articleDto.setTitre("Test Article");

    Specialist specialist = new Specialist();
    specialist.setId(1L);

    Article article = new Article();
    article.setId(1L);
    article.setTitre("Test Article");

    when(specialistRepository.findById(1L)).thenReturn(Optional.of(specialist));
    when(articleMapper.articleDtoToArticle(articleDto)).thenReturn(article);
    when(articleRepository.save(any(Article.class))).thenReturn(article);
    when(articleMapper.articleToArticleDto(article)).thenReturn(articleDto);

    // Act
    ArticleDto result = articleService.addArticle(articleDto, 1L, new byte[]{});

    // Assert
    assertNotNull(result);
    assertEquals("Test Article", result.getTitre());
    verify(articleRepository).save(any(Article.class));
}

@Test
void testGetAllArticles() {
    // Arrange
    Article article1 = new Article();
    Article article2 = new Article();
    List<Article> articles = Arrays.asList(article1, article2);

    ArticleDto articleDto1 = new ArticleDto();
    ArticleDto articleDto2 = new ArticleDto();

    when(articleRepository.findAll()).thenReturn(articles);
    when(articleMapper.articleToArticleDto(article1)).thenReturn(articleDto1);
    when(articleMapper.articleToArticleDto(article2)).thenReturn(articleDto2);

    // Act
    List<ArticleDto> result = articleService.getAllArticles();

    // Assert
    assertEquals(2, result.size());
    verify(articleRepository).findAll();
}

@Test
void testGetArticleById() {
    // Arrange
    Long articleId = 1L;
    Article article = new Article();
    article.setId(articleId);

    ArticleDto articleDto = new ArticleDto();
    articleDto.setId(articleId);

    when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));
    when(articleMapper.articleToArticleDto(article)).thenReturn(articleDto);

    // Act
    ArticleDto result = articleService.getArticleById(articleId);

    // Assert
    assertNotNull(result);
    assertEquals(articleId, result.getId());
}

@Test
void testDeleteArticle() {
    // Arrange
    Long articleId = 1L;
    Article article = new Article();
    article.setId(articleId);

    when(articleRepository.findById(articleId)).thenReturn(Optional.of(article));

    // Act
    articleService.deleteArticle(articleId);

    // Assert
    verify(articleRepository).delete(article);
}
}