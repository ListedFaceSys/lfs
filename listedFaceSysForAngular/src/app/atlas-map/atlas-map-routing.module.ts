import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AtlasMapComponent } from './atlas-map.component';

const routes: Routes = [
  { path: 'atlas', component: AtlasMapComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  declarations: [RouterModule]
})
export class AtlasMapRoutingModule { }
