import { Component, OnInit } from '@angular/core';

import { User } from '../../common/model/user';
import { BaseApiResponseModel } from '../../common/model/base-api-response.model';

import { UserApiService } from '../../common/api/user-api.service';

@Component({
  selector: 'app-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.css']
})
export class BaseComponent implements OnInit {
  users: User[];
  resData: BaseApiResponseModel;

  constructor(private userApiService: UserApiService) { }

  ngOnInit() {
    this.getUsers();
  }

  getUsers(): void {
    this.userApiService.getUsers()
      .subscribe(data => {
          console.log(data);
          this.resData = data;
          console.log(this.resData);
          this.users = this.resData.data['userList'];
      });
  }

  add(userName: string): void {
    userName = userName.trim();
    if (!userName) { return; }
    this.userApiService.saveUser({ userName } as User)
      .subscribe(user => {
        this.users.push(user);
      });
  }
}
