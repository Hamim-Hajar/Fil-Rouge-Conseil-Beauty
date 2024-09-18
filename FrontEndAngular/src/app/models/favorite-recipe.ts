export class FavoriteRecipe {
  id: number;
  recipeName: string;
  value: number;
  recipeId: number; // Storing the Recipe ID (relationship to Recipe)
  visiteurId: number; // Storing the Visiteur ID (relationship to Visiteur)

  constructor(
    id: number,
    recipeName: string,
    value: number,
    recipeId: number,
    visiteurId: number
  ) {
    this.id = id;
    this.recipeName = recipeName;
    this.value = value;
    this.recipeId = recipeId;
    this.visiteurId = visiteurId;
  }
}
