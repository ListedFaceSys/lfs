import { Component, OnInit } from '@angular/core';

import {NgxEchartsService} from "ngx-echarts";
import {HttpClient} from "@angular/common/http";
import {ApiUrl} from "../../common/constant/api-url.const";
import {CommonUtil} from "../../common/utill/common-util";

@Component({
  selector: 'app-trend-chart',
  templateUrl: './trend-chart.component.html',
  styleUrls: ['./trend-chart.component.css']
})
export class TrendChartComponent implements OnInit {
  dataIsNull = false; //无数据情况
  options = {};
  //eChart图数据值
  dataMap:any = {
    currentIndex:0
  };

  trendChartData = {
    dataMonth: CommonUtil.prototype.dateFormat(new Date(),"yyyy-MM"),  //日期时间
    risk1: 0,  //治理风险
    risk2: 0,  //财务风险
    risk3: 0,  //经营风险
    risk4: 0,  //市场风险
    risk5: 0,   //法律法规风险
  };
  timeList =["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"];

  constructor(
    private http: HttpClient,
    private es: NgxEchartsService) { }


  ngOnInit() {
    this.initNewsData();
    this.showChart();
  }

  //初始化当前年月数据
  initNewsData(){
    let url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_trendWarningChartSingle}`;
    console.log(url);
    this.http.get(url)
      .subscribe(geoJson => {
        if(geoJson["code"] == 0){
          this.trendChartData = geoJson["data"].monthData;
        }else if(geoJson["code"] == 1 ){

        }
      });

  }

  //加载数据展开图
  showChart(){
    this.dataIsNull = false;
    let yy = new Date().getFullYear();
    let url = `${ApiUrl.api_uri}${ApiUrl.regionRisk_trendWarningChart}`+'/'+(yy-6)+'/'+yy;
    this.http.get(url)
      .subscribe(geoJson => {
        if(geoJson["code"] == 0){
          this.dataMap = this.getTrueData(geoJson, this.timeList);
          this.getChartsData(this.dataMap);
        }else if(geoJson["code"] == 1 ){
          this.dataIsNull = true;
        }

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
              data: ['治理风险', '财务风险', '经营风险', '市场风险', '法律法规风险'],
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
              {name: '治理风险', type: 'line'},
              {name: '财务风险', type: 'line'},
              {name: '经营风险', type: 'line'},
              {name: '市场风险', type: 'line'},
              {name: '法律法规风险', type: 'line'},
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
    if(JsonData.data.warningRiskOutDataList!=undefined && JsonData.data.warningRiskOutDataList!= null){
      for(let oneConent of JsonData.data.warningRiskOutDataList){
        startEndList.push(oneConent.date);
         // risk1://治理风险   risk2://财务风险        risk3://经营风险       risk4:  //市场风险      risk5://法律法规风险
        let dataList1:any = [];  //治理风险
        let dataList2:any = [];  //财务风险
        let dataList3:any = [];  //经营风险
        let dataList4:any = [];  //市场风险
        let dataList5:any = [];  //法律法规风险
        for(let oneSingleNews of oneConent.warningRiskInfoDataList){ //添加新闻总数 ， 负面新闻数
          dataList1.push({
            name: timeList[oneSingleNews.dataMonth],
            value: oneSingleNews.risk1,
          });
          dataList2.push({
            name: timeList[oneSingleNews.dataMonth],
            value: oneSingleNews.risk2,
          });
          dataList3.push({
            name: timeList[oneSingleNews.dataMonth],
            value: oneSingleNews.risk3,
          });
          dataList4.push({
            name: timeList[oneSingleNews.dataMonth],
            value: oneSingleNews.risk5,
          });
          dataList5.push({
            name: timeList[oneSingleNews.dataMonth],
            value: oneSingleNews.risk4,
          })
        }
        optionSeries.push({  //添加所有时间轴数据
          series:[
            {data:dataList1},
            {data:dataList2},
            {data:dataList3},
            {data:dataList4},
            {data:dataList5},
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
