import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./public/login/login.component";
import {ErrorComponent} from "./public/error/error.component";
import {MenuComponent} from "./public/menu/menu.component";

const routes: Routes = [
  {path:'',redirectTo:"login",pathMatch:"full"},
  {path:'login',component:LoginComponent },
  {
    path:'menu',component:MenuComponent
  },
  {path:'**',component:ErrorComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes,{useHash:true})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
