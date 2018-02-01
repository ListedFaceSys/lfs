import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PublicModule } from "./public/public.module";
import { CompanyShowModule } from './company-show/company-show.module';
import { AreaViewModule } from './area-view/area-view.module';

import { IsLastDirective } from './common/utill/isLast';

import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    PublicModule,

    CompanyShowModule,  //上市公司风险展台
    AreaViewModule,     //区域风险总览

    AppRoutingModule
  ],
  declarations: [
    AppComponent,
    IsLastDirective   //指令
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
