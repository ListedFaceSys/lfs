import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

@Component({
  selector: 'app-news-track',
  templateUrl: './news-track.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class NewsTrackComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  moreTracks() {
    this.router.navigate(['lfs/tracks']);
  }
}
