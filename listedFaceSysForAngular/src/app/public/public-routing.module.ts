import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { MenuComponent } from './menu/menu.component';
import { RegionRiskComponent } from '../region-risk/region-risk.component';
import { GroupRiskComponent } from '../group-risk/group-risk.component';
import { CompanyRiskComponent } from '../company-risk/company-risk.component';
import { AtlasMapComponent } from '../atlas-map/atlas-map.component';
import { TrackMoreComponent } from '../region-risk/track-more/track-more.component';
import { WarningMoreComponent } from '../region-risk/warning-more/warning-more.component';

const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent,
    children: [
      {
        path: '',
        component: LoginComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      }
    ]
  },
  {
    path: 'lfs',
    component: MenuComponent,
    children: [
      {
        path: '',
        component: RegionRiskComponent
      },
      {
        path: 'region',
        component: RegionRiskComponent   // 区域风险总览
      },
      {
        path: 'group',
        component: GroupRiskComponent    // 分组风险总览
      },
      {
        path: 'company',
        component: CompanyRiskComponent  // 上市公司风险展台
      },
      {
        path: 'atlas',
        component: AtlasMapComponent     // 关联图谱
      },
      {
        path: 'tracks',
        component: TrackMoreComponent   // 更多负面新闻
      },
      {
        path: 'warnings',
        component: WarningMoreComponent  // 更多预警信息
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PublicRoutingModule { }
