import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyRiskComponent } from './company-risk.component';

import { CompanyRiskRoutingModule } from './company-risk-routing.module';

@NgModule({
  imports: [
    CommonModule,
    CompanyRiskRoutingModule
  ],
  declarations: [
    CompanyRiskComponent
  ]
})
export class CompanyRiskModule { }
