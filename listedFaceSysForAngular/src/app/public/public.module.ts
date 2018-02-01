import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistComponent } from './regist/regist.component';
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
    ErrorComponent,   // 报错
    HomeComponent,    // 首页
    LoginComponent,   // 登录
    RegistComponent,  // 注册
    MenuComponent,    // 菜单
    HeaderComponent   // 头部
  ],
  exports: [
    HeaderComponent,
    ErrorComponent
  ]
})
export class PublicModule { }
