import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { RegionRiskComponent } from './region-risk.component';

const routes: Routes = [
  { path: 'region', component: RegionRiskComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class RegionRiskRoutingModule { }
