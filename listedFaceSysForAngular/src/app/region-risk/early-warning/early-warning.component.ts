import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { EarlyWarning } from '../../common/model/early-warning';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import { BaseApiResponseModel } from "../../common/model/base-api-response.model";
import { WarningRiskIn } from '../../common/model/warning-risk-in';

@Component({
  selector: 'app-early-warning',
  templateUrl: './early-warning.component.html',
  styleUrls: ['../region-risk.component.css'],
})
export class EarlyWarningComponent implements OnInit {
  earlyWarningList: EarlyWarning[];
  copyEarlyWarningList: EarlyWarning[];
  getYear: string;

  constructor(
    private router: Router,
    private regionRiskApiService: RegionRiskApiService
  ) {
    this.getYear = new Date().getFullYear().toString();
  }

  ngOnInit() {
    let userId = 1;
    this.getWarningTop(userId, this.getYear);
  }

  getWarningTop(userId: number, year?: string, pageSize?: string, pageCount?: string) {
    let warningRiskIn: WarningRiskIn = {
      userId: userId,
      year: year,
      pageSize: pageSize,
      pageCount: pageCount
    };
    this.regionRiskApiService.getWarningTop(warningRiskIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            let list: EarlyWarning[];
            list = data.data['warningDataList'];
            if (list.length <= 5) {
              this.earlyWarningList = list;
              return;
            }
            this.earlyWarningList = data.data['warningDataList'].slice(1, 5);
            this.copyEarlyWarningList = data.data['warningDataList'].slice(5, list.length);
            return;
          }
          this.earlyWarningList = null;
          this.copyEarlyWarningList = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  moreWarning() {
    this.router.navigate(['lfs/warnings']);
  }

}
