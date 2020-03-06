package com.swim.dao;

import com.swim.entity.*;
import org.apache.ibatis.annotations.*;

public interface IUserManagementMapper {
    @Select("select * from user where username=#{username}")
    public User ifExitsUserByUsername(String username);

    @Insert("insert into user(username,password) values (#{username},#{password}) ")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    public Integer insertUser(User user);

    @Insert("insert into user_message values (#{id},#{name},#{sex},#{birthday},#{phoneNumber}) ")
    public Integer insertUserMessage(UserMessage userMessage);

    @Insert("insert into user_info values (#{id},#{status},#{authority},#{createTime},#{swimStatus}) ")
    public Integer insertUserInfo(UserInfo userInfo);

    @Insert("insert into account values (#{id},#{balance},#{remainPoints},#{remainTime}) ")
    public Integer insertAccount(Account account);

    @Insert("insert into member values (#{id},#{endTime},#{memberStatus}) ")
    public Integer insertMember(Member member);

    @Delete("delete from user where id=#{id}")
    public Integer deleteUser(Integer id);

    @Delete("delete from user_info where id=#{id}")
    public Integer deleteUserInfo(Integer id);

    @Delete("delete from user_message where id=#{id}")
    public Integer deleteUserMessage(Integer id);

    @Delete("delete from account where id=#{id}")
    public Integer deleteAccount(Integer id);

    @Delete("delete from member where id=#{id}")
    public Integer deleteMember(Integer id);

    @Select("select balance from account where id=#{id}")
    public Float selectBalanceById(Integer id);

    @Update("update user_info set authority=#{authority} where id=#{id}")
    public Integer alterAuthority(@Param("id") Integer id,@Param("authority") Integer authority);

    @Update("update user_info set status=#{status} where id=#{id}")
    public Integer alterStatus(@Param("id") Integer id,@Param("status") Boolean status);

    @Select("select * from user where id=#{id}")
    public User selectUserById(Integer id);

    @Update("update account set balance=balance+#{rechargeMoney} where id=#{id}")
    public Integer recharge(@Param("id") Integer id,@Param("rechargeMoney") Float rechargeMoney);

    @Update("update account set balance=balance-#{refundMoney} where id=#{id}")
    public Integer refund(@Param("id") Integer id,@Param("refundMoney") Float refundMoney);





}
