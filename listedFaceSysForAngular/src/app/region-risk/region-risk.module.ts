import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { NgxEchartsModule } from 'ngx-echarts';
import { AccordionModule, TooltipModule, CarouselModule } from 'ngx-bootstrap';

import { RegionRiskComponent } from './region-risk.component';
import { GeographyComponent } from './geography/geography.component';
import { TrendChartComponent } from './trend-chart/trend-chart.component';
import { NewsEventComponent } from './news-event/news-event.component';
import { EarlyWarningComponent } from './early-warning/early-warning.component';
import { NewsTrackComponent } from './news-track/news-track.component';

import { LoginService } from '../common/service/login.service';
import { AreaViewApiService } from '../common/api/area-view-api.service';

@NgModule({
  imports: [
    CommonModule,
    NgxEchartsModule,
    AccordionModule.forRoot(),
    TooltipModule.forRoot(),
    CarouselModule.forRoot()
  ],
  declarations: [
    RegionRiskComponent,
    GeographyComponent,     // 上市公司地理分布一览
    TrendChartComponent,    // 监测预警趋势图
    EarlyWarningComponent,  // 监测预警新闻
    NewsEventComponent,     // 热点新闻
    NewsTrackComponent      // 负面新闻跟踪
  ],
  providers: [
    LoginService,
    AreaViewApiService
  ]
})
export class RegionRiskModule { }
