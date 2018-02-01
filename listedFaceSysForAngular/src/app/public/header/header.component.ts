import { Component, OnInit, OnDestroy } from '@angular/core';

import { Subscription } from 'rxjs/Subscription';

import { LoginService } from '../../common/service/login.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit, OnDestroy {
  loginFlag: boolean;
  subscription: Subscription;

  constructor(
    private loginService: LoginService) {
    this.subscription = this.loginService
      .getLogin()
      .subscribe(loginFlag => {
        this.loginFlag = loginFlag;
      });
  }

  ngOnInit() {

  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }
}
