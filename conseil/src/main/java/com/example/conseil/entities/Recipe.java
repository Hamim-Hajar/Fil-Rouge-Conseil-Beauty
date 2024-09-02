package com.example.conseil.entities;

import com.example.conseil.enums.validatorenum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
  @Id
    private Long id;
      private String name;
      private  String description;
      private String ingredients;
      private LocalDate datePublication;

      @OneToMany(fetch = FetchType.EAGER)
  private List<Comment> commentList;

  @OneToMany(fetch = FetchType.EAGER)
  private List<FavoriteRecipe> favoriteRecipeList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specialist_id")
  private Specialist specialist;


}
