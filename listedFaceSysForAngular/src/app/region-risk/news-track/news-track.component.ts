import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { NegativeNews } from '../../common/model/negative-news';

import { BsLocaleService } from "ngx-bootstrap/datepicker";
import { RegionRiskApiService } from '../../common/api/region-risk-api.service';
import {CompanyNews} from "../../common/model/company-news";

@Component({
  selector: 'app-news-track',
  templateUrl: './news-track.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class NewsTrackComponent implements OnInit {
  newsTrack: CompanyNews;

  constructor(
    private router: Router,
    private bsLocaleService: BsLocaleService,
    private regionRiskApiService: RegionRiskApiService) { }

  ngOnInit() {
    this.bsLocaleService.use('zh-cn');
    this.getNewsTrack();
  }

  getNewsTrack() {
    let news: NegativeNews;
    news = {
      page: 1,
      pageSize: 10,
    };
    this.regionRiskApiService.getNewsTrack(news)
      .subscribe(
        data => {
          this.newsTrack = data.data['content'];
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
