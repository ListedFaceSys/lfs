import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserInfo } from "../../common/model/entity";

import { LoginService } from '../../common/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['../public.component.css']
})
export class LoginComponent implements OnInit{
  userInfo: UserInfo = new UserInfo(); //用户信息

  constructor(
    private router: Router,
    private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.loginService.sendLogin(false);
  }


  // 登录
  login(info?: UserInfo) {
    this.router.navigate(['lfs']);
  }
}



