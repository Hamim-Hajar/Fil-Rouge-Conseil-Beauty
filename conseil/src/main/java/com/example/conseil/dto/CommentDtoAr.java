package com.example.conseil.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDtoAr {

    private String content;
    private Date timestamp;
//    private Long visiteur_id;
    private Long articl_id;
}
