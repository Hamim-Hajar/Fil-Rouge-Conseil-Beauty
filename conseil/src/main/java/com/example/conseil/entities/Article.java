package com.example.conseil.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    private Long id;
     private String titre;
     private String contenu;
     private String datePublication;

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "specialist_id")
     private Specialist specialist;
}
