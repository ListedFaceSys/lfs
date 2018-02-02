import { Component, OnInit } from '@angular/core';

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

  constructor() {
    this.uSwipeFlag = this.uSLFlag = this.signFlag = true;
  }

  ngOnInit() {
  }

  // 是否显示登录注册画面
  showLogin() {
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
