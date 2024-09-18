package com.example.conseil.mapper;

import com.example.conseil.dto.ArticleDto;
import com.example.conseil.entities.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ArticleMapper {
    ArticleMapper MAPPER = Mappers.getMapper(ArticleMapper.class);
    ArticleDto articleToArticleDto(Article article);
    Article articleDtoToArticle(ArticleDto articleDto);
}
