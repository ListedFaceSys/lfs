import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  uSwipe: object;
  uSwipeFlag: boolean;

  constructor() {
    this.uSwipeFlag = true;
  }

  ngOnInit() {
  }

  showLogin() {
    if (this.uSwipeFlag) {
      this.uSwipeFlag = false;
      this.uSwipe = {
        'uSwipeLeft': true,
        'uSwipeRight': false
      };
    } else {
      this.uSwipeFlag = true;
      this.uSwipe = {
        'uSwipeLeft': false,
        'uSwipeRight': true
      };
    }
  }
}
