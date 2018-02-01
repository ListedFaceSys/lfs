import { HttpHeaders } from '@angular/common/http';

/**
 * 接口地址管理
 * @type {{}}
 */
export const ApiUrl = {
  api_url: 'http://localhost:8080/',
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),

  user_save: 'user/save',
  user_updatePassword: 'user/updatePassword',
  user_getAllUser: 'user/getAllUser',

  areaView_warningTop5: 'assets/dataJson/earlyWarning.json'
};

export const HttpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
