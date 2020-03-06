package com.swim.dao;

import com.swim.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserListMapper {
    public List<User> selectUser(@Param("start") Integer start,@Param("limit")Integer limit);

    @Select("select count(id) from user")
    public Integer selectUserCount();

    public List<User> selectUserByType(
            @Param("start") Integer start,@Param("limit")Integer limit,@Param("type")String type,@Param("param")Object param);

    @Select("select count(id) from ${table} where ${type} LIKE #{param}")
    public Integer selectUserCountByType(@Param("table") String table,@Param("type") String type,@Param("param")Object param);

    public User selectUserById(Integer id);


}
