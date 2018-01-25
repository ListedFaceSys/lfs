package com.cscs.listedfacesys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloTestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "Hello World!";
    }
}
