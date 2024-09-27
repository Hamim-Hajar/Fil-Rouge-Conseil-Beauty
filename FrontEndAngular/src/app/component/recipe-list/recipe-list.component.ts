import {Component, OnInit} from '@angular/core';
import {RecipeDto} from "../../dto/recipe-dto";
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {RecipeService} from "../../services/recipe.service";

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {


  listrecipe:RecipeDto[]=[];
  displayedColumns: string[] = ['image', 'name', 'description', 'ingredients', 'instructions','deleteRecipe'];
  clickedRows = new Set<RecipeDto>();


  constructor(private recipeservice :RecipeService) {
  }
  ngOnInit(): void {
    this.fetchAllRecipes();
  }
  fetchAllRecipes() {
    this.recipeservice.getAllRecipes().subscribe((res: RecipeDto[]) => {
      this.listrecipe = res;
      console.log(res);
    });
  }

  deleteRecipe(id:number){
    this.recipeservice.deleteRecipe(id).subscribe(()=>{
      this.fetchAllRecipes();
    })
  }


}
