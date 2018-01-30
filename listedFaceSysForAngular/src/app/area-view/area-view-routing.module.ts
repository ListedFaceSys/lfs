import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AreaViewComponent } from "./area-view.component";

const routes: Routes = [
  { path: 'areaView', component: AreaViewComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AreaViewRoutingModule { }
