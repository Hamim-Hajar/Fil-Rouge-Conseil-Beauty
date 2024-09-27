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

    public ArticleDto addArticle(ArticleDto articleDto,Long specialist_id, byte[] imageBytes) {
        Article article = articleMapper.articleDtoToArticle(articleDto);

        Specialist specialist = specialistRepository.findById(specialist_id
        ).orElseThrow(() ->
                new ResourceNotFoundException("Specialist not found with id: " + articleDto.getSpecialist_id()));
       article.setDatePublication(new Date(System.currentTimeMillis()));

        article.setSpecialist(specialist);
        article.setImage(imageBytes);

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
    public ArticleDto updateArticle(Long articleId, ArticleDto articleDto, byte[] imageBytes) {
        Article existingArticle = articleRepository.findById(articleId)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + articleId));
        existingArticle.setTitre(articleDto.getTitre());
        existingArticle.setContenu(articleDto.getContenu());
        if (imageBytes != null && imageBytes.length > 0) {
            existingArticle.setImage(imageBytes);
        }
        existingArticle.setDatePublication(new Date(System.currentTimeMillis()));
        Article updatedArticle = articleRepository.save(existingArticle);
        return articleMapper.articleToArticleDto(updatedArticle);
    }



}
