export class EarlyWarning {
  companyId: string;
  companyNm: string;
  noticeDt: string;
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
  supervisor: string;
  content: Map<string, object>;
  typeMap: Map<string, Array<string>>
}
