import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxEchartsModule } from 'ngx-echarts';
import { AccordionModule } from "ngx-bootstrap";

import { AreaViewComponent } from './area-view.component';
import { GeographyComponent } from '../region-risk/geography/geography.component';
import { CategoryComponent } from '../region-risk/category/category.component';
import { EarlyWarningComponent } from '../region-risk/early-warning/early-warning.component';
import { NewsEventComponent } from '../region-risk/news-event/news-event.component';
import { NewsTrackComponent } from '../region-risk/news-track/news-track.component';

import { LoginService } from '../common/service/login.service';
import { AreaViewApiService } from '../common/api/area-view-api.service';

@NgModule({
  imports: [
    CommonModule,
    NgxEchartsModule,
    AccordionModule.forRoot()
  ],
  declarations: [
    AreaViewComponent,
    GeographyComponent,     //上市公司地理分布一览
    CategoryComponent,      //上市公司类别分布一览
    EarlyWarningComponent,  //监测预警风险Top5
    NewsEventComponent,     //热点新闻-新闻事件时间趋势图
    NewsTrackComponent      //负面新闻跟踪
  ],
  providers: [
    LoginService,
    AreaViewApiService
  ]
})
export class AreaViewModule { }
