package com.swim.dao;

import com.sun.scenario.effect.Effect;
import com.swim.entity.*;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

public interface ISwimMapper {

    @Insert("insert into vistor(name,phone_number,balance,cost,swim_start,swim_end)" +
            " values(#{name},#{phoneNumber},#{balance},#{cost},#{swimStart},#{swimEnd})")
    @Options(useGeneratedKeys=true,keyProperty="vistorId",keyColumn="vistor_id")
    public Integer addVistor(Vistor vistor);

    @Select("select * from vistor where swim_end is null")
    public List<Vistor> selectSwimmingVistor(@Param("start") Integer start, @Param("limit")Integer limit);

    @Select("select * from vistor where swim_end is null and ${type} LIKE #{param} LIMIT ${start},${limit}")
    public List<Vistor> selectSwimmingVistorByType(
            @Param("start") Integer start, @Param("limit")Integer limit,@Param("type") String type,@Param("param")String param);

    public List<User> selectNoSwimUser(@Param("start") Integer start,@Param("limit")Integer limit);

    public List<User> selectSwimmingUser(@Param("start") Integer start,@Param("limit")Integer limit);

    public List<User> selectNoSwimUserByType(
            @Param("start") Integer start,@Param("limit")Integer limit,@Param("type")String type,@Param("param")Object param);

    public List<User> selectSwimmingUserByType(
            @Param("start") Integer start,@Param("limit")Integer limit,@Param("type")String type,@Param("param")Object param);

    @Select("select count(vistor_id) from vistor where swim_end is null")
    public Integer selectCountSwimmingVistor();

    @Select("select count(id) from user_info where swim_status=0")
    public Integer selectCountNoSwimUser();

    @Select("select count(id) from user_info where swim_status=1")
    public Integer selectCountSwimmingUser();

    @Select("select count(vistor_id) from vistor where swim_end is null and ${type} LIKE #{param}")
    public Integer selectCountSwimmingVistorByType(@Param("type") String type,@Param("param")String param);

    @Select("select count(b.id) from user a left join user_info b on a.id=b.id where b.swim_status=0 and a.${type} LIKE #{param}")
    public Integer selectCountNoSwimByType1(@Param("type") String type,@Param("param")String param);

    @Select("select count(b.id) from user_message a left join user_info b on a.id=b.id where b.swim_status=0 and a.${type} LIKE #{param}")
    public Integer selectCountNoSwimByType2(@Param("type") String type,@Param("param")String param);

    @Select("select count(b.id) from user a left join user_info b on a.id=b.id where b.swim_status=1 and a.${type} LIKE #{param}")
    public Integer selectCountSwimmingByType1(@Param("type") String type,@Param("param")String param);

    @Select("select count(b.id) from user_message a left join user_info b on a.id=b.id where b.swim_status=1 and a.${type} LIKE #{param}")
    public Integer selectCountSwimmingByType2(@Param("type") String type,@Param("param")String param);

    @Delete("delete from vistor where vistor_id=#{vistorId}")
    public Integer deleteVistor(Integer vistorId);

    @Select("select * from vistor where vistor_id=#{vistorId}")
    public Vistor selectVistorById(Integer vistorId);

    @Select("select * from goods where goods_type=#{goodsType}")
    public Goods selectGoodsByGoodsType(Integer goodsType);//根据商品类型查找

    @Deprecated
    @Select("select * from goods where goods_type=#{goodsType} and goods_value='1' ")
    public Goods selectGoodsBySwimTime(Integer goodsType);//单次费用

    @Select("select * from goods where goods_id=#{goodsId}")
    public Goods selectGoodsByGoodsId(Integer goodsId);

    @Update("update vistor set balance=#{balance},cost=#{cost},swim_end=#{swimEnd} where vistor_id=#{vistorId}")
    public Integer endSwimVistor(Vistor vistor);//结束（结算）散客游泳

    public User selectUserById(Integer id);

    public User selectUserAndSwimHistoryById(Integer id);

    @Update("update user_info set swim_status=1 where id=#{id}")
    public Integer setSwimStatusToTrue(Integer id);//将游泳状态置为游泳状态

    @Update("update user_info set swim_status=0 where id=#{id}")
    public Integer setSwimStatusToFalse(Integer id);//将游泳状态置为游泳状态

    @Insert("insert into swim_history(id,swim_start,swim_end) values (#{id},#{swimStart},#{swimEnd})")
    @Options(useGeneratedKeys=true,keyColumn="swim_id",keyProperty="swimId")
    public Integer recordSwinHistory(SwimHistory swimHistory);

    @Update("update account set remain_time=remain_time-1 where id=#{id}")
    public Integer useRemainTime(Integer id);//使用一次游泳次数

    @Select("select remain_time from account where id=#{id}")
    public int selectRemainTime(Integer id);//剩余游泳次数

    @Update("update account set balance=balance-#{cost} where id=#{id}")
    public Integer swimCostByTimer(@Param("id") Integer id,@Param("cost")Float cost);

    @Update("update swim_history set swim_end=#{swimEnd} where id=#{id} and swim_end is null")
    public Integer setEndSwimHistory(@Param("id")Integer id,@Param("swimEnd") Timestamp swimEnd);


}
