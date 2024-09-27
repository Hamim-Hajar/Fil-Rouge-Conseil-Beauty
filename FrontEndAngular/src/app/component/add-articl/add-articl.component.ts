import { Component } from '@angular/core';
import {ArticleDto} from "../../dto/article-dto";
import {ArticlService} from "../../services/articl.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {jwtDecode} from "jwt-decode";

@Component({
  selector: 'app-add-articl',
  templateUrl: './add-articl.component.html',
  styleUrls: ['./add-articl.component.css']
})
export class AddArticlComponent {

  formArticle !: FormGroup
  specialist_id= 2;
  selectedImage!: File;

  constructor(
    private fb: FormBuilder,
    private articleService: ArticlService,

  ) { }

  ngOnInit(): void {
    this.getId()
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

    if(valid){
      const value=  this.formArticle.value
      this.articleService.addArticle(value,this.specialist_id,this.selectedImage).subscribe()
      console.log(value)
      this.formArticle.reset()
    }
  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.selectedImage = event.target.files[0]; // Get the actual File object
    }
  }
}
