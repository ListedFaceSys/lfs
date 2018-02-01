import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { MenuComponent } from './menu/menu.component';
import { RegionRiskComponent } from '../region-risk/region-risk.component';
import { GroupRiskComponent } from '../group-risk/group-risk.component';
import { CompanyRiskComponent } from '../company-risk/company-risk.component';
import { AtlasMapComponent } from '../atlas-map/atlas-map.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  {
    path: 'lfs',
    component: MenuComponent,
    children:[
      {
        path: '',
        component: RegionRiskComponent,
      },
      {
        path: 'region',
        component: RegionRiskComponent,   // 区域风险总览
      },
      {
        path: 'group',
        component: GroupRiskComponent,   // 分组风险总览
      },
      {
        path: 'company',
        component: CompanyRiskComponent,  // 上市公司风险展台
      },
      {
        path: 'atlas',
        component: AtlasMapComponent,     // 关联图谱
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }
