import { Component, OnInit } from '@angular/core';

import { RegionRiskApiService } from '../../common/api/region-risk-api.service';

import { CompanyNews } from '../../common/model/company-news';
import { NegativeNewsIn } from '../../common/model/negative-news-in';

@Component({
  selector: 'app-track-more',
  templateUrl: './track-more.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class TrackMoreComponent implements OnInit {
  newsTrack: CompanyNews;
  totalItems: number;
  currentPage: number;
  itemsPerPage: number;

  constructor(
    private regionRiskApiService: RegionRiskApiService
  ) {
    this.currentPage = 1;
    this.itemsPerPage = 10;
  }

  ngOnInit() {
    this.getNewsTrack();
  }

  getNewsTrack($event?) {
    if ($event) {
      this.currentPage = $event.page;
    }
    let news: NegativeNewsIn = {
      page: this.currentPage,
      pageSize: this.itemsPerPage,
    };
    this.regionRiskApiService.getNewsTrack(news)
      .subscribe(
        data => {
          if (data.code === '0') {
            this.totalItems = data.count;
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

}
