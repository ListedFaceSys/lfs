import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ErrorComponent } from './error/error.component';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { HeaderComponent } from './header/header.component';

import { PublicRoutingModule } from './public-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PublicRoutingModule
  ],
  declarations: [
    ErrorComponent,   //报错模块
    LoginComponent,   //登录模块
    MenuComponent,    //菜单模块
    HeaderComponent   //头, BaseComponent部模块
  ],
  providers: [],
  exports: [
    HeaderComponent,
    ErrorComponent
  ]
})
export class PublicModule { }
