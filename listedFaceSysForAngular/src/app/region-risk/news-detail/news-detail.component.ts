import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-news-detail',
  templateUrl: './news-detail.component.html',
  styleUrls: ['./news-detail.component.css']
})
export class NewsDetailComponent implements OnInit {

  max: number = 10;
  rate: number = 7;
  isReadonly: boolean = true;

  checkModel: any = { left: false, middle: true, right: false };

  constructor() { }

  ngOnInit() {
  }

}
