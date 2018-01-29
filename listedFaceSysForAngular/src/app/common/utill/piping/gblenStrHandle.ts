import { Pipe, PipeTransform } from '@angular/core';

@Pipe({name: 'gblenStrHandle'})//截取字符串返回省略状态
export class GblenStrHandlePipe implements PipeTransform {
  transform(instr:string,len?:number):string{//instr 需要截取为  str + '...' 字符串， len截取长度
    if(instr == undefined || instr == null || instr == ''){
      return '';
    }else{
      instr = instr + "";
      if(!len){//默认截取剩余5个文字
        if(instr.length>5){
          instr = instr.substr(0,5)+"...";
        }
      }else{
        if(instr.length>len){
          instr = instr.substr(0,len)+"...";
        }
      }
      return instr;
    }
  }
}
