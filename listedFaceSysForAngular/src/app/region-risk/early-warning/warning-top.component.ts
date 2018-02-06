import { Component, Input } from '@angular/core';

import { EarlyWarning } from '../../common/model/early-warning';

@Component({
  selector: 'app-warning-top',
  templateUrl: './warning-top.component.html',
  styleUrls: ['../region-risk.component.css']
})
export class WarningTopComponent {
  @Input() earlyWarningList: EarlyWarning[];
  @Input() num: number;
}
