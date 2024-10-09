package com.example.conseil.mapper;

import com.example.conseil.dto.CommentDto;
import com.example.conseil.entities.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommentMapper {

    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);
    CommentDto commentToCommentDto(Comment comment);
    Comment commentDtoToComment(CommentDto commentDto);
}
