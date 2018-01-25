import { Directive, Input, Output, EventEmitter, OnInit} from "@angular/core";

//创建指令
@Directive({
  selector : '[isLast]'
})

export class IsLastDirective implements OnInit{
  @Input() isLast : boolean;
  @Output() lastDone : EventEmitter<boolean> = new EventEmitter<boolean>();

  ngOnInit(): void {
    //isLast成功启动lastDone内方法
    if (this.isLast) {
      this.lastDone.emit(true);
    }
  }
}

// <label *ngFor="let isState of database.isNoState; let last = last" [isLast]="last" (lastDone)="printMsg()">
// <input type="radio" name="new-event-radio-1" class="flat-red iCheckCSS"  [value]="isState.dataKey"  [(ngModel)]="checkedState" >
//   <span>{{isState.dataValue}}</span>
// </label>


// //ngFor循环结束后触发指令方法
// printMsg(){
//   console.log("printMsg方法");
// }
