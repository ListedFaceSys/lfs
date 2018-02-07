import { Pipe, PipeTransform } from '@angular/core';
import { BaseMap } from '../model/base-map';

@Pipe({
  name: 'mapTrans'
})
export class MapTransPipe implements PipeTransform{

  transform(data: Object): Array<Map<string, any>> {
    let array: Array<any> = new Array<Map<string, any>>();
    let baseMap: BaseMap;
    let map = new Map<string, any>();
    for (let key of Object.keys(data)) {
      map.set(key, data[key]);
    }
    if (map !== null && map.size > 0) {
      map.forEach((value, key) => {
        baseMap = new BaseMap();

        baseMap.value = value;
        baseMap.key = key;
        array.push(baseMap);
      });

    }
    return array;
  }

}
