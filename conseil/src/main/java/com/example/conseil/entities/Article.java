package com.example.conseil.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
     private String titre;
    @Column(nullable = false)
     private String contenu;
    @Column(nullable = false)
     private Date datePublication;
    @Lob
    @Column(length = 1000000)
    private byte[] image;
    @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "specialist_id")
     private Specialist specialist;
}
