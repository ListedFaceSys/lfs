import {Component, OnInit, DoCheck } from '@angular/core';
import { Router } from '@angular/router';

import { CommonUtil } from '../../common/utill/common-util';

import { BsLocaleService } from "ngx-bootstrap/datepicker";
import { RegionRiskApiService } from '../../common/api/region-risk-api.service';

import { CompanyNews } from '../../common/model/company-news';
import { NegativeNewsIn } from '../../common/model/negative-news-in';

@Component({
  selector: 'app-news-track',
  templateUrl: './news-track.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class NewsTrackComponent implements OnInit, DoCheck {
  newsTrack: CompanyNews;
  range: Date;
  oldRange: Date;

  constructor(
    private router: Router,
    private commonUtil: CommonUtil,
    private bsLocaleService: BsLocaleService,
    private regionRiskApiService: RegionRiskApiService) { }

  ngOnInit() {
    this.bsLocaleService.use('zh-cn');
    this.getNewsTrack(1, 10);
  }

  ngDoCheck() {
    if (this.range !== this.oldRange) {
      let startDate = this.commonUtil.dateFormat(this.range[0], 'yyyy-MM-dd');
      let endDate = this.commonUtil.dateFormat(this.range[1], 'yyyy-MM-dd');
      console.log(startDate, endDate);
      this.getNewsTrack(1, 10, startDate, endDate);
      this.oldRange = this.range;
    }
  }

  getNewsTrack(page: number, pageSize: number, startDate?: string, endDate?: string) {
    let news: NegativeNewsIn;
    news = {
      page: page,
      pageSize: pageSize,
      startDate: startDate,
      endDate: endDate
    };
    this.regionRiskApiService.getNewsTrack(news)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.newsTrack = data.data['content'];
            return;
          }
          this.newsTrack = null;
        },
        error => {
          console.log(error);
        }
      );
  }

  moreTracks() {
    this.router.navigate(['lfs/tracks']);
  }
}
