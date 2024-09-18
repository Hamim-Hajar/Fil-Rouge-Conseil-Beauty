package com.example.conseil.services;

import com.example.conseil.dto.ArticleDto;
import com.example.conseil.entities.Article;
import com.example.conseil.entities.Specialist;
import com.example.conseil.exception.ResourceNotFoundException;
import com.example.conseil.mapper.ArticleMapper;
import com.example.conseil.repository.ArticleRepository;
import com.example.conseil.repository.SpecialistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private SpecialistRepository specialistRepository;

    @Autowired
    private ArticleMapper articleMapper;

    public ArticleDto addArticle(ArticleDto articleDto,Long specialistId) {
        Article article = articleMapper.articleDtoToArticle(articleDto);

        Specialist specialist = specialistRepository.findById(specialistId).orElseThrow(() ->
                new ResourceNotFoundException("Specialist not found with id: " + articleDto.getSpecialistId()));
       article.setDatePublication(new Date(System.currentTimeMillis()));

        article.setSpecialist(specialist);

        Article article1 = articleRepository.save(article);
        return  articleMapper.articleToArticleDto(article1);
    }




    public List<ArticleDto> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(articleMapper::articleToArticleDto)
                .toList();
    }

    public ArticleDto getArticleById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + id));
        return articleMapper.articleToArticleDto(article);
    }

    public void deleteArticle(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + id));
        articleRepository.delete(article);
    }
}
