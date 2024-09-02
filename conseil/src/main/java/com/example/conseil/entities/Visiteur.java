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
@DiscriminatorValue("visiteur")
public class Visiteur extends User{
    @OneToMany(fetch = FetchType.EAGER)
    private List<Comment> commentList;
    @OneToMany(fetch = FetchType.EAGER)
    private  List<FavoriteRecipe> favoriteRecipeList;

}
