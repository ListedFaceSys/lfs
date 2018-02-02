import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";

import { LoginService } from '../../common/service/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  uSwipe: object;         // 整个滑块
  uSwipeContent: object;  // 右侧滑块
  uSwipeFlag: boolean;    // 控制滑块滑动
  uSLFlag: boolean;       // 是否显示左侧滑块
  signFlag: boolean;      // 显示登录或注册
  loginLink: string;
  registerLink: string;

  constructor(
    private router: Router,
    private loginService: LoginService) {
    this.uSwipeFlag = this.uSLFlag = this.signFlag = true;
  }

  ngOnInit() {
    this.loginService.sendLogin(false);
  }

  // 显示登录画面
  slogin() {
    this.loginLink = '700';
    this.registerLink = '400';
    this.router.navigate(['home/login']);
  }

  // 显示注册画面
  sRegister() {
    this.loginLink = '400';
    this.registerLink = '700';
    this.router.navigate(['home/register']);
  }

  // 是否显示登录注册画面
  showSign() {
    this.uSLFlag = this.uSwipeFlag ? false : true;
    this.changeCss(this.uSLFlag);
  }

  // 登录注册动画效果
  changeCss(flag: boolean) {
    this.uSwipeFlag = !flag;
    this.uSwipe = {
      'uSwipeLeft': !flag,
      'uSwipeRight': flag
    };
    this.uSwipeContent = {
      'uSConLeft': !flag,
    }
  }
}
