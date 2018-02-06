import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { EarlyWarning } from '../../common/model/early-warning';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';

@Component({
  selector: 'app-early-warning',
  templateUrl: './early-warning.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class EarlyWarningComponent implements OnInit {
  earlyWarningList: Array<EarlyWarning>;

  constructor(
    private router: Router,
    private regionRiskApiService: RegionRiskApiService
  ) { }

  ngOnInit() {
    let userId = 1;
    let year = new Date().getFullYear().toString();

    this.getWarningTop(userId, year);
  }

  getWarningTop(userId: number, year:string) {
    this.regionRiskApiService.getWarningTop(userId, year)
      .subscribe(
        data => {
          this.earlyWarningList = data.data['creditWarningDataList'];
          console.log(this.earlyWarningList);
        },
        error => {
          console.log(error);
        }
      );
  }

  moreWarning() {
    this.router.navigate(['lfs/warnings']);
  }

}
