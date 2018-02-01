import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NgxEchartsService} from "ngx-echarts";

@Component({
  selector: 'app-news-event',
  templateUrl: './news-event.component.html',
  styleUrls: ['./news-event.component.css']
})
export class NewsEventComponent implements OnInit {
  options = {};
  selectOptions:{}[] = []; //下拉选项集
  selectChecked:number=1;//选中值
  getTimeNewsData:{dataName:string, allNetworkNum:number,negativeNum:number,negativeProportion:string }={
    dataName:"最近一周汇总",
    allNetworkNum:124,
    negativeNum:53,
    negativeProportion:"32%"
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
    this.getChartsData();
  }

  //初始化数据
  initNewsData(){
    this.http.get('assets/dataJson/selectBase.json')
      .subscribe(geoJson => {
        this.selectOptions= geoJson["data"].newsEventSelects;
      })
  }


  //获取图形数据值
  getChartsData(){
    this.http.get('assets/dataJson/newsEventChartData.json')
      .subscribe(geoJson => {
        this.newsEventChartData.xAxisData = geoJson["data"].xAxisData;
        this.newsEventChartData.seriesData = geoJson["data"].seriesData;
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
              /*markPoint : {
                  data : [
                      {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                  ]
              },
              markLine : {
                  data : [
                      {type : 'average', name : '平均值'}
                  ]
              }*/
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

}
