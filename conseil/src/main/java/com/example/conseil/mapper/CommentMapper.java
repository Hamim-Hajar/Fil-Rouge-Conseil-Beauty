package com.example.conseil.mapper;

import com.example.conseil.dto.CommentDto;
import com.example.conseil.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "visiteur.id", target = "visiteurId")
    @Mapping(source = "recipe.id", target = "recipeId")
    CommentDto toDto(Comment comment);

    @Mapping(source = "visiteurId", target = "visiteur.id")
    @Mapping(source = "recipeId", target = "recipe.id")
    Comment toEntity(CommentDto commentDto);
}
