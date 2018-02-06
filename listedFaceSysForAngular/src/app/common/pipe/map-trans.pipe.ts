import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'mapTrans'
})
export class MapTransPipe implements PipeTransform{

  transform(value: any): any {

    const array = new Array<any>();
    let map: any;
    value = this.transMap(value);

    if (value != null && value.size > 0) {
      value.forEach((value, key) => {
        map = new Map();
        map.key = key;
        map.value = value;
        array.push(map);
        console.log(map.key + ' : ' + map.value);
      });
    }
    return array;
  }

  transMap(value: any): any {
    if (!(value instanceof Map)) {
      let strMap = new Map();
      for (let k of Object.keys(value)) {
        strMap.set(k, value[k]);
      }
      value = strMap;
    }
    return value;
  }
}
