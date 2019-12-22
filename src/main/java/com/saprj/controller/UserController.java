package com.saprj.controller;

import com.saprj.entity.Dataset;
import com.saprj.entity.User;
import com.saprj.service.UserServicer;
import com.saprj.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserServicer userServicer;

    //TODO 登录
    @RequestMapping(value = "login")
    public void login(){}

    //TODO 登出
    @RequestMapping(value = "logout")
    public void logout(){}

    //注册
    @ResponseBody
    @RequestMapping(value = "registe")
    public ResponseData registe(User user){
        userServicer.registe(user);
        return ResponseData.ok();
    }

    /**
     * 获取一个用户的收藏数据集记录
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "myCollection",method = RequestMethod.GET)
    public Map myCollection(User user){
        List<Dataset> datasets = userServicer.myCollection(user.getUid());
        Map<String,Object> result = new HashMap<>();
        result.put("datasets",datasets);
        return result;
    }
}
