import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./public/login/login.component";
import {Business} from "./business/business";
import {HomeComponent} from "./public/home/home.component";
import {ErrorComponent} from "./public/error/error.component";

const routes: Routes = [
  {path:'',redirectTo:"login",pathMatch:"full"},
  {path:'login',component:LoginComponent },
  {path:'business',component:Business,
    children:[
      {path:'home',component:HomeComponent },
    ]
  },
  {path:'**',component:ErrorComponent},
]

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
