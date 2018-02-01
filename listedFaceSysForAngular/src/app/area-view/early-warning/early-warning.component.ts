import { Component, OnInit } from '@angular/core';

import { AreaViewApiService } from '../../common/api/area-view-api.service';

@Component({
  selector: 'app-early-warning',
  templateUrl: './early-warning.component.html',
  styleUrls: ['./early-warning.component.css']
})
export class EarlyWarningComponent implements OnInit {

  constructor(
    private areaViewApiService: AreaViewApiService
  ) { }

  ngOnInit() {
    // this.areaViewApiService.getWarningTop5().subscribe(
    //   data => {
    //
    //   },
    //   error => {
    //     console.log(error);
    //   }
    // );
  }

}
