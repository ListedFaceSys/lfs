export class UtillFun {
  constructor() {
  }
  //获取随机数方法min 最小 max最大值
  GetRandomNum(Min,Max)
  {
    var Range = Max - Min;
    var Rand = Math.random();
    return(Min + Math.round(Rand * Range));
  }
  //时间格式化方法
  //  let retTime = this.mms.dateFormat(new Date(),"yyyy-MM-dd");  // "2017-01-11"
  dateFormat(inData?:Date,format?:string){
    if(inData==undefined){
      return null;
    }
    var o = {
      "M+" : inData.getMonth()+1,                 //月份
      "d+" : inData.getDate(),                    //日
      "h+" : inData.getHours(),                   //小时
      "m+" : inData.getMinutes(),                 //分
      "s+" : inData.getSeconds(),                 //秒
      "q+" : Math.floor((inData.getMonth()+3)/3), //季度
      "S"  : inData.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(format)) {
      format=format.replace(RegExp.$1, (inData.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    for(var k in o) {
      if(new RegExp("("+ k +")").test(format)){
        format = format.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
      }
    }
    return format;
  }

  //对象处理 data [Object,Object,...]  增加的属性值nodeAttribute {value:"  ",value12:"  "...}   rowIndex_my节点行次值,默认0开始
  dataHandle(data,nodeAttribute?:Object,rowIndex_my?) {
    if(data!=null&&data!=undefined&&data.length>0){
      if (rowIndex_my==undefined){
        rowIndex_my =0;
      }
      for(let row of data){
        let rowIndexObj = {
          rowIndex_my: rowIndex_my++,
        }
        row = Object.assign(row,nodeAttribute,rowIndexObj);
        if(row.children){
          if (row.children.length>0){
            let returnData = this.dataHandle(row.children,nodeAttribute,rowIndex_my);
            rowIndex_my = returnData[returnData.length-1].rowIndex_my+1;
          }else{
            return data;
          }
        }
      }
      return data;
    }else{
      return -1;
    }
  }

  // 通过字面量方式实现的函数each
  each(object, callback){
    var type = (function(){
      switch (object.constructor){
        case Object:
          return 'Object';
          // break;
        case Array:
          return 'Array';
          // break;
        case NodeList:
          return 'NodeList';
          // break;
        default:
          return 'null';
          // break;
      }
    })();
    // 为数组或类数组时, 返回: index, value
    if(type === 'Array' || type === 'NodeList'){
      // 由于存在类数组NodeList, 所以不能直接调用every方法
      [].every.call(object, function(v, i){
        return callback.call(v, i, v) === false ? false : true;
      });
    }
    // 为对象格式时,返回:key, value
    else if(type === 'Object'){
      for(var i in object){
        if(callback.call(object[i], i, object[i]) === false){
          break;
        }
      }
    }
  }

  //金额格式化   12345格式化为12,345.00
  fmoney(s, n) {
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    var l = s.split(".")[0].split("").reverse(), r = s.split(".")[1];
    let t = "";
    for (let i = 0; i < l.length; i++) {
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
    }
    let ret = t.split("").reverse().join("") + "." + r;
    ret = ret.replace('-,','-');  //将 -,123.40  转换为 -123.40
    return ret;
  }

  //取消金额格式化
  rmoney(s) {
    return parseFloat(s.replace(/[^\d\.-]/g, ""));
  }
}
