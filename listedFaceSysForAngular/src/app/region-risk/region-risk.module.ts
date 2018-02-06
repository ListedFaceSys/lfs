import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { NgxEchartsModule } from 'ngx-echarts';
import { AccordionModule, CarouselModule, PaginationModule, BsDatepickerModule } from 'ngx-bootstrap';
import { defineLocale } from 'ngx-bootstrap/chronos';
import { zhCnLocale } from 'ngx-bootstrap/locale';
defineLocale('zh-cn', zhCnLocale);

import { RegionRiskComponent } from './region-risk.component';
import { GeographyComponent } from './geography/geography.component';
import { TrendChartComponent } from './trend-chart/trend-chart.component';
import { NewsEventComponent } from './news-event/news-event.component';
import { EarlyWarningComponent } from './early-warning/early-warning.component';
import { NewsTrackComponent } from './news-track/news-track.component';
import { WarningMoreComponent } from './warning-more/warning-more.component';
import { TrackMoreComponent } from './track-more/track-more.component';

import { LoginService } from '../common/service/login.service';
import { AreaViewApiService } from '../common/api/area-view-api.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgxEchartsModule,
    AccordionModule.forRoot(),
    CarouselModule.forRoot(),
    PaginationModule.forRoot(),
    BsDatepickerModule.forRoot()
  ],
  declarations: [
    RegionRiskComponent,
    GeographyComponent,     // 上市公司地理分布一览
    TrendChartComponent,    // 监测预警趋势图
    EarlyWarningComponent,  // 监测预警新闻
    NewsEventComponent,     // 热点新闻
    NewsTrackComponent,     // 负面新闻跟踪
    WarningMoreComponent,   // 更多监测预警
    TrackMoreComponent      // 更多负面跟踪
  ],
  providers: [
    LoginService,
    AreaViewApiService
  ]
})
export class RegionRiskModule { }
