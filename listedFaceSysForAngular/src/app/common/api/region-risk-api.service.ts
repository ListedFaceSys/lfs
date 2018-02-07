import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import { NegativeNews } from '../model/negative-news';

@Injectable()
export class RegionRiskApiService {

  constructor(
    private http: HttpClient
  ) { }

  // 监测预警风险TOP
  getWarningTop(userId: number, year: String): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_warningTop}/${userId}/${year}`;
    return this.http.get<BaseApiResponseModel>(url);
  }

  // 负面新闻跟踪
  getNewsTrack(negativeNewsInData: NegativeNews): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_lastingBondViolation}`;
    return this.http.post<BaseApiResponseModel>(url, negativeNewsInData, HttpOptions);
  }
}
