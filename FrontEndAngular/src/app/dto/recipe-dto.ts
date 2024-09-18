export interface RecipeDto {
  id: number;
  name: string;
  description: string;
  ingredients: string;
  datePublication: string; // Using string for date format (ISO format)
  specialistId: number;
  commentIds: number[];
  favoriteRecipeIds: number[];


}
