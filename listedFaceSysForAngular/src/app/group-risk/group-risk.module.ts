import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { GroupRiskComponent } from './group-risk.component';

import { GroupRiskRoutingModule } from './group-risk-routing.module';
import {GroupNewsEventComponent} from "./group-news-event/group-news-event.component";
import {AccordionModule} from "ngx-bootstrap";
import {NgxEchartsModule} from "ngx-echarts";

@NgModule({
  imports: [
    CommonModule,
    GroupRiskRoutingModule,
    NgxEchartsModule,
    AccordionModule.forRoot()
  ],
  declarations: [
    GroupRiskComponent,
    GroupNewsEventComponent,

  ]
})
export class GroupRiskModule { }
