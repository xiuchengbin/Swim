package com.swim.dao;

import com.swim.entity.LotteryHistory;
import com.swim.entity.PointsGoods;
import com.swim.entity.PointsHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPointsMapper {
    @Update("update account set remain_points=remain_points+#{points} where id=#{id}")
    public Integer addPoints(@Param("id") Integer id, @Param("points")Integer points);

    @Insert("insert into points_history(id,points,points_time,source) values(#{id},#{points},#{pointsTime},#{source})")
    @Options(useGeneratedKeys = true,keyProperty = "pointsId",keyColumn = "points_id")
    public Integer addPointsHistory(PointsHistory pointsHistory);

    @Select("select * from points_goods")
    public List<PointsGoods> getAllPointsGoods();


    @Update("update account set remain_time=remain_time+#{remainTime} where id=#{id}")
    public Integer addSwimTime(@Param("id") Integer id, @Param("remainTime")Integer remainTime);

    @Update("update account set balance=balance+#{money} where id=#{id}")
    public Integer rechargeAccount(@Param("id")Integer id,@Param("money")Integer money);

    @Select("select remain_points from account where id=#{id}")
    public Integer getPointsById(Integer id);

    @Select("select count(points_goods_id) from points_goods")
    public Integer getGoodsCount();

    @Select("select * from points_goods limit ${start},${limit}")
    public List<PointsGoods> getPointsGoodsList(@Param("start") Integer start,@Param("limit") Integer limit);

    @Delete("delete from points_goods where points_goods_id=#{pointsGoodsId}")
    public Integer deletePointsGoods(Integer pointsGoodsId);

    @Select("select * from points_goods where points_goods_id=#{pointsGoodsId}")
    public PointsGoods getPointsGoodsById(Integer pointsGoodId);

    @Update("update points_goods set " +
            "points_goods_name=#{pointsGoodsName},points_goods_value=#{pointsGoodsValue},points_goods_weight=#{pointsGoodsWeight} " +
            "where points_goods_id=#{pointsGoodsId}")
    public Integer alterPointsGoodsById(PointsGoods pointsGoods);

    @Select("select sum(points_goods_weight) from points_goods")
    public Integer getSumWeight();

    @Insert("insert into points_goods(points_goods_name,points_goods_type,points_goods_value,points_goods_weight)" +
            "values(#{pointsGoodsName},#{pointsGoodsType},#{pointsGoodsValue},#{pointsGoodsWeight})")
    @Options(useGeneratedKeys = true,keyColumn = "points_goods_id",keyProperty = "pointsGoodsId")
    public Integer addPointsGoods(PointsGoods pointsGoods);

    @Select("select count(points_id) from points_history where id=#{id}")
    public Integer getPointsHistoryCountById(Integer id);

    @Select("select * from points_history where id=#{id} LIMIT ${start},${limit}")
    public List<PointsHistory> getPointsHistoryById(Integer id, Integer start, Integer limit);

    @Insert("insert into lottery_history(id,prize,lottery_time) values(#{id},#{prize},#{lotteryTime})")
    @Options(useGeneratedKeys = true,keyProperty = "lotteryId",keyColumn = "lottery_id")
    public Integer addLotteryHistory(LotteryHistory lotteryHistory);

    @Select("select count(lottery_id) from lottery_history where id=#{id}")
    public Integer getLotteryHistoryCountById(Integer id);

    @Select("select count(lottery_id) from lottery_history where id=#{id} and DATEDIFF(lottery_time,#{start})>=0 and DATEDIFF(lottery_time,#{end})<=0")
    public Integer getLotteryHistoryCountByIdAndTime(Integer id,@Param("start")String startTime, @Param("end")String endTime);

    @Select("select * from lottery_history where id=#{id} LIMIT ${start},${limit}")
    public List<LotteryHistory> getLotteryHistoryById(Integer id, Integer start, Integer limit);

    @Select("select * from lottery_history where id=#{id} and DATEDIFF(lottery_time,#{startTime})>=0 and DATEDIFF(lottery_time,#{endTime})<=0 " +
            "LIMIT ${start},${limit}")
    public List<LotteryHistory> getLotteryHistoryByIdAndTime(@Param("id") Integer id,@Param("start")Integer start,@Param("limit")Integer limit
            , @Param("startTime")String startTime,@Param("endTime")String endTime);
}
