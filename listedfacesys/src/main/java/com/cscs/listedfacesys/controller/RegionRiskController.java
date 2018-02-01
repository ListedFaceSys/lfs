package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.NewsTableOutData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.services.NewsTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Create by wzy on 2018/2/1
 * 区域风险总览
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/regionRisk")
public class RegionRiskController {
    @Autowired
    NewsTableService newsTableService;

    //违约事件跟踪
    @RequestMapping(value = "/lastingBondViolation/{page}", method = RequestMethod.GET)
    public BaseOutData getViolation(@PathVariable int page) {
        BaseOutData out = new BaseOutData();
        try {
            out =  newsTableService.getLastingBondViolationNews(page, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

}
