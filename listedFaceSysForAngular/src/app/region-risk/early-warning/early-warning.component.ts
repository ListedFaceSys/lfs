import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { EarlyWarning } from '../../common/model/early-warning';
import { WarningRiskIn } from '../../common/model/warning-risk-in';

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
  userId: number;
  getYear: string;

  constructor(
    private router: Router,
    private regionRiskApiService: RegionRiskApiService
  ) {
    this.userId = 1;
    this.getYear = new Date().getFullYear().toString();
  }

  ngOnInit() {
    this.getWarningTop();
  }

  // 获取监测预警信息
  getWarningTop() {
    let warningRiskIn: WarningRiskIn = {
      userId: this.userId,
      year: this.getYear
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
            this.earlyWarningList = data.data['warningDataList'].slice(0, 5);
            this.copyEarlyWarningList = data.data['warningDataList'].slice(5, list.length);
            return;
          }
          this.earlyWarningList = null;
          this.copyEarlyWarningList = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }

  // 查看更多监测预警
  moreWarning() {
    this.router.navigate(['lfs/warnings']);
  }

}
