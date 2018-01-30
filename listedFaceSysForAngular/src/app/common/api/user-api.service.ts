import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { User } from '../model/user';
import { ApiUrl } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class UserApiService {

  private userUrl = ApiUrl.api_url;

  constructor(private http: HttpClient) { }

  getUsers(): Observable<BaseApiResponseModel> {
    const url = `${this.userUrl}${ApiUrl.user_getAllUser}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  saveUser(user: User): Observable<User> {
    const url = `${this.userUrl}${ApiUrl.user_save}`;
    return this.http.post<User>(url, user, httpOptions);
  }
}
