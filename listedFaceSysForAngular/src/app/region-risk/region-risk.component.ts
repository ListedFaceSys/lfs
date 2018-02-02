import { Component, OnInit } from '@angular/core';
import {LoginService} from "../common/service/login.service";

@Component({
  selector: 'app-region-risk',
  templateUrl: './region-risk.component.html',
  styleUrls: ['./region-risk.component.css']
})
export class RegionRiskComponent implements OnInit {

  constructor(
    private loginService: LoginService
  ) { }

  ngOnInit() {
    this.loginService.sendLogin(true);
  }

}
