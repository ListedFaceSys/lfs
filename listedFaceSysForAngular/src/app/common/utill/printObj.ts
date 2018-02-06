import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'printObj'})//截取字符串返回省略状态
export class PrintObjPipe implements PipeTransform {
  transform(obj:any){
    console.log(obj);
  }
}
