package com.swim.dao;

import com.swim.entity.User;
import com.swim.entity.UserInfo;
import com.swim.entity.UserMessage;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ILoginMapper {
    @Deprecated
    @Select("select * from user where username=#{username} and password=#{password}")
    public User selectUserByUsernameAndPassword(User user);

    @Select("select * from user where id=#{id}")
    public User selectUserById(Integer id);

    @Select("select * from user_message where id=#{id}")
    public UserMessage selectUserMessageById(Integer id);

    @Select("select * from user_info where id=#{id}")
    public UserInfo selectUserInfoById(Integer id);

    @Select("select * from user where username=#{username}")
    public User getUserByUsername(String username);

    @Select("select id from user where username=#{username}")
    public Integer getIdByUsername(String username);





}
