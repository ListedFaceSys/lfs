package com.cscs.listedfacesys.controller;

import com.cscs.listedfacesys.dto.LfsUserInfoData;
import com.cscs.listedfacesys.dto.LfsUserInfoOutData;
import com.cscs.listedfacesys.dto.base.BaseOutData;
import com.cscs.listedfacesys.entity.LfsUser;
import com.cscs.listedfacesys.services.LfsUserInfoServices;
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
public class LfsUserInfoController {
    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    LfsUserInfoServices lfsUserInfoServices;

    /**
     * 注册新用户
     * @param inData
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public LfsUserInfoOutData save(@RequestBody LfsUserInfoData inData) {
        LfsUser infoData = new LfsUser();
        LfsUserInfoOutData outData = new LfsUserInfoOutData();
        try {
            LfsUser userBasicinfo = lfsUserInfoServices.findByUserName(inData.getUserName());
            if (userBasicinfo != null) {
                outData.setCode("1");
                outData.setMessage("该用户已注册！");
                return outData;
            }

            BeanUtils.copyProperties(inData, infoData);
            infoData = lfsUserInfoServices.save(infoData);

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
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public BaseOutData updatePassword(@RequestBody LfsUserInfoData inData) throws Exception {
        BaseOutData outData = new BaseOutData();

        int num = lfsUserInfoServices.updatePassword(inData.getUserName(), inData.getPassword());

        outData.setCode(String.valueOf(num));
        return outData;
    }

    /**
     * 用户列表
     * @return
     */
    @RequestMapping(value = "/getAllUser", method = RequestMethod.GET)
    public BaseOutData getAllUser() {
        BaseOutData outData = new BaseOutData();

        Map<String, List<LfsUser>> userList = new HashMap<String, List<LfsUser>>();
        List<LfsUser> users = lfsUserInfoServices.findAllUser();
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
