package com.swim.dao;

import com.swim.entity.Account;
import com.swim.entity.Member;
import com.swim.entity.UserMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IUserInfoMapper {
    @Update("update user set password=#{password} where id=#{id}")
    public Integer updatePassword(@Param("id") Integer id,@Param("password")String newPassword);

    @Update("update user_message set name=#{name},sex=#{sex},birthday=#{birthday},phone_number=#{phoneNumber} where id=#{id}")
    public Integer updateUserMessageById(UserMessage userMessage);

    @Select("select * from account where id=#{id}")
    public Account selectAccount(Integer id);

    @Select("select * from member where id=#{id}")
    public Member selectMember(Integer id);
}
