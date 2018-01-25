import {Component, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'business',
  templateUrl: './business.html',
  styleUrls: ['./business.css'],
})
export class Business implements OnInit {
  constructor(private routeInfo:ActivatedRoute) { }
  ngOnInit() {

  }

}
