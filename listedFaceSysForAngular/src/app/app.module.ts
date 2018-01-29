import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {IsLastDirective} from "./common/utill/isLast";
import {LoginComponent} from "./public/login/login.component";
import {ErrorComponent} from "./public/error/error.component";
import {AppRoutingModule} from "./app-routing.module";
import {FormsModule} from "@angular/forms";
import {MenuComponent} from "./public/menu/menu.component";
import {HeaderComponent} from "./public/header/header.component";


@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent, //报错模块
    IsLastDirective, //指令
    LoginComponent,//登录模块
    HeaderComponent,//头部模块
    MenuComponent, //菜单模块

  ],
  imports: [
    BrowserModule,
    FormsModule,// 数据的双向绑定 [(ngModel)]
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
