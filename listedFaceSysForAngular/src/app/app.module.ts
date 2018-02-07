import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { PublicModule } from './public/public.module';
import { RegionRiskModule } from './region-risk/region-risk.module';
import { GroupRiskModule } from './group-risk/group-risk.module';
import { CompanyRiskModule } from './company-risk/company-risk.module';
import { AtlasMapModule } from './atlas-map/atlas-map.module';

import { AppRoutingModule } from './app-routing.module';
import { UtillFun } from './common/utill/common-util';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    PublicModule,
    RegionRiskModule,   // 区域风险总览
    GroupRiskModule,    // 分组风险总览
    CompanyRiskModule,  // 上市公司风险展台
    AtlasMapModule,     // 关联图谱
    AppRoutingModule
  ],
  declarations: [
    AppComponent
  ],
  providers: [UtillFun],
  bootstrap: [AppComponent]
})
export class AppModule { }
