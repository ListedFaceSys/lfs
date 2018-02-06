import { Component, OnInit } from '@angular/core';

import { Router } from '@angular/router';

@Component({
  selector: 'app-early-warning',
  templateUrl: './early-warning.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class EarlyWarningComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  moreWarning() {
    this.router.navigate(['lfs/warnings']);
  }

}
