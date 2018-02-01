import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';

import { ErrorComponent } from './error/error.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistComponent } from './regist/regist.component';
import { MenuComponent } from './menu/menu.component';
import { HeaderComponent } from './header/header.component';

import { PublicRoutingModule } from './public-routing.module';

import { LoginService } from '../common/service/login.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    PublicRoutingModule,
    BsDropdownModule.forRoot()
  ],
  declarations: [
    ErrorComponent,   // 报错
    HomeComponent,    // 首页
    LoginComponent,   // 登录
    RegistComponent,  // 注册
    MenuComponent,    // 菜单
    HeaderComponent   // 头部
  ],
  providers: [
    LoginService
  ],
  exports: [
    HeaderComponent,
    ErrorComponent
  ]
})
export class PublicModule { }
