import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { GroupRiskComponent } from './group-risk.component';

const routes: Routes = [
  { path: 'group', component: GroupRiskComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class GroupRiskRoutingModule { }
