import { Component } from '@angular/core';
import {Recipe} from "../../models/recipe";
import {RecipeService} from "../../services/recipe.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {jwtDecode} from "jwt-decode";

@Component({
  selector: 'app-addrecipe',
  templateUrl: './addrecipe.component.html',
  styleUrls: ['./addrecipe.component.css']
})
export class AddrecipeComponent {
  formRecipe !: FormGroup
  specialist_id= 2;
  selectedImage!: File;

  constructor(private recipeService: RecipeService, private fb: FormBuilder,) {}

  ngOnInit(): void {
    this.getId()
    this.addRecipe()
  }


  addRecipe(){
    this.formRecipe=this.fb.group({
      name:["",Validators.required],
      description:["",Validators.required],
      ingredients:["",Validators.required],
      instructions: ["",Validators.required],
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
    const valid = this.formRecipe.valid
    console.log(this.specialist_id)

    if(valid){
      const value=  this.formRecipe.value
      this.recipeService.addRecipe(value,this.specialist_id,this.selectedImage).subscribe()
      console.log(value)
      this.formRecipe.reset()
    }
  }
  onFileChange(event: any) {
    if (event.target.files.length > 0) {
      this.selectedImage = event.target.files[0];
    }
  }

}
