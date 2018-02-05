import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-track-more',
  templateUrl: './track-more.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class TrackMoreComponent implements OnInit {

  totalItems = 64;
  currentPage = 4;
  smallnumPages = 0;

  constructor() { }

  ngOnInit() {
  }

  setPage(pageNo: number): void {
    this.currentPage = pageNo;
  }

  pageChanged(event: any): void {
    console.log('Page changed to: ' + event.page);
    console.log('Number items per page: ' + event.itemsPerPage);
  }

}
