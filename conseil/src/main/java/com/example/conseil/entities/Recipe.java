package com.example.conseil.entities;

import com.example.conseil.enums.RecipeCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
      private String name;
  @Column(nullable = false)
      private  String description;
  @Column(nullable = false)
      private String ingredients;
  @Column(nullable = false)
      private LocalDateTime datePublication;
  @Column(nullable = false)
      private String Instructions;
  @Column(nullable = false)
      private String image;
     @Enumerated(EnumType.STRING)
     private RecipeCategory category;

      @OneToMany(fetch = FetchType.EAGER)
  private List<Comment> commentList;

  @OneToMany(fetch = FetchType.EAGER)
  private List<FavoriteRecipe> favoriteRecipeList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specialist_id")
  private Specialist specialist;


}
