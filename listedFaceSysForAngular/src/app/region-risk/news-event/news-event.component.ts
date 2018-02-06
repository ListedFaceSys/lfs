import { Component, OnInit,ViewChild } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NgxEchartsService} from "ngx-echarts";
import {ApiUrl} from "../../common/constant/api-url.const";

@Component({
  selector: 'app-news-event',
  templateUrl: './news-event.component.html',
  styleUrls: ['./news-event.component.css']
})
export class NewsEventComponent implements OnInit {
  options:any = {};
  getTimeNewsData:{dataName:string, newCount:number,negativeNewsCount:number,ratio:string }={
    dataName:"最近一周汇总",
    newCount:124,  //新闻总数
    negativeNewsCount:53, //负面新闻
    ratio:"32%" //总/负新闻占比
  };
  //eChart图数据值
  dataMap:any = {
    currentIndex:0
  };
  timeList =["01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"];

  constructor(
    private http: HttpClient,
    private es: NgxEchartsService) { }

  ngOnInit() {
    this.showChart();
  }
  //加载数据展开图
  showChart(){
    this.http.get(`${ApiUrl.api_url}${ApiUrl.regionRisk_newsCharts}`)
      .subscribe(geoJson => {
        this.dataMap = this.getTrueData(geoJson, this.timeList);
        this.getChartsData(this.dataMap);
      });

  }

  //获取图形数据值
  getChartsData(dataMap){
    if(dataMap.currentIndex ==undefined){
      dataMap.currentIndex = 0;
    }
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
          currentIndex:dataMap.currentIndex, //默认选中值
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
          formatter: function (params) {
            let ret = "";
            for(let t of params){
                ret+=t.marker+" "+ t.seriesName+" : "+ t.value+"</br>";
            }
            return ret;
          }
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
              }
            }
          }
        },
        xAxis: [
          {
            type:'category',
            axisLabel:{'interval':0},
            boundaryGap : false,//从最左侧开始
            data:dataMap.timeList,
            splitLine: {show: false},
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
              type : 'line',        // 默认为直线，可选为：'line' | 'shadow'
              label: {
                show: true,
                // formatter: function (params) {
                //   let ret = params.value.replace('\n', '');
                //   return ret;
                // }
              }
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
          {name: '总/负面新闻占比', type: 'line'},
        ]
      },
      options: dataMap.optionSeries
    };
  }
  //获取时间轴点击事件
  getClickTimeLine(e?){
    if(e.componentType =="timeline"){ //点击时间轴方法
      let d = new Date(e.name);
      let days = new Date(d.getFullYear(),d.getMonth()+1,0);
      this.timeList = [];
      for(let i=1;i<= days.getDate();i++){
        this.timeList.push(i<10?('0'+i):i+'');
      }
      this.dataMap.timeList = this.timeList;
      this.dataMap.currentIndex = e.dataIndex;
      this.getChartsData(this.dataMap);
    }

  }
          //  days = 30;//月时间段
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
        let dataList1:any = [];
        let dataList2:any = [];
        let dataList3:any = [];
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
