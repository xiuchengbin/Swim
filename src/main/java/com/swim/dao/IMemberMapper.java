package com.swim.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;

public interface IMemberMapper {
    @Update("update member set member_status =0 where id IN(\n" +
            "select a.id from (\n" +
            "(select id from member where (DATEDIFF(curdate(),end_time)>0 or end_time is null) and member_status=1)\n" +
            ")a\n" +
            ")")
    public Integer verifyMemberStatus();//将会员过期的状态置为0

    @Update("update member set end_time=date_add(end_time,INTERVAL #{day} day) where id=#{id}")
    public Integer addMemberDay(@Param("id") Integer id, @Param("day")Integer day);

    @Update("update member set  member_status='1',end_time=date_add(#{today},INTERVAL #{day} day) where id=#{id}")
    public Integer addNoMemberDay(@Param("id") Integer id, @Param("day")Integer day, @Param("today")Date today);

}
