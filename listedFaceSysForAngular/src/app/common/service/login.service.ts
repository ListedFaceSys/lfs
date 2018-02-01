import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';


@Injectable()
export class LoginService {
  private loginSubject = new Subject<boolean>();

  constructor() { }

  sendLogin(loginFlag: boolean) {
    this.loginSubject.next(loginFlag);
  }

  getLogin(): Observable<boolean> {
    return this.loginSubject.asObservable();
  }
}
