package com.swim.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class CostHistory {
    private Integer costId;
    private Integer id;
    private Float money;
    private String goodsName;
    private Timestamp costTime;

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Timestamp getCostTime() {
        return costTime;
    }

    public void setCostTime(Timestamp costTime) {
        this.costTime = costTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostHistory that = (CostHistory) o;
        return Objects.equals(costId, that.costId) &&
                Objects.equals(id, that.id) &&
                Objects.equals(money, that.money) &&
                Objects.equals(goodsName, that.goodsName) &&
                Objects.equals(costTime, that.costTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(costId, id, money, goodsName, costTime);
    }

    @Override
    public String toString() {
        return "CostHistory{" +
                "costId=" + costId +
                ", id=" + id +
                ", money=" + money +
                ", goodsName='" + goodsName + '\'' +
                ", costTime=" + costTime +
                '}';
    }
}
