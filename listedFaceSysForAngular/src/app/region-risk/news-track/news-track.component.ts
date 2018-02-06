import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { BsLocaleService } from "ngx-bootstrap/datepicker";

@Component({
  selector: 'app-news-track',
  templateUrl: './news-track.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class NewsTrackComponent implements OnInit {

  constructor(
    private router: Router,
    private bsLocaleService: BsLocaleService) { }

  ngOnInit() {
    this.bsLocaleService.use('zh-cn');
  }

  moreTracks() {
    this.router.navigate(['lfs/tracks']);
  }
}
