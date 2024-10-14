import {Component, OnInit} from '@angular/core';
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {jwtDecode} from "jwt-decode";
import {Article} from "../../models/article";

@Component({
  selector: 'app-add-articl',
  templateUrl: './add-articl.component.html',
  styleUrls: ['./add-articl.component.css']
})
export class AddArticlComponent implements OnInit{

  formArticle !: FormGroup
  specialist_id!:number;
  selectedImage!: File;
   articleid?:number;
  constructor(
    private fb: FormBuilder,
    private articleService: ArticlService,
    private route:ActivatedRoute

  ) { }

  ngOnInit(): void {
    this.getId()
    this.fetchIdUpdate()
    this.addArticle()

  }

addArticle(){
    this.formArticle=this.fb.group({
      titre:["",Validators.required],
      contenu:["",Validators.required],
      image:["",Validators.required]
    })
}
getId(){
    const token : any = localStorage.getItem("token")
   const decodeToken :any = jwtDecode(token)
  this.specialist_id = decodeToken.id
  console.log(this.specialist_id)

}
  onSubmit(){
    const valid = this.formArticle.valid
    console.log(this.specialist_id)
    console.log(valid)

    if(valid){
      const value=  this.formArticle.value
      this.articleService.addArticle(value,this.specialist_id,this.selectedImage).subscribe()
      console.log(value)
      this.formArticle.reset()
    }
  }

  // onFileChange(event: any) {
  //   if (event.target.files.length > 0) {
  //     this.selectedImage = event.target.files[0]; // Get the actual File object
  //   }
  // }
  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.selectedImage = file;
      // Additional validation can be done here, e.g. file type or size
      if (file.size > 2 * 1024 * 1024) { // Example: limit file size to 2MB
        console.error('File is too large');
        this.formArticle.get('image')?.setErrors({ tooLarge: true });
      }
    }
  }

  fetchIdUpdate(){
    this.route.params.subscribe(params =>{
      this.articleid= +params['id'];
    if(this.articleid){
      this.loadArticle();
    }
    })
  }
  loadArticle(): void {
    this.articleService.getArticleById(this.articleid!).subscribe(
      (article:ArticleDto) => {
        this.formArticle.patchValue(article);
      },
      error => {
        console.error('Error fetching equipment', error);
      }
    );
  }


}
