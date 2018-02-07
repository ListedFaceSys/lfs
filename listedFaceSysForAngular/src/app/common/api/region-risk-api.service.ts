import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';

import { ApiUrl } from '../constant/api-url.const';
import { HttpOptions } from '../constant/api-url.const';
import { BaseApiResponseModel } from '../model/base-api-response.model';
import { NegativeNewsIn } from '../model/negative-news-in';
import { WarningRiskIn } from '../model/warning-risk-in';

@Injectable()
export class RegionRiskApiService {

  constructor(
    private http: HttpClient
  ) { }

  // 监测预警风险TOP
  getWarningTop(warningRiskIn: WarningRiskIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_warningTop}`;
    return this.http.post<BaseApiResponseModel>(url, warningRiskIn, HttpOptions);
  }

  // 负面新闻跟踪
  getNewsTrack(negativeNewsIn: NegativeNewsIn): Observable<BaseApiResponseModel> {
    const url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_lastingBondViolation}`;
    return this.http.post<BaseApiResponseModel>(url, negativeNewsIn, HttpOptions);
  }
}
