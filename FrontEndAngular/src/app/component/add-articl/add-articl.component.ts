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
  specialistId!:number
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
  this.specialistId = decodeToken.id
  console.log(this.specialistId)

}
  onSubmit(){
    const valid = this.formArticle.valid

    if(valid){
      const value=  this.formArticle.value
      this.articleService.addArticle(value,this.specialistId,this.selectedImage).subscribe()
      console.log(value)
    }
  }

  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.selectedImage = event.target.files[0]; // Get the actual File object
    }
  }
}
