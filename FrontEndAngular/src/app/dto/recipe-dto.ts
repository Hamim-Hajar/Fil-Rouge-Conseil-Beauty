export interface RecipeDto {
  id: number;
  name: string;
  description: string;
  ingredients: string;
  instructions: string;
  datePublication: string; // Using string for date format (ISO format)
  specialist_id: number;
  image?: string;
  commentIds: number[];
  favoriteRecipeIds: number[];
  category: string;


}
