import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RegionRiskComponent } from './region-risk.component';

import {GeographyComponent} from './geography/geography.component';
import {NewsEventComponent} from './news-event/news-event.component';
import {EarlyWarningComponent} from './early-warning/early-warning.component';
import {NewsTrackComponent} from './news-track/news-track.component';
import {CategoryComponent} from './category/category.component';
import {NgxEchartsModule} from 'ngx-echarts';
import {AccordionModule} from 'ngx-bootstrap';
import {LoginService} from '../common/service/login.service';
import {AreaViewApiService} from '../common/api/area-view-api.service';

@NgModule({
  imports: [
    CommonModule,
    NgxEchartsModule,
    AccordionModule.forRoot()
  ],
  declarations: [
    RegionRiskComponent,

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
export class RegionRiskModule { }
