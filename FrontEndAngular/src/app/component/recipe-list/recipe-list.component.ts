import {Component, OnInit, ViewChild} from '@angular/core';
import {RecipeDto} from "../../dto/recipe-dto";
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {RecipeService} from "../../services/recipe.service";
import {Router} from "@angular/router";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  listrecipe: RecipeDto[] = [];
  displayedColumns: string[] = ['image', 'name', 'description', 'ingredients', 'instructions','category', 'deleteRecipe'];
  clickedRows = new Set<RecipeDto>();
  paginatedDataSource = new MatTableDataSource<RecipeDto>();

  @ViewChild(MatPaginator) paginator!: MatPaginator; // Use ViewChild to access the paginator

  constructor(private recipeservice: RecipeService, private router: Router) {}

  ngOnInit(): void {
    this.fetchAllRecipes();
  }

  fetchAllRecipes() {
    this.recipeservice.getAllRecipes().subscribe((res: RecipeDto[]) => {
      this.listrecipe = res;
      this.paginatedDataSource.data = this.listrecipe; // Set data for pagination
      this.paginatedDataSource.paginator = this.paginator; // Attach paginator
      console.log(res);
    });
  }

  deleteRecipe(id: number) {
    this.recipeservice.deleteRecipe(id).subscribe(() => {
      this.fetchAllRecipes();
    });
  }

  update(id: number): void {
    this.router.navigate(['/updaterecipe', id]);
  }

  onPageChange(event: any) {
    // This method can be used if you need to perform any action on page change
    this.paginatedDataSource.paginator = this.paginator; // Ensure paginator is attached
  }
}
