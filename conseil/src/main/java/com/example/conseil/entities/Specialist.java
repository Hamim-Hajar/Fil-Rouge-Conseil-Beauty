package com.example.conseil.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Getter
@Setter
@DiscriminatorValue("specialist")
public class Specialist extends User{

  @OneToMany(fetch = FetchType.EAGER)
    private List<Recipe> recipeList;
  @OneToMany(fetch = FetchType.EAGER)
    private  List<Article> articleList;
}
