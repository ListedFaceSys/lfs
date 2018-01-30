import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {CompanyShowComponent} from "./company-show.component";

const routes: Routes = [
  {
    path: 'companyShow',
    component: CompanyShowComponent,
    // outlet: 'popup'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyShowRoutingModule { }
