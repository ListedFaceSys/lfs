import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxEchartsModule } from 'ngx-echarts';

import { AreaViewComponent } from './area-view.component';
import { GeographyComponent } from '../area-view/geography/geography.component';
import { CategoryComponent } from '../area-view/category/category.component';

@NgModule({
  imports: [
    CommonModule,
    NgxEchartsModule
  ],
  declarations: [
    AreaViewComponent,
    GeographyComponent,
    CategoryComponent
  ]
})
export class AreaViewModule { }
