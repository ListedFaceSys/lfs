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

  regionRisk_newsCharts:'assets/dataJson/tempData.json', //上市公司-热点新闻Charts图接口
  regionRisk_geographyChartsMap:'assets/mapJson/ShenzhenDistrict.json',
  regionRisk_geographyAllCompanyData:'assets/dataJson/geographyAllCompanyData.json',
  regionRisk_geographySZCityData:'assets/dataJson/geographySZCtityData.json',  //深圳市 所有区公司数据
  regionRisk_trendChartSelectBase:'assets/dataJson/selectBase.json',
};

export const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
