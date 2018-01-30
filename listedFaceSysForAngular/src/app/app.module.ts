import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { PublicModule } from "./public/public.module";
import { CompanyShowModule } from './company-show/company-show.module';

import { IsLastDirective } from './common/utill/isLast';

import { AppRoutingModule } from './app-routing.module';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    PublicModule,
    CompanyShowModule,
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
