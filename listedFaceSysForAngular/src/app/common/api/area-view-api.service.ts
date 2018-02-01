import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';

@Injectable()
export class AreaViewApiService {

  constructor(private http: HttpClient) { }

  // 监测预警风险TOP5
  getWarningTop5(id: number): Observable<BaseApiResponseModel> {
    // const url = `${ApiUrl}${ApiUrl.areaView_warningTop5}/${id}`;
    return this.http.get<BaseApiResponseModel>(ApiUrl.areaView_warningTop5);
  }
}
