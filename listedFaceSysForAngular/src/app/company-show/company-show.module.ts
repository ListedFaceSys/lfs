import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyShowRoutingModule } from './company-show-routing.module';

import { BaseComponent } from './base/base.component';

@NgModule({
  imports: [
    CommonModule,
    CompanyShowRoutingModule
  ],
  declarations: [
    BaseComponent
  ]
})
export class CompanyShowModule { }
