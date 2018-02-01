import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegionRiskComponent } from './region-risk.component';

import { RegionRiskRoutingModule } from './region-risk-routing.module';

@NgModule({
  imports: [
    CommonModule,
    RegionRiskRoutingModule
  ],
  declarations: [
    RegionRiskComponent
  ]
})
export class RegionRiskModule { }
