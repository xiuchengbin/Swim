package com.swim.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface IDataMapper {
    @Select("select version()")
    public String getMySQL_version();

    @Update("update data day_click=0,day_swim=0,day_money=0")
    public Integer updateDataEveryDay();
}
