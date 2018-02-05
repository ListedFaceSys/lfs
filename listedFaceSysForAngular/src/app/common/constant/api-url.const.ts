import { HttpHeaders } from '@angular/common/http';

/**
 * 接口地址管理
 * @type {{}}
 */
export const ApiUrl = {
  api_url: 'http://localhost:4200/',
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),

  user_save: 'user/save',
  user_updatePassword: 'user/updatePassword',
  user_getAllUser: 'user/getAllUser',

  areaView_warningTop5: 'assets/dataJson/earlyWarning.json',

  regionRisk_newsCharts:'assets/dataJson/tempData.json',
  regionRisk_geographyChartsMap:'assets/mapJson/深圳各区.json',
  regionRisk_geographyAllCompanyData:'assets/dataJson/geographyAllCompanyData.json',
  regionRisk_trendChartSelectBase:'assets/dataJson/selectBase.json',
};

export const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
