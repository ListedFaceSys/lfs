import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegionRiskComponent } from './region-risk.component';
import { WarningMoreComponent } from "./warning-more/warning-more.component";
import { TrackMoreComponent } from "./track-more/track-more.component";

const routes: Routes = [
  { path: 'region', component: RegionRiskComponent },
  { path: 'warnings', component: WarningMoreComponent },
  { path: 'tracks', component: TrackMoreComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegionRiskRoutingModule { }
