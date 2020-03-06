package com.swim.dao;

import com.swim.entity.CostHistory;
import com.swim.entity.Goods;
import com.swim.entity.PointsHistory;
import com.swim.entity.SwimHistory;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

public interface IGoodsMapper {
    @Select("select * from goods where goods_id=#{goodsId}")
    public Goods selectGoodsById(Integer goodsId);

    @Insert("insert into cost_history(id,money,goods_name,cost_time) values (#{id},#{money},#{goodsName},#{costTime})")
    @Options(useGeneratedKeys=true,keyColumn="cost_id",keyProperty="costId")
    public Integer recordCostHistory(CostHistory costHistory);

    @Update("update account set balance=balance-#{price} where id=#{id}")
    public Integer cost(@Param("id")Integer id,@Param("price")Float goodsPrice);//消费

    @Select("select count(goods_id) from goods")
    public Integer getGoodsCount();

    @Select("select * from goods LIMIT ${start},#{limit}")
    public List<Goods> getGoodsList(@Param("start")Integer start,@Param("limit") Integer limit);

    @Delete("delete from goods where goods_id=#{goodsId}")
    public Integer deleteGoodsById(Integer goodsId);

    @Update("update goods set goods_name=#{goodsName},goods_message=#{goodsMessage},goods_price=#{goodsPrice},goods_value=#{goodsValue}" +
            " where goods_id=#{goodsId}")
    public Integer alterGoods(Goods goods);

    @Insert("insert into goods(goods_name,goods_message,goods_price,goods_type,goods_value) " +
            "values(#{goodsName},#{goodsMessage},#{goodsPrice},#{goodsType},#{goodsValue})")
    @Options(useGeneratedKeys=true,keyColumn="goods_id",keyProperty="goodsId")
    public Integer addGoods(Goods goods);

    @Select("select * from cost_history where id=#{id} LIMIT ${start},${limit}")
    public List<CostHistory> getCostHistoryById(@Param("id") Integer id,@Param("start")Integer start,@Param("limit")Integer limit);

    @Select("select count(cost_id) from cost_history where id=#{id}")
    public Integer getCostHistoryCountById(Integer id);

    @Select("select count(swim_id) from swim_history where id=#{id}")
    public Integer getSwimHistoryCountById(Integer id);

    @Select("select * from swim_history where id=#{id} LIMIT ${start},${limit}")
    public List<SwimHistory> getSwimHistoryById(@Param("id") Integer id, @Param("start")Integer start, @Param("limit")Integer limit);

    @Select("select count(cost_id) from cost_history where id=#{id} and DATEDIFF(cost_time,#{start})>=0 and DATEDIFF(cost_time,#{end})<=0")
    public Integer getCostHistoryCountByIdAndTime(@Param("id")Integer id, @Param("start")String start,@Param("end")String end);

    @Select("select count(swim_id) from swim_history where id=#{id} and DATEDIFF(swim_start,#{start})>=0 and DATEDIFF(swim_start,#{end})<=0")
    public Integer getSwimHistoryCountByIdAndTime(@Param("id")Integer id, @Param("start")String start,@Param("end")String end);

    @Select("select * from cost_history where id=#{id} and DATEDIFF(cost_time,#{startTime})>=0 and DATEDIFF(cost_time,#{endTime})<=0" +
            " LIMIT ${start},${limit}")
    public List<CostHistory> getCostHistoryByIdAndTime(@Param("id") Integer id,@Param("start")Integer start,@Param("limit")Integer limit
            , @Param("startTime")String startTime,@Param("endTime")String endTime);

    @Select("select * from swim_history where id=#{id} and DATEDIFF(swim_start,#{startTime})>=0 and DATEDIFF(swim_start,#{endTime})<=0" +
            " LIMIT ${start},${limit}")
    public List<SwimHistory> getSwimHistoryByIdAndTime(@Param("id") Integer id,@Param("start")Integer start,@Param("limit")Integer limit
            , @Param("startTime")String startTime,@Param("endTime")String endTime);

    @Select("select count(points_id) from points_history where id=#{id} and DATEDIFF(points_time,#{start})>=0 and DATEDIFF(points_time,#{end})<=0")
    public Integer getPointsHistoryCountByIdAndTime(@Param("id") Integer id, @Param("start")String startTime, @Param("end")String endTime);

    @Select("select * from points_history where id=#{id} and DATEDIFF(points_time,#{startTime})>=0 and DATEDIFF(points_time,#{endTime})<=0" +
            " LIMIT ${start},${limit}")
    public List<PointsHistory> getPointsHistoryByIdAndTime(@Param("id") Integer id,@Param("start")Integer start,@Param("limit")Integer limit
            , @Param("startTime")String startTime,@Param("endTime")String endTime);

    @Select("select * from swim_history LIMIT ${start},${limit}")
    public List<SwimHistory> getAllSwimHistory(Integer start, Integer limit);

    @Select("select count(swim_id) from swim_history")
    public Integer getAllSwimHistoryCount();


    public Integer getAllSwimHistoryCountByIdOrTime(@Param("id") Integer id, @Param("start")String startTime, @Param("end")String endTime);


    public List<SwimHistory> getAllSwimHistoryByIdOrTime(@Param("id") Integer id,@Param("start")Integer start,@Param("limit")Integer limit
            , @Param("startTime")String startTime,@Param("endTime")String endTime);
}
