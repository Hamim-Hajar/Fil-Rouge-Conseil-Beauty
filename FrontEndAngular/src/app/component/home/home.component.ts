import {Component, OnInit} from '@angular/core';
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit {
  selectedArticle: ArticleDto | null = null;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticlService
  ) {}

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      const articleId = params.get('id');
      if (articleId) {
        this.articleService.getArticleById(+articleId).subscribe((article: ArticleDto) => {
          this.selectedArticle = article; // Save the selected article details
        });
      }
    });
  }
}
