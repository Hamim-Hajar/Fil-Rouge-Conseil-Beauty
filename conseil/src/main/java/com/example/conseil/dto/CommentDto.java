package com.example.conseil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private  Long id;
    private String content;
    private Date timestamp;
    //private Long visiteur_id;
    private Long recipe_id;

}
