import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { EarlyWarning } from '../../common/model/early-warning';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import { BaseApiResponseModel } from "../../common/model/base-api-response.model";

@Component({
  selector: 'app-early-warning',
  templateUrl: './early-warning.component.html',
  styleUrls: ['../region-risk.component.css'],
})
export class EarlyWarningComponent implements OnInit {
  earlyWarningList: EarlyWarning[];
  copyEarlyWarningList: EarlyWarning[];

  constructor(
    private router: Router,
    private regionRiskApiService: RegionRiskApiService
  ) { }

  ngOnInit() {
    let userId = 1;
    let year = '2003';

    this.getWarningTop(userId, year);
  }

  getWarningTop(userId: number, year:string) {
    this.regionRiskApiService.getWarningTop(userId, year)
      .subscribe(
        (data: BaseApiResponseModel) => {
          console.log(data);
          console.log(data.data['creditWarningDataList'][0]['typeMap']);
          // this.earlyWarningList = data.data['warningDataList'];
          this.earlyWarningList = data.data['creditWarningDataList'].slice(5);
          // this.copyEarlyWarningList = data.data['warningDataList'].slice(5, 10);
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  moreWarning() {
    this.router.navigate(['lfs/warnings']);
  }

}
