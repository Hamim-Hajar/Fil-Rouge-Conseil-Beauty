//package com.example.conseil.services;
//
//import com.example.conseil.dto.ArticleDto;
//import com.example.conseil.entities.Article;
//import com.example.conseil.entities.Specialist;
//import com.example.conseil.mapper.ArticleMapper;
//import com.example.conseil.repository.ArticleRepository;
//import com.example.conseil.repository.SpecialistRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import static org.mockito.ArgumentMatchers.any;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class ArticleServiceTest {
//    @Mock
//    private ArticleRepository articleRepository;
//
//    @Mock
//    private SpecialistRepository specialistRepository;
//
//    @Mock
//    private ArticleMapper articleMapper;
//
//    @InjectMocks
//    private ArticleService articleService;
//
//    private Specialist specialist;
//    private Article article;
//    private ArticleDto articleDto;
//
//    @BeforeEach
//    void setUp() {
//        specialist = new Specialist();
//        article = new Article();
//        articleDto = new ArticleDto();
//    }
//    @Test
//    void addArticle() {
//        Long specialistId = 1L;
//        when(specialistRepository.findById(specialistId)).thenReturn(Optional.of(specialist));
//        when(articleMapper.articleDtoToArticle(articleDto)).thenReturn(article);
//        when(articleRepository.save(any(Article.class))).thenReturn(article);
//        when(articleMapper.articleToArticleDto(article)).thenReturn(articleDto);
//
//        ArticleDto createdArticle = articleService.addArticle(articleDto, specialistId);
//
//        assertNotNull(createdArticle);
//        verify(specialistRepository).findById(specialistId);
//        verify(articleRepository).save(article);
//    }
//
//    @Test
//    void getAllArticles() {
//        when(articleRepository.findAll()).thenReturn(Arrays.asList(article));
//
//        List<ArticleDto> articles = articleService.getAllArticles();
//
//        assertEquals(1, articles.size());
//        verify(articleRepository).findAll();
//    }
//
//    @Test
//    void getArticleById() {
//        Long id = 1L;
//        when(articleRepository.findById(id)).thenReturn(Optional.of(article));
//        when(articleMapper.articleToArticleDto(article)).thenReturn(articleDto);
//
//        ArticleDto foundArticle = articleService.getArticleById(id);
//
//        assertNotNull(foundArticle);
//        verify(articleRepository).findById(id);
//    }
//
//    @Test
//    void deleteArticle() {
//        Long id = 1L;
//        when(articleRepository.findById(id)).thenReturn(Optional.of(article));
//
//        articleService.deleteArticle(id);
//
//        verify(articleRepository).delete(article);
//    }
//}