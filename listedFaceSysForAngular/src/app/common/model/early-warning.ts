export class EarlyWarning {
  companyId: string;
  companyNm: string;
  noticeDt: string;
  content: [
    {
      title: string;
      typeName: object
    }

    ];
  type: string;
  securityCd: string;
  industryNm: string;
  warnCount: number;
  lowWarnCount: number;
  midWarnCount: number;
  highWarnCount: number;
  thisWarnCount: number;
  focusCompanyId: string;
  focusCompanyNm: string;
  isFocused: boolean;
  typeMap: {}
}
