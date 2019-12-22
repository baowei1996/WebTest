package com.saprj.service;

import com.saprj.dao.CollectMapper;
import com.saprj.dao.UserMapper;
import com.saprj.entity.Collect;
import com.saprj.entity.Dataset;
import com.saprj.entity.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServicer {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CollectMapper collectMapper;

    public void registe(User user){
        String pwd = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(pwd);
        userMapper.insertSelective(user);
    }

    public List<Dataset> myCollection(User user) {
        return collectMapper.selectByUid(user.getUid());
    }
}
