import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NgxEchartsService} from "ngx-echarts";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  options = {};
  radioOptions:{}[] = [];//单选项
  radioChecked:number=1;//选中值

  //eChart图数据值
  categoryChartData:{ xAxisData:any[],seriesData:{ line:any[], bar:any[] } } = {
    xAxisData:[],
    seriesData:{
      line:[],
      bar:[]
    }
  };

  constructor(
    private http: HttpClient,
    private es: NgxEchartsService) { }

  ngOnInit() {
    this.initCategoryData();

    this.http.get('assets/dataJson/categoryChartData.json')
      .subscribe(geoJson => {
        this.categoryChartData.xAxisData = geoJson["data"].xAxisData;
        this.categoryChartData.seriesData = geoJson["data"].seriesData;
        this.options = {
          title: {
            show:false,
          },
          toolbox:{
            show:false,
            right:20,
            feature:{
              saveAsImage: {},
              restore: {},
              magicType: {
                type: ['line', 'bar']
              },
              // brush: {},
            }
          },
          tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
              type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
          },
          color: [
            '#07cdbd', '#ff5700', '#06cd06', '#ff9600', '#00a3d8', '#dce319'
          ],
         /* legend: {
            show:false,
            data:['AQI','PM2.5']
          },*/
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis : [
            {
              type : 'category',
              axisLabel:{
                textStyle:{
                  color: '#000',
                  fontSize:'14',
                },
                interval:0,
                rotate:-90,
              },
              data : this.categoryChartData.xAxisData//['临川区','东乡区','南城县','黎川县','南丰县','崇仁县','乐安县','宜黄县','金溪县','资溪县','广昌县']
            }
          ],
          yAxis : [
            {
              type : 'value',
              // name: '单位:μg/m3(CO为mg/m3)',
              axisTick: {
                show: true,  //分段短横线
                // color:'#fff',
              },
              axisLine: {
                show: true,
              },
              axisLabel: {
                show: true
                //color:'#fff',
              },
              splitLine: {
                show: false,
                // color:'#fff',
              }
            },
            {
              type : 'value',
              // name: '温度',
              min: 0,
              max: 60,
              // interval: 5,
              axisLabel: {
                formatter: '{value} °C'
              },
              axisTick: {
                show: true,
                // color:'#fff',
              },
              axisLine: {
                show: true,
              },
              splitLine: {
                show: false,
                // color:'#fff',
              }
            }
          ],
          series : [
            {
              name:'AQI',
              type:'line',
              data:this.categoryChartData.seriesData.line//[40, 50, 45, 56, 45, 55,123,156, 129,142,148]
            },
            {
              name:'PM2.5',
              type:'bar',
              data:this.categoryChartData.seriesData.bar //[30, 45, 73, 34, 58, 70, 60, 54, 63,50, 70]
            }
          ]
        };

      });
  }

  //初始化数据
  initCategoryData(){
    this.http.get('assets/dataJson/selectBase.json')
      .subscribe(geoJson => {
        this.radioOptions= geoJson["data"].categoryRadios;
      })
  }

  //获取radio选中值
  getRadioChecked(){

  }
}
