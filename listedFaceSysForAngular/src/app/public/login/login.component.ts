import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { UserInfo } from "../../common/model/entity";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit{
  userInfo:UserInfo = new UserInfo(); //用户信息
  message:string; //消息返回
  constructor(public router:Router){
    // this.userInfo = new UserInfo();//创建用户
  }

  ngOnInit(): void {

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

    // this.router.navigate(["allbox/allsearch",1])
    this.router.navigate(['menu',1]);
  }
}



