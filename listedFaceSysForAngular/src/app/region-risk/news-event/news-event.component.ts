import { Component, OnInit,ViewChild } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NgxEchartsService} from "ngx-echarts";

@Component({
  selector: 'app-news-event',
  templateUrl: './news-event.component.html',
  styleUrls: ['./news-event.component.css']
})
export class NewsEventComponent implements OnInit {
  @ViewChild("newsEcharts")newsEcharts;

  options:any = {};
  selectOptions:{}[] = []; //下拉选项集
  selectChecked:number=1;//选中值
  getTimeNewsData:{dataName:string, allNetworkNum:number,negativeNum:number,negativeProportion:string }={
    dataName:"最近一周汇总",
    allNetworkNum:124,
    negativeNum:53,
    negativeProportion:"32%"
  };

  //eChart图数据值
  dataMap:any = {
    currentIndex:0
  };

  //eChart图数据值
  newsEventChartData:{ xAxisData:any[],seriesData:{ allNetwork:any[], negative:any[] } } = {
    xAxisData:[], //时间周期
    seriesData:{
      allNetwork:[], //全网新闻数据
      negative:[]  //负面新闻数据
    }
  };

  constructor(
    private http: HttpClient,
    private es: NgxEchartsService) { }

  ngOnInit() {
    this.initNewsData();
    this.showChart();

  }

  //初始化数据
  initNewsData(){
    this.http.get('assets/dataJson/selectBase.json')
      .subscribe(geoJson => {
        this.selectOptions= geoJson["data"].newsEventSelects;
      })
  }


  //获取图形数据值
  getChartsData(dataMap){
    this.http.get('assets/dataJson/newsEventChartData.json')
      .subscribe(geoJson => {
        this.newsEventChartData.xAxisData = geoJson["data"].xAxisData;
        this.newsEventChartData.seriesData = geoJson["data"].seriesData;
        //eCharts配置
        this.options = {
          baseOption: {
            timeline: {
              // y: 0,
              axisType: 'category',
              // realtime: false,
              // loop: false,
              autoPlay: false,//自动播放
              // currentIndex: 2,
              playInterval: 1000,
              // controlStyle: { //播放按钮
              //     position: 'left'
              // },
              checkpointStyle:{ //当前点样式
                color : 'auto',
                borderColor:'auto',
              },
              data: this.dataMap.startEndList,
              currentIndex:this.dataMap.currentIndex, //默认选中值
              label: {
                formatter : function(s) {
                  return (new Date(s)).getFullYear();
                }
              },
              controlStyle:{
                show:false
              }
            },
            title: {
              show:false
            },
            tooltip: {
              trigger: 'axis',

            },
            legend: {
              right: '30',
              data: ['新闻总数', '负面新闻'],
            },
            calculable : true,
            grid: {
              top: 80,
              bottom: 100,
              tooltip: {
                trigger: 'axis',
                axisPointer: {
                  type: 'shadow',
                  label: {
                    show: true,
                    formatter: function (params) {
                      return params.value.replace('\n', '');
                    }
                  }
                }
              }
            },
            xAxis: [
              {
                type:'category',
                axisLabel:{'interval':0},
                boundaryGap : false,//从最左侧开始
                data:this.dataMap.timeList,
                splitLine: {show: false},
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                  type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
                }
              }
            ],
            yAxis: [
              {
                type: 'value',
                // name: '市场风险（亿元）',
                splitArea: {//块颜色分割
                  show: false
                }
              }
            ],
            series: [
              {name: '新闻总数', type: 'line'},
              {name: '负面新闻', type: 'line'},
              {name: '经营风险', type: 'line'},
            ]
          },
          options: [
            {
              series: [
                {data: dataMap.dataPI['2002']},
                {data: dataMap.dataSI['2002']},

              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2003']},
                {data: dataMap.dataSI['2003']},
              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2004']},
                {data: dataMap.dataSI['2004']},

              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2005']},
                {data: dataMap.dataSI['2005']},

              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2006']},
                {data: dataMap.dataSI['2006']},

              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2007']},
                {data: dataMap.dataSI['2007']},

              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2008']},
                {data: dataMap.dataSI['2008']},

              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2009']},
                {data: dataMap.dataSI['2009']},

              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2010']},
                {data: dataMap.dataSI['2010']},

              ]
            },
            {
              series : [
                {data: dataMap.dataPI['2011']},
                {data: dataMap.dataSI['2011']},

              ]
            }
          ]
        };
      });
  }

  //切换选中时间
  getSelectChange(value){
    console.log(value);
    let inBody = {
      selectKey:value
    };
    this.http.get('assets/dataJson/selectBase.json')
      .subscribe(geoJson => {
        // this.selectOptions= geoJson["data"].newsEventSelects;
      })
  }

  timeList =["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
  //加载数据展开图
  showChart(){
    let startEndList = [];
    for ( let year =2002 ; year <= 2011; year++) {
      startEndList.push(year);
    }
    this.dataMap.startEndList = startEndList;
    this.dataMap.timeList = this.timeList;
    this.initDataMap(this.dataMap.timeList,this.dataMap.startEndList);
    this.getChartsData(this.dataMap);
  }

  //初始化图数据
  private initDataMap(timeList,startEndList){
    this.dataMap.dataPI = this.dataFormatter({
      //max : 4000,
      2011:[136.27,159.72,2905.73,641.42,1306.3,1915.57,1277.44,1701.5,124.94,3064.78,1583.04,2015.31,1612.24,1391.07,3973.85,3512.24,2569.3,2768.03,2665.2,2047.23,659.23,844.52,2983.51,726.22,1411.01,74.47,1220.9,678.75,155.08,184.14,1139.03],
      2010:[124.36,145.58,2562.81,554.48,1095.28,1631.08,1050.15,1302.9,114.15,2540.1,1360.56,1729.02,1363.67,1206.98,3588.28,3258.09,2147,2325.5,2286.98,1675.06,539.83,685.38,2482.89,625.03,1108.38,68.72,988.45,599.28,134.92,159.29,1078.63],
      2009:[118.29,128.85,2207.34,477.59,929.6,1414.9,980.57,1154.33,113.82,2261.86,1163.08,1495.45,1182.74,1098.66,3226.64,2769.05,1795.9,1969.69,2010.27,1458.49,462.19,606.8,2240.61,550.27,1067.6,63.88,789.64,497.05,107.4,127.25,759.74],
      2008:[112.83,122.58,2034.59,313.58,907.95,1302.02,916.72,1088.94,111.8,2100.11,1095.96,1418.09,1158.17,1060.38,3002.65,2658.78,1780,1892.4,1973.05,1453.75,436.04,575.4,2216.15,539.19,1020.56,60.62,753.72,462.27,105.57,118.94,691.07],
      2007:[101.26,110.19,1804.72,311.97,762.1,1133.42,783.8,915.38,101.84,1816.31,986.02,1200.18,1002.11,905.77,2509.14,2217.66,1378,1626.48,1695.57,1241.35,361.07,482.39,2032,446.38,837.35,54.89,592.63,387.55,83.41,97.89,628.72],
      2006:[88.8,103.35,1461.81,276.77,634.94,939.43,672.76,750.14,93.81,1545.05,925.1,1011.03,865.98,786.14,2138.9,1916.74,1140.41,1272.2,1532.17,1032.47,323.48,386.38,1595.48,382.06,724.4,50.9,484.81,334,67.55,79.54,527.8],
      2005:[88.68,112.38,1400,262.42,589.56,882.41,625.61,684.6,90.26,1461.51,892.83,966.5,827.36,727.37,1963.51,1892.01,1082.13,1100.65,1428.27,912.5,300.75,463.4,1481.14,368.94,661.69,48.04,435.77,308.06,65.34,72.07,509.99],
      2004:[87.36,105.28,1370.43,276.3,522.8,798.43,568.69,605.79,83.45,1367.58,814.1,950.5,786.84,664.5,1778.45,1649.29,1020.09,1022.45,1248.59,817.88,278.76,428.05,1379.93,334.5,607.75,44.3,387.88,286.78,60.7,65.33,461.26],
      2003:[84.11,89.91,1064.05,215.19,420.1,615.8,488.23,504.8,81.02,1162.45,717.85,749.4,692.94,560,1480.67,1198.7,798.35,886.47,1072.91,658.78,244.29,339.06,1128.61,298.69,494.6,40.7,302.66,237.91,48.47,55.63,412.9],
      2002:[82.44,84.21,956.84,197.8,374.69,590.2,446.17,474.2,79.68,1110.44,685.2,783.66,664.78,535.98,1390,1288.36,707,847.25,1015.08,601.99,222.89,317.87,1047.95,281.1,463.44,39.75,282.21,215.51,47.31,52.95,305]
    },startEndList,timeList);
    this.dataMap.dataSI = this.dataFormatter({
      //max : 26600,
      2011:[3752.48,5928.32,13126.86,6635.26,8037.69,12152.15,5611.48,5962.41,7927.89,25203.28,16555.58,8309.38,9069.2,6390.55,24017.11,15427.08,9815.94,9361.99,26447.38,5675.32,714.5,5543.04,11029.13,2194.33,3780.32,208.79,6935.59,2377.83,975.18,1056.15,3225.9],
      2010:[3388.38,4840.23,10707.68,5234,6367.69,9976.82,4506.31,5025.15,7218.32,21753.93,14297.93,6436.62,7522.83,5122.88,21238.49,13226.38,7767.24,7343.19,23014.53,4511.68,571,4359.12,8672.18,1800.06,3223.49,163.92,5446.1,1984.97,744.63,827.91,2592.15],
      2009:[2855.55,3987.84,8959.83,3993.8,5114,7906.34,3541.92,4060.72,6001.78,18566.37,11908.49,4905.22,6005.3,3919.45,18901.83,11010.5,6038.08,5687.19,19419.7,3381.54,443.43,3448.77,6711.87,1476.62,2582.53,136.63,4236.42,1527.24,575.33,662.32,1929.59],
      2008:[2626.41,3709.78,8701.34,4242.36,4376.19,7158.84,3097.12,4319.75,6085.84,16993.34,11567.42,4198.93,5318.44,3554.81,17571.98,10259.99,5082.07,5028.93,18502.2,3037.74,423.55,3057.78,5823.39,1370.03,2452.75,115.56,3861.12,1470.34,557.12,609.98,2070.76],
      2007:[2509.4,2892.53,7201.88,3454.49,3193.67,5544.14,2475.45,3695.58,5571.06,14471.26,10154.25,3370.96,4476.42,2975.53,14647.53,8282.83,4143.06,3977.72,16004.61,2425.29,364.26,2368.53,4648.79,1124.79,2038.39,98.48,2986.46,1279.32,419.03,455.04,1647.55],
      2006:[2191.43,2457.08,6110.43,2755.66,2374.96,4566.83,1915.29,3365.31,4969.95,12282.89,8511.51,2711.18,3695.04,2419.74,12574.03,6724.61,3365.08,3187.05,13469.77,1878.56,308.62,1871.65,3775.14,967.54,1705.83,80.1,2452.44,1043.19,331.91,351.58,1459.3],
      2005:[2026.51,2135.07,5271.57,2357.04,1773.21,3869.4,1580.83,2971.68,4381.2,10524.96,7164.75,2245.9,3175.92,1917.47,10478.62,5514.14,2852.12,2612.57,11356.6,1510.68,240.83,1564,3067.23,821.16,1426.42,63.52,1951.36,838.56,264.61,281.05,1164.79],
      2004:[1853.58,1685.93,4301.73,1919.4,1248.27,3061.62,1329.68,2487.04,3892.12,8437.99,6250.38,1844.9,2770.49,1566.4,8478.69,4182.1,2320.6,2190.54,9280.73,1253.7,205.6,1376.91,2489.4,681.5,1281.63,52.74,1553.1,713.3,211.7,244.05,914.47],
      2003:[1487.15,1337.31,3417.56,1463.38,967.49,2898.89,1098.37,2084.7,3209.02,6787.11,5096.38,1535.29,2340.82,1204.33,6485.05,3310.14,1956.02,1777.74,7592.78,984.08,175.82,1135.31,2014.8,569.37,1047.66,47.64,1221.17,572.02,171.92,194.27,719.54],
      2002:[1249.99,1069.08,2911.69,1134.31,754.78,2609.85,943.49,1843.6,2622.45,5604.49,4090.48,1337.04,2036.97,941.77,5184.98,2768.75,1709.89,1523.5,6143.4,846.89,148.88,958.87,1733.38,481.96,934.88,32.72,1007.56,501.69,144.51,153.06,603.15]
    },startEndList,timeList);
  }

  //格式化图数据
  private dataFormatter(obj,startEndList:Array<any>,timeList) {
    var temp;
    for (let j = 0 ; j < startEndList.length; j++) {
      var max = 0;
      var sum = 0;
      temp = obj[startEndList[j]];
      for (var i = 0, l = temp.length; i < l; i++) {
        max = Math.max(max, temp[i]);
        sum += temp[i];
        obj[startEndList[j]][i] = {
          name : timeList[i],
          value : temp[i]
        }
      }
      obj[startEndList[j] + 'max'] = Math.floor(max / 100) * 100;
      obj[startEndList[j] + 'sum'] = sum;
    }
    return obj;
  }

  //获取时间轴点击事件
  getClickTimeLine(e?){
    if(e.componentType =="timeline"){ //点击时间轴方法
      console.log(e.dataIndex);
      this.timeList = ["二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];
      this.dataMap.timeList = this.timeList;
      this.dataMap.currentIndex = e.dataIndex;
      this.showChart();
    }

  }
}





//初期配置
/*
this.options = {
  title : {
    show:false,
  },
  tooltip : {
    trigger: 'axis'
  },
  legend: {
    right:20,
    data:['全网新闻总数','负面新闻总数']
  },
  toolbox: {
    show : false,
  },
  calculable : true,
  xAxis : [
    {
      type : 'category',
      boundaryGap : false,
      data :  this.newsEventChartData.xAxisData//[ "08-01", "08-02", "08-03", "08-04", "08-05", "08-06", "08-07"]//['周一','周二','周三','周四','周五','周六','周日']
    }
  ],
  yAxis : [
    {
      type : 'value',
      axisLabel : {
        formatter: '{value} '
      }
    }
  ],
  series : [
    {
      name:'全网新闻总数',
      type:'line',
      data: this.newsEventChartData.seriesData.allNetwork//[0,2, 3, 5, 6, 4, 8, 9, 4, 3, 1, 6, 7],
    },
    {
      name:'负面新闻总数',
      type:'line',
      data:this.newsEventChartData.seriesData.negative //[0,0, 0, 11, 3, 0, 8, 7, 4, 2, 1, 1],
      /!*markPoint : {
          data : [
              {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
          ]
      },
      markLine : {
          data : [
              {type : 'average', name : '平均值'}
          ]
      }*!/
    }
  ]
};*/
