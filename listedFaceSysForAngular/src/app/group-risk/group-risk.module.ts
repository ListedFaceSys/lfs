import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GroupRiskComponent } from './group-risk.component';

import { GroupRiskRoutingModule } from './group-risk-routing.module';

@NgModule({
  imports: [
    CommonModule,
    GroupRiskRoutingModule
  ],
  declarations: [GroupRiskComponent]
})
export class GroupRiskModule { }
