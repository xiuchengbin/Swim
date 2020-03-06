package com.swim.service;

import com.swim.dao.IGoodsMapper;
import com.swim.dao.ILoginMapper;
import com.swim.dao.IUserListMapper;
import com.swim.dao.IUserManagementMapper;
import com.swim.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserManagementService {
    @Autowired(required = false)
    private IUserManagementMapper iUserManagementMapper;

    @Autowired(required = false)
    private IUserListMapper iUserListMapper;

    @Autowired(required = false)
    private IGoodsMapper iGoodsMapper;

    @Autowired
    private SwimService swimService;

    private final static String ID="id";
    private final static String USERNAME="username";
    private final static String NAME="name";
    private final static String PHONE_NUMBER="phone_number";
    private final static String RECHARGE="充值";
    private final static String REFUND="退款";

    @Transactional
    public Integer addUser(User user, UserMessage userMessage,Float balance){
        if(iUserManagementMapper.ifExitsUserByUsername(user.getUsername())!=null){//用户名已经存在
            return -1;
        }
        if(iUserManagementMapper.insertUser(user)==1){//添加用户
            Integer id=user.getId();//获取添加成功后的id
            userMessage.setId(id);
            UserInfo userInfo=new UserInfo();
            userInfo.setId(id);
            userInfo.setStatus(true);
            userInfo.setAuthority(1);
            userInfo.setCreateTime(new Timestamp(System.currentTimeMillis()));
            userInfo.setSwimStatus(false);
            Account account=new Account();
            account.setId(id);
            account.setBalance(balance);
            account.setRemainPoints(0);
            account.setRemainTime(0);
            Member member=new Member();
            member.setId(id);
            member.setEndTime(null);
            member.setMemberStatus(false);
            if(iUserManagementMapper.insertUserMessage(userMessage)==1&&iUserManagementMapper.insertUserInfo(userInfo)==1&&
                    iUserManagementMapper.insertAccount(account)==1&&iUserManagementMapper.insertMember(member)==1){
                return id;//添加用户成功
            }else {
                throw new RuntimeException("添加用户失败");
            }
        }else {
            throw new RuntimeException("添加用户失败");
        }
    }

    public Map<String,Object> selectUserList(Integer page,Integer limit){//第几页，查询多少数据 limit (page-1)*limit,limit
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iUserListMapper.selectUserCount());//总数据量
        map.put("msg","");
        map.put("data",iUserListMapper.selectUser(start,limit));
        return map;
    }

    public Map<String,Object> selectUserListByType(Integer page, Integer limit, String type, String param) {
        String table = null;
        if(type.equals(ID)||type.equals(USERNAME)){//查询条件是id或username
            table="user";
        }else if(type.equals(NAME)||type.equals(PHONE_NUMBER)){//查询条件是id或username
            table="user_message";
        }else {
            return null;
        }
        Integer start=(page-1)*limit;
        List<User> list=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("status",200);
        map.put("count",iUserListMapper.selectUserCountByType(table,type,param+"%"));//总数据量
        map.put("msg","");
        map.put("data",iUserListMapper.selectUserByType(start,limit,type,param+"%"));
        return  map;
    }

    @Transactional
    public Object deleteUser(Integer id) {
        if(iUserManagementMapper.selectBalanceById(id)!=0f){//有余额不可以删除
            return -1;
        }
        Integer temp=iUserManagementMapper.deleteUser(id)&iUserManagementMapper.deleteUserMessage(id)&iUserManagementMapper.deleteUserInfo(id)
                &iUserManagementMapper.deleteAccount(id)&iUserManagementMapper.deleteMember(id);
        if(temp!=1){
            throw new RuntimeException("删除用户失败");
        }else{
            return temp;
        }
    }

    public Integer alterAuthority(Integer id,Integer authority) {//修改权限
        if(authority!=2){//防止错误数据
            authority=1;
        }
        return iUserManagementMapper.alterAuthority(id,authority);
    }

    public Integer alterStatus(Integer id,Boolean status) {//修改账号状态
        return iUserManagementMapper.alterStatus(id,status);
    }

    @Transactional
    public Integer recharge(Integer id,Float rechargeMoney) {//充值
        if(iUserManagementMapper.recharge(id,rechargeMoney)==1&&setCostHistory(id,rechargeMoney,RECHARGE)==1){
            swimService.setMoneyData(rechargeMoney,"用户充值",id);
            return 1;
        }else {
            throw new RuntimeException("充值异常");
        }
    }

    @Transactional
    public Integer refund(Integer id,Float refundMoney) {//退款
        System.out.println();
        if(refundMoney>iUserManagementMapper.selectBalanceById(id)){//退款金额大于余额
            throw new RuntimeException("金额异常");
        }
        if(iUserManagementMapper.refund(id,refundMoney)==1&&setCostHistory(id,-refundMoney,REFUND)==1){
            swimService.setMoneyData(-refundMoney,"用户退款",id);
            return 1;
        }else {
            throw new RuntimeException("充值异常");
        }
    }

    public Integer setCostHistory(Integer id,Float money,String source){
        CostHistory costHistory=new CostHistory();
        costHistory.setId(id);
        costHistory.setMoney(money);
        costHistory.setGoodsName(source);
        costHistory.setCostTime(new Timestamp(System.currentTimeMillis()));
        return iGoodsMapper.recordCostHistory(costHistory);
    }
}
