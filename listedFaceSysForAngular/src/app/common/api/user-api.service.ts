import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { User } from '../model/user';
import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';

@Injectable()
export class UserApiService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_url}${ApiUrl.user_getAllUser}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  saveUser(user: User): Observable<User> {
    const url = `${ApiUrl.api_url}${ApiUrl.user_save}`;
    return this.http.post<User>(url, user, HttpOptions);
  }
}
