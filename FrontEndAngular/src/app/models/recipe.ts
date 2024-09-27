import {FavoriteRecipe} from "./favorite-recipe";

export class Recipe {
  id: number;
  name: string;
  description: string;
  ingredients: string;
  datePublication: string; // Representing `LocalDate` as ISO string
  instructions: string;
  image: string;
  category: string; // Enum in backend, string in frontend
  commentList: Comment[]; // List of Comment objects
  favoriteRecipeList: FavoriteRecipe[]; // List of FavoriteRecipe objects
  specialistId: number; // Store the ID of the Specialist

  constructor(
    id: number,
    name: string,
    description: string,
    ingredients: string,
    datePublication: string,
    instructions: string,
    image: string,
    category: string,
    commentList: Comment[],
    favoriteRecipeList: FavoriteRecipe[],
    specialistId: number
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.ingredients = ingredients;
    this.datePublication = datePublication;
    this.instructions= instructions;
    this.image = image;
    this.category = category;
    this.commentList = commentList;
    this.favoriteRecipeList = favoriteRecipeList;
    this.specialistId = specialistId;
  }
}
