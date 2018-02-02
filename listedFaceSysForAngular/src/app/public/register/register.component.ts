import { Component, OnInit } from '@angular/core';
import { UserInfo } from '../../common/model/entity';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['../public.component.css']
})
export class RegisterComponent implements OnInit {
  userInfo: UserInfo = new UserInfo();

  constructor() { }

  ngOnInit() {
  }

}
