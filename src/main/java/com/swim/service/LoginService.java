package com.swim.service;

import com.swim.dao.ILoginMapper;
import com.swim.entity.User;
import com.swim.entity.UserInfo;
import com.swim.entity.UserMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private ILoginMapper loginMapper;

    public User updateloginSession(Integer id){//用于更新session中用户信息
        User sessionUser=loginMapper.selectUserById(id);//登录信息
        UserInfo userInfo=loginMapper.selectUserInfoById(id);//账号的信息
        if(userInfo.getStatus()==false){//账号被冻结
            return null;
        }
        UserMessage userMessage=loginMapper.selectUserMessageById(id);//用户资料
        sessionUser.setUserInfo(userInfo);
        sessionUser.setUserMessage(userMessage);
        return sessionUser;
    }

    public User getShiroUser(Integer id){
        User user=loginMapper.selectUserById(id);
        user.setUserInfo(loginMapper.selectUserInfoById(id));
        return user;
    }

    public Integer getIdByUsername(String username){
        return loginMapper.getIdByUsername(username);
    }

}
