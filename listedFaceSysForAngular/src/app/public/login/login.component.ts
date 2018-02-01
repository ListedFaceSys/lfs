import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserInfo } from "../../common/model/entity";

import { LoginService } from '../../common/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit{
  userInfo: UserInfo = new UserInfo(); //用户信息
  message: string;  //消息返回

  constructor(
    private router: Router,
    private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.loginService.sendLogin(false);
  }


  //提交登录
  submitUp(info?:UserInfo){
    if(info.name == ''|| info.name==undefined){
      this.message = '登录名不能为空！';
      return;
    }
    if(info.pwd == ''|| info.pwd==undefined){
      this.message = '密码不能为空！';
      return;
    }
    this.message = '';

    this.router.navigate(['menu']);
  }
}



