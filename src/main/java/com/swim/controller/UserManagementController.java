package com.swim.controller;

import com.swim.dao.IUserListMapper;
import com.swim.dao.IUserManagementMapper;
import com.swim.entity.Account;
import com.swim.entity.User;
import com.swim.entity.UserMessage;
import com.swim.service.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserManagementController {
    @Autowired
    private UserManagementService userManagementService;
    @Autowired(required = false)
    private IUserListMapper iUserListMapper;

    @PostMapping("user-management/addUser")
    @ResponseBody
    public Object addUser(User user, UserMessage userMessage,Float balance){
        Integer temp=null;
        try {
            temp=userManagementService.addUser(user,userMessage,balance);
        }catch (RuntimeException e){//添加用户失败
            e.printStackTrace();
            temp=0;
        }
        return temp;
    }

    @GetMapping(value="user-management/userList")
    @ResponseBody
    public Object selectUserList(Integer page,Integer limit){//page为第几页，limit为一次查找的数据
        return userManagementService.selectUserList(page,limit);
    }

    @GetMapping(value="user-management/userListByType")
    @ResponseBody
    public Object selectUserListByType(Integer page,Integer limit,String type,String param){//page为第几页，limit为一次查找的数据
        return userManagementService.selectUserListByType(page,limit,type,param);
    }


    @PostMapping(value="user-management/deleteUser")
    @ResponseBody
    public Object deleteUser(Integer id){
        return userManagementService.deleteUser(id);
    }

    @PostMapping(value="user-management/alterAuthority")
    @ResponseBody
    public Integer alterAuthority(Integer id,Integer authority){
        return userManagementService.alterAuthority(id,authority);
    }

    @PostMapping(value="user-management/alterStatus")
    @ResponseBody
    public Integer alterStatus(Integer id,Boolean status){
        return userManagementService.alterStatus(id,status);
    }

    @GetMapping(value="user-management/recharge.html")
    public String toRecharge(Integer id,Model model){
        model.addAttribute("user",iUserListMapper.selectUserById(id));
        return "user-management/recharge";
    }

    @GetMapping(value="user-management/refund.html")
    public String toRefund(Integer id,Model model){
        model.addAttribute("user",iUserListMapper.selectUserById(id));
        return "user-management/refund";
    }

    @PostMapping("user-management/recharge.do")
    @ResponseBody
    public Integer recharge(Integer id,Float rechargeMoney){
        try {
            userManagementService.recharge(id,rechargeMoney);
        }catch (RuntimeException e){
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @PostMapping("user-management/refund.do")
    @ResponseBody
    public Integer refund(Integer id,Float refundMoney){
        try {
            userManagementService.refund(id,refundMoney);
        }catch (RuntimeException e){
            if(e.getMessage().equals("金额异常")){
                return -1;
            }
            return 0;
        }
        return 1;
    }



}
