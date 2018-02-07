import { Component, OnInit } from '@angular/core';

import {NgxEchartsService} from "ngx-echarts";
import {HttpClient} from "@angular/common/http";
import {ApiUrl} from "../../common/constant/api-url.const";

@Component({
  selector: 'app-trend-chart',
  templateUrl: './trend-chart.component.html',
  styleUrls: ['./trend-chart.component.css']
})
export class TrendChartComponent implements OnInit {
  options = {};
  //eChart图数据值
  dataMap:any = {
    currentIndex:0
  };

  trendChartData = {
    dateTime: "2018-11",  //日期时间
    financialRisk: 213,  //财务风险
    governanceRisk: 12,  //治理风险
    businessRisk: 54,  //经营风险
    marketRisk: 124,  //市场风险
    lawsRegulationsRisk: 53,   //法律法规风险
  };
  timeList =["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];

  constructor(
    private http: HttpClient,
    private es: NgxEchartsService) { }



  //eChart图数据值
  ChartData:{ xAxisData:any[],seriesData:{ allNetwork:any[], negative:any[] } } = {
    xAxisData:[], //时间周期
    seriesData:{
      allNetwork:[], //全网新闻数据
      negative:[]  //负面新闻数据
    }
  };


  ngOnInit() {
    this.initNewsData();
    this.showChart();
  }

  //初始化数据
  initNewsData(){

  }

  //加载数据展开图
  showChart(){
    this.http.post(`${ApiUrl.api_uri}${ApiUrl.regionRisk_newsCharts}`,{})
      .subscribe(geoJson => {
        this.dataMap = this.getTrueData(geoJson, this.timeList);
        this.getChartsData(this.dataMap);
      });

  }


  //获取图形数据值
  getChartsData(dataMap){
    this.http.post(`${ApiUrl.api_uri}${ApiUrl.regionRisk_newsCharts}`,{})
      .subscribe(geoJson => {

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
              data: dataMap.startEndList,
              label: {
                formatter : function(s) {
                  return s;
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
              x: '30',
              data: ['财务风险', '治理风险', '经营风险', '市场风险', '法律法规风险'],
              selected: {
                '市场风险': true, '法律法规风险': true
              }
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
                'type':'category',
                'axisLabel':{'interval':0},
                boundaryGap : false,//从最左侧开始
                data:dataMap.timeList,
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
                  show: true
                }
              }
            ],
            series: [
              {name: '市场风险', type: 'line'},
              {name: '法律法规风险', type: 'line'},
              // {name: '房地产', type: 'line'},
              {name: '财务风险', type: 'line'},
              {name: '治理风险', type: 'line'},
              {name: '经营风险', type: 'line'},
            ]
          },
          options: dataMap.optionSeries
        };
      });
  }



  private getTrueData(JsonData,timeList){ //传入后台json
    let dataMap:any = {};
    let startEndList = [];//时间轴
    let optionSeries:{  //直角坐标系点坐标
      series: [
        {
          data: [
            {
              name: string,
              value: string
            }
            ]
        }
        ]
    }[] = [];

    if(JsonData.data.conent!=undefined){
      for(let oneConent of JsonData.data.conent){
        startEndList.push(oneConent.countDate);
        let dataList1:any = [];  //添加新闻总数
        let dataList2:any = [];  //负面新闻数
        let dataList3:any = [];  //负面/新闻总数占比
        for(let oneSingleNews of oneConent.singleNews){ //添加新闻总数 ， 负面新闻数
          dataList1.push({
            name: "01",
            value: oneSingleNews.newCount,
            postDt:'2018-01-01',
            ratio:oneSingleNews.ratio
          });
          dataList2.push({
            name: "01",
            value: oneSingleNews.negativeNewsCount,
            postDt:'2018-01-01',
            ratio:oneSingleNews.ratio
          });
          dataList3.push({
            name: "01",
            value: oneSingleNews.ratio,
            postDt:'2018-01-01',
            ratio:oneSingleNews.ratio
          })
        }
        optionSeries.push({  //添加所有时间轴数据
          series:[
            {data:dataList1},
            {data:dataList2},
            {data:dataList3}
          ]
        })
      }
    }else {
      let mes = JSON.stringify(JsonData);
      throw new Error("验证后台数据： "+mes);
    }
    dataMap.startEndList = startEndList;//时间轴
    dataMap.timeList = timeList; //直角坐标系x轴
    dataMap.optionSeries = optionSeries;
    return dataMap;
  }

}
