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
  selectOption:{}[] = [];

  constructor(
    private http: HttpClient,
    private es: NgxEchartsService) { }

  ngOnInit() {
    this.initNewsData();
    this.http.get('assets/mapJson/香港.json')
      .subscribe(geoJson => {
        // this.es.registerMap('HK', geoJson);
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
              data : [ "08-01", "08-02", "08-03", "08-04", "08-05", "08-06", "08-07"]//['周一','周二','周三','周四','周五','周六','周日']
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
              data:[0,2, 3, 5, 6, 4, 8, 9, 4, 3, 1, 6, 7],
            },
            {
              name:'负面新闻总数',
              type:'line',
              data:[0,0, 0, 11, 3, 0, 8, 7, 4, 2, 1, 1],
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

  //初始化数据
  initNewsData(){
    this.http.get('assets/mapJson/香港.json')
      .subscribe(geoJson => {
        this.selectOption.push({key:1,value:"最近一周"});
        this.selectOption.push({key:2,value:"最近一月"});
        this.selectOption.push({key:3,value:"最近一年"});
      })
  }

}
