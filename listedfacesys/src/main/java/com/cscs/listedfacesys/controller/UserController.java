package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.model.User;
import com.cscs.listedfacesys.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<User> getAllUser(){
        List<User> userList = userServices.getAllUserList();
        return userList;
    }
}
