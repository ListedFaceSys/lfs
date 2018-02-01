import { Component, OnInit } from '@angular/core';

import { LoginService } from '../common/service/login.service';

@Component({
  selector: 'app-area-view',
  templateUrl: './area-view.component.html',
  styleUrls: ['./area-view.component.css']
})
export class AreaViewComponent implements OnInit {

  constructor(
    private loginService: LoginService
  ) { }

  ngOnInit() {
    this.loginService.sendLogin(true);
  }

}
