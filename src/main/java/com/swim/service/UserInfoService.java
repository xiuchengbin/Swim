package com.swim.service;

import com.swim.dao.IUserInfoMapper;
import com.swim.entity.Account;
import com.swim.entity.Member;
import com.swim.entity.User;
import com.swim.entity.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserInfoService {
    @Autowired
    private IUserInfoMapper iUserInfoMapper;

    public Integer alterPassword(Integer id,String newPassword){
        return iUserInfoMapper.updatePassword(id,newPassword);
    }

    public Integer alterUserInfo(UserMessage userMessage){
        return iUserInfoMapper.updateUserMessageById(userMessage);
    }

    @Transactional
    public User selectAccountAndMember(Integer id){
        User user=new User();
        user.setAccount(iUserInfoMapper.selectAccount(id));
        user.setMember(iUserInfoMapper.selectMember(id));
        return user;
    }


}
