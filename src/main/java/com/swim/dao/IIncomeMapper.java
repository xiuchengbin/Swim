package com.swim.dao;

import com.swim.entity.IncomeHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IIncomeMapper {

    @Insert("insert into income_history(id,source,income_money,time) values(#{id},#{source},#{incomeMoney},#{time})")
    @Options(useGeneratedKeys = true,keyProperty = "incomeId",keyColumn = "income_id")
    public Integer addIncomeHistory(IncomeHistory incomeHistory);

    @Select("select count(income_id) from income_history")
    public Integer getAllCountIncomeHistory();

    @Select("select * from income_history LIMIT ${start},${limit}")
    public List<IncomeHistory> getAllIncomeHistory(Integer start, Integer limit);


    public Integer getAllIncomeHistoryCountByIdOrTime(@Param("id") Integer id, @Param("start")String startTime, @Param("end")String endTime);

    public List<IncomeHistory> getAllIncomeHistoryByIdOrTime(@Param("id") Integer id, @Param("start")Integer start, @Param("limit")Integer limit
            , @Param("startTime")String startTime, @Param("endTime")String endTime);
}
