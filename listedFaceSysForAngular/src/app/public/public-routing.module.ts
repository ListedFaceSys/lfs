import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { CompanyShowComponent } from '../company-show/company-show.component';
import { CompanyViewComponent } from '../company-view/company-view.component';
import { AreaViewComponent } from '../area-view/area-view.component';
import { NtbShowComponent } from '../ntb-show/ntb-show.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'menu',
    component: MenuComponent,
    children:[
      { path: '',
        component: CompanyShowComponent,  //上市公司风险展台
      },
      { path: 'companyShow',
        component: CompanyShowComponent,
      },
      { path: 'companyView',
        component: CompanyViewComponent,    //上市公司风险总览
      },
      { path: 'areaView',
        component: AreaViewComponent,     //区域风险总览
      },
      { path: 'ntbView',
        component: NtbShowComponent,    //新三板展台
      },
      ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }
