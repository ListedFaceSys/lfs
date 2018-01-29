package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.UserBasicInfoData;
import com.cscs.listedfacesys.dto.UserBasicInfoOutData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.entity.UserBasicinfoEntity;
import com.cscs.listedfacesys.services.IUserBasicInfoServices;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by wth on 2018/1/27
 * 用户信息
 */
@RestController
@RequestMapping(value = "/user")
public class UserBasicInfoController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    IUserBasicInfoServices userBasicInfoServices;

    /**
     * 注册新用户
     * @param inData
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public UserBasicInfoOutData save(@RequestBody UserBasicInfoData inData) {
        UserBasicinfoEntity infoData = new UserBasicinfoEntity();
        UserBasicInfoOutData outData = new UserBasicInfoOutData();
        try {
            UserBasicinfoEntity userBasicinfo = userBasicInfoServices.findByUserNm(inData.getUserNm());
            if (userBasicinfo != null) {
                outData.setCode("1");
                outData.setMessage("该用户已注册！");
                return outData;
            }

            BeanUtils.copyProperties(inData, infoData);
            infoData = userBasicInfoServices.save(infoData);

            BeanUtils.copyProperties(infoData, outData);
            outData.setCode("0");
            outData.setMessage("用户注册成功！");
        } catch (Exception e) {
            outData.setCode("1");
            outData.setMessage("用户注册失败！");
        }
        return outData;
    }


    /**
     * 忘记密码
     * @param inData
     * @return
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    public BaseOutData updatePassword(@RequestBody UserBasicInfoData inData) throws Exception {
        BaseOutData outData = new BaseOutData();

        int num = userBasicInfoServices.updatePassword(inData.getUserNm(), inData.getPassword());

        outData.setCode(String.valueOf(num));
        return outData;
    }

    /**
     * 用户列表
     * @return
     */
    @RequestMapping(value = "getAllUser", method = RequestMethod.GET)
    public BaseOutData getAllUser() {
        BaseOutData outData = new BaseOutData();

        Map<String, List<UserBasicinfoEntity>> userList = new HashMap<String, List<UserBasicinfoEntity>>();
        List<UserBasicinfoEntity> users = userBasicInfoServices.findAllUser();
        if (users != null) {
            userList.put("userList", users);
            outData.setCode("0");
            outData.setMessage("获取用户信息成功！");
            outData.setData(userList);
            return outData;
        }
        outData.setCode("1");
        outData.setMessage("用户信息为空！");
        return outData;
    }
}
