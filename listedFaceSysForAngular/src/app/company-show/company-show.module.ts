import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TabsModule } from 'ngx-bootstrap/tabs';

import { CompanyShowRoutingModule } from './company-show-routing.module';

import { BaseComponent } from './base/base.component';
import { CompanyShowComponent } from './company-show.component';

import { UserApiService } from '../common/api/user-api.service';

@NgModule({
  imports: [
    CommonModule,
    CompanyShowRoutingModule,
    TabsModule.forRoot()
  ],
  declarations: [
    BaseComponent,
    CompanyShowComponent
  ],
  providers: [
    UserApiService
  ]
})
export class CompanyShowModule { }
