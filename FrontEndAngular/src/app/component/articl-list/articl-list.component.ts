import {Component, OnInit, ViewChild} from '@angular/core';
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {ConfirmDialogComponent} from "../confirm-dialog/confirm-dialog.component";
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {AuthService} from "../../services/auth.service";
import {User} from "../../models/user";
import {Userrole} from "../../enums/userrole";

@Component({
  selector: 'app-articl-list',
  templateUrl: './articl-list.component.html',
  styleUrls: ['./articl-list.component.css']
})
export class ArticlListComponent implements OnInit{
  listArticle: ArticleDto[] = [];
  displayedColumns: string[] = ['image', 'titre', 'contenu', 'datePublication', 'delete'];
  clickedRows = new Set<ArticleDto>();
  dataSource = new MatTableDataSource<ArticleDto>(this.listArticle); // Initialize MatTableDataSource



  @ViewChild(MatPaginator) paginator!: MatPaginator; // Reference to MatPaginator

  constructor(private articleService: ArticlService, private router: Router, private dialog: MatDialog, private authService:AuthService) {

  }

  ngOnInit(): void {
    this.fetchAllArticles();


  }


  fetchAllArticles() {
    this.articleService.getAllArticles().subscribe((res: ArticleDto[]) => {
      this.listArticle = res;
      this.dataSource.data = res; // Assign the data to MatTableDataSource
      this.dataSource.paginator = this.paginator; // Connect paginator to dataSource
      console.log(res);
    });
  }

  delete(id: number) {
    const dialogRef = this.dialog.open(ConfirmDialogComponent);

    dialogRef.afterClosed().subscribe(result => {
      if (result === true) {
        this.articleService.deleteArticle(id).subscribe(() => {
          this.fetchAllArticles();
        });
      } else {
        console.log('Suppression annulée');
      }
    });
  }

  update(id: number): void {
    this.router.navigate(['/update', id]);
  }
}
