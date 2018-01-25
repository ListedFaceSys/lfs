package com.cscs.listedfacesys.services;

import com.cscs.listedfacesys.dao.UserDao;
import com.cscs.listedfacesys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUserList(){
        return userDao.getAllUserList();
    }
}
