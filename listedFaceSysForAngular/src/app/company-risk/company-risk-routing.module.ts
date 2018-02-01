import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CompanyRiskComponent } from './company-risk.component';

const routes: Routes = [
  { path: 'company', component: CompanyRiskComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyRiskRoutingModule { }
