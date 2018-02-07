import { Component, OnInit } from '@angular/core';

import { EarlyWarning } from '../../common/model/early-warning';
import { WarningRiskIn } from '../../common/model/warning-risk-in';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import { BaseApiResponseModel } from "../../common/model/base-api-response.model";

@Component({
  selector: 'app-warning-more',
  templateUrl: './warning-more.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class WarningMoreComponent implements OnInit {
  earlyWarningList: EarlyWarning[];
  userId: number;
  getYear: string;
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;

  constructor(
    private regionRiskApiService: RegionRiskApiService
  ) {
    this.userId = 1;
    this.getYear = new Date().getFullYear().toString();
    this.currentPage = 1;
    this.itemsPerPage = 3;
  }

  ngOnInit() {
    this.getWarningTop();
  }

  getWarningTop($event?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    let warningRiskIn: WarningRiskIn = {
      userId: this.userId,
      year: this.getYear,
      pageSize: this.currentPage,
      pageCount: this.itemsPerPage
    };
    this.regionRiskApiService.getWarningTop(warningRiskIn)
      .subscribe(
        (data: BaseApiResponseModel) => {
          if (data.code === '0') {
            this.earlyWarningList = data.data['warningDataList'];
            return;
          }
          this.earlyWarningList = null;
        },
        (error: any[]) => console.log('Error: ' + error),
      );
  }
}
