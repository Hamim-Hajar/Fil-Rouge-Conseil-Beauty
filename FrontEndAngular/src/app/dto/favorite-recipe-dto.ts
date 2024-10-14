export interface FavoriteRecipeDto {
  id: number;
  recipeName: string;
  recipe: {
    id: number;
    name: string;
    description: string;
    image: string;
    datePublication: string;
  };



}
