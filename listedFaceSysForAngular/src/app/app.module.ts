import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CompanyShowModule } from './company-show/company-show.module';

import { ErrorComponent } from './public/error/error.component';
import { LoginComponent } from './public/login/login.component';
import { HeaderComponent } from './public/header/header.component';
import { MenuComponent } from './public/menu/menu.component';
import { IsLastDirective } from './common/utill/isLast';

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,      //数据的双向绑定 [(ngModel)]
    AppRoutingModule,
    CompanyShowModule
  ],
  declarations: [
    AppComponent,
    ErrorComponent,   //报错模块
    LoginComponent,   //登录模块
    HeaderComponent,  //头, BaseComponent部模块
    MenuComponent,    //菜单模块
    IsLastDirective   //指令
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
