import {Component, OnInit} from '@angular/core';
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";

@Component({
  selector: 'app-articl-list',
  templateUrl: './articl-list.component.html',
  styleUrls: ['./articl-list.component.css']
})
export class ArticlListComponent implements OnInit{

  listArticle: ArticleDto[] = [];
  displayedColumns: string[] = ['image', 'titre', 'contenu', 'datePublication', 'delete'];
  clickedRows = new Set<ArticleDto>();

  constructor(private articleService: ArticlService) { }

  ngOnInit(): void {
    this.fetchAllArticles();
  }

  fetchAllArticles() {
    this.articleService.getAllArticles().subscribe((res: ArticleDto[]) => {
      this.listArticle = res;
      console.log(res);
    });
  }

  delete(id: number) {
    this.articleService.deleteArticle(id).subscribe(() => {
      this.fetchAllArticles(); // Rafraîchir la liste après suppression
    });
  }
}
