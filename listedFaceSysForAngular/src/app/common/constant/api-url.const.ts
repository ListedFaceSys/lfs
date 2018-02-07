import { HttpHeaders } from '@angular/common/http';

/**
 * 接口地址管理
 * @type {{}}
 */
export const ApiUrl = {
  api_url: 'http://localhost:4200/',
  api_uri: 'http://localhost:8080/',
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),

  user_save: 'user/save',
  user_updatePassword: 'user/updatePassword',
  user_getAllUser: 'user/getAllUser',

  regionRisk_warningTop: 'regionRisk/warningTop',
  regionRisk_lastingBondViolation: 'regionRisk/lastingBondViolation',
  regionRisk_newsCharts: 'regionRisk/newsChart',
  regionRisk_newsChartByDate: 'regionRisk/newsChartByDate',
  regionRisk_geographyAllCompanyData: 'assets/dataJson/geographyAllCompanyData.json',

  regionRisk_trendChartSelectBase: 'assets/dataJson/selectBase.json',

  regionRisk_trendWarningChartSingle: 'regionRisk/warningChartSingle',
  regionRisk_trendWarningChart:'regionRisk/warningChart',

  regionRisk_geographyChartsMap:'assets/mapJson/ShenzhenDistrict.json',
  regionRisk_geographySZCityData:'assets/dataJson/geographySZCtityData.json',  //深圳市 所有区公司数据
};

export const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
