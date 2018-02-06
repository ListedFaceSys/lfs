import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { BsDatepickerConfig, BsLocaleService } from 'ngx-bootstrap/datepicker';
import { listLocales } from 'ngx-bootstrap/chronos';
import { getSetGlobalLocale} from "ngx-bootstrap";

@Component({
  selector: 'app-news-track',
  templateUrl: './news-track.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class NewsTrackComponent implements OnInit {
  locale = 'zh-cn';
  locales = listLocales();

  constructor(
    private router: Router,
    private bsLocaleService: BsLocaleService) { }

  ngOnInit() {
    this.bsLocaleService.use(this.locale);
    getSetGlobalLocale('zh-cn');
    console.log(getSetGlobalLocale());
  }

  moreTracks() {
    this.router.navigate(['lfs/tracks']);
  }
}
