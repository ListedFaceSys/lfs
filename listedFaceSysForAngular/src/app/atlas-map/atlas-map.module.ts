import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AtlasMapComponent } from './atlas-map.component';

import { AtlasMapRoutingModule } from './atlas-map-routing.module';

@NgModule({
  imports: [
    CommonModule,
    AtlasMapRoutingModule
  ],
  declarations: [
    AtlasMapComponent
  ]
})
export class AtlasMapModule { }
