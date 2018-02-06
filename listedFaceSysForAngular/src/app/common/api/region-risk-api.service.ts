import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';

@Injectable()
export class RegionRiskApiService {

  constructor(
    private http: HttpClient
  ) { }


  // 监测预警风险TOP
  getWarningTop(userId: number, year: String): Observable<BaseApiResponseModel> {
    // const url = `${ApiUrl}${ApiUrl.regionRisk_warningTop}/${userId}/${year}`;
    return this.http.get<BaseApiResponseModel>(ApiUrl.regionRisk_warningTop);
  }
}
