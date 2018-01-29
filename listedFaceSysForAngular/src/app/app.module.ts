import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {CommonApi} from "./service/api/commonApi";
import {SendDataService} from "./service/transmit/senddata";
import {IsLastDirective} from "./common/utill/directive/isLast";
import {LoginComponent} from "./public/login/login.component";
import {ErrorComponent} from "./public/error/error.component";


@NgModule({
  declarations: [
    AppComponent,

    IsLastDirective, //指令
    LoginComponent,//登录模块
    ErrorComponent, //报错模块
  ],
  imports: [
    BrowserModule
  ],
  providers: [CommonApi,SendDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
