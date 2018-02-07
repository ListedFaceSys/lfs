import { HttpHeaders } from '@angular/common/http';

/**
 * 接口地址管理
 * @type {{}}
 */
export const ApiUrl = {
  api_url: 'http://localhost:4200/',
  api_url1: 'http://localhost:8080/',
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),

  user_save: 'user/save',
  user_updatePassword: 'user/updatePassword',
  user_getAllUser: 'user/getAllUser',

  // regionRisk_warningTop: 'regionRisk/warningTop',
  regionRisk_warningTop: 'assets/dataJson/warningTop.json',
  regionRisk_lastingBondViolation: 'regionRisk/lastingBondViolation',
  regionRisk_newsCharts: 'assets/dataJson/tempData.json',
  regionRisk_geographyAllCompanyData: 'assets/dataJson/geographyAllCompanyData.json',
  regionRisk_trendChartSelectBase: 'assets/dataJson/selectBase.json',
  regionRisk_geographyChartsMap:'assets/mapJson/ShenzhenDistrict.json',
  regionRisk_geographySZCityData:'assets/dataJson/geographySZCtityData.json',  //深圳市 所有区公司数据
};

export const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
