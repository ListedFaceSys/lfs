import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { NgxEchartsService } from 'ngx-echarts';
import {ApiUrl} from "../../common/constant/api-url.const";

@Component({
  selector: 'app-geography',
  templateUrl: './geography.component.html',
  styleUrls: ['./geography.component.css']
})
export class GeographyComponent implements OnInit {
  SZCityData={  //深沪各类公司数据
    deadline: "2018-01", //截止时间

    SHASZMBCompanyNum:234,    //主板公司
    SHASZMBCompanyRatio:"32%",    //主板公司 较上年同比新增

    MASBCompanyNum:234,    //中小板公司
    MASBCompanyRatio:"32%",    //中小板公司 较上年同比新增

    GCompanyNum:234,    //创业板公司
    GCompanyRatio:"32%",    //创业板公司 较上年同比新增
  };
  options = {};

  constructor(
    private http: HttpClient,
    private es: NgxEchartsService) { }

  ngOnInit() {
    this.getAllCompany();
    this.getSZCityData();
  }


  //获取 深圳市 所有区公司数据
  getSZCityData(){
    this.http.get(`${ApiUrl.api_url}${ApiUrl.regionRisk_geographySZCityData}`)
      .subscribe(geoJson => {
        this.SZCityData = geoJson["data"].SZCityData;
      })
  }

  allCompanyData:any ={};
  //获取公司数据
  getAllCompany(){
    this.http.get(`${ApiUrl.api_url}${ApiUrl.regionRisk_geographyAllCompanyData}`)
      .subscribe(geoJson => {
        this.allCompanyData = geoJson["data"].allCompanyData;

        this.http.get(`${ApiUrl.api_url}${ApiUrl.regionRisk_geographyChartsMap}`)
          .subscribe(geoJson => {
            this.es.registerMap('SZ', geoJson);
            this.options = {
              title: {
                subtext: '公司分布按注册地统计',
              },
              tooltip: {
                trigger: 'item',
                formatter: function (params,ticket,callback) {
                  var res = params.name;
                  if(params.data!=null){
                    if(params.data.SHASZMBCompany!=undefined && params.data.SHASZMBCompany!=null && params.data.SHASZMBCompany !='' && params.data.SHASZMBCompany !='') {
                      res += '<br/>' + '深沪主板公司'+ ' : ' + params.data.SHASZMBCompany + ' 家';
                    }else {
                      res += '<br/>' + '深沪主板公司'+ ' : ' + 0 + ' 家';
                    }
                    if(params.data.MASBCompany!=undefined &&params.data.MASBCompany!=null && params.data.MASBCompany !='' && params.data.MASBCompany !='') {
                      res += '<br/>' + '中小板公司'+ ' : ' + params.data.MASBCompany+ ' 家';
                    }else {
                      res += '<br/>' + '中小板公司'+ ' : ' + 0+ ' 家';
                    }
                    if(params.data.GCompany!=undefined &&params.data.GCompany!=null && params.data.GCompany !='' && params.data.GCompany !='') {
                      res += '<br/>' + '创业板公司'+ ' : ' + params.data.GCompany+ ' 家';
                    }else {
                      res += '<br/>' + '创业板公司'+ ' : ' + 0+ ' 家';
                    }
                  }
                  //使用for可以将需要的数据全部加到res
                  //注意下边的<br/>
                  return res;
                }
              },
              toolbox: {
                show: false,
                orient: 'vertical',
                left: 'right',
                top: 'center',
                feature: {
                  dataView: { readOnly: false },
                  restore: {},
                  saveAsImage: {}
                }
              },
              visualMap: {
                show: false,
                min: 800,
                max: 50000,
                text: ['High', 'Low'],
                realtime: false,
                calculable: true,
                inRange: {
                  color: ['#ccc', '#eee'] //颜色控制
                }
              },

              series: [
                {
                  // name: '香港18区人口密度',
                  type: 'map',
                  mapType: 'SZ', // map type should be registered
                  itemStyle: {
                    normal: { label: { show: true } },
                    emphasis: { label: { show: true } }
                  },
                  aspectScale:1.2, //图片长宽比
                  data: this.allCompanyData,
                  nameMap: {
                    'Guangming New District': '光明新区',
                    'Baoan District': '宝安区',
                    'Longgang District': '龙岗区',
                    'Yantian Area': '盐田区',
                    'Pingshan New District': '坪山新区',
                    'Dapeng New District': '大鹏新区',
                    'Nanshan District': '南山区',
                    'Futian District': '福田区',
                    'Longhua New District': '龙华新区',
                    'Luohu District': '罗湖区',
                  }
                }
              ]
            };
          });
      });

  }
}
