package com.swim.controller;

import com.swim.entity.User;
import com.swim.entity.UserMessage;
import com.swim.service.LoginService;
import com.swim.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LoginService loginService;

    @PostMapping("user/alterPassword")
    @ResponseBody
    public Object alterPassword(HttpSession session,String oldPassword,String newPassword){//修改密码
        User user= (User) session.getAttribute("user");//session中用户信息
        if(!user.getPassword().equals(oldPassword)){//原密码错误
            return -1;
        }
        Integer temp = userInfoService.alterPassword(user.getId(),newPassword);
        if(temp==1){//修改成功
            session.invalidate();
            return 1;
        }
        return temp;
    }

    @PostMapping("user/alterUserInfo")
    @ResponseBody
    public Object alterUserInfo(HttpSession session, UserMessage userMessage){//修改个人信息
        User user= (User) session.getAttribute("user");//session中用户信息
        Integer temp=userInfoService.alterUserInfo(userMessage);
        if(temp==1){//修改成功
            session.setAttribute("user",loginService.updateloginSession(user.getId()));
        }
        return temp;
    }

    @PostMapping("user/selectAccount")
    @ResponseBody
    public User selectAccount(HttpSession session){
        User user= (User) session.getAttribute("user");//session中用户信息
        return userInfoService.selectAccountAndMember(user.getId());
    }

}
