package com.swim.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class RechargeHistory {
    private Integer rechargeId;
    private Integer id;
    private Float money;
    private Timestamp rechargeTime;

    public Integer getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Integer rechargeId) {
        this.rechargeId = rechargeId;
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

    public Timestamp getRechargeTime() {
        return rechargeTime;
    }

    public void setRechargeTime(Timestamp rechargeTime) {
        this.rechargeTime = rechargeTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RechargeHistory that = (RechargeHistory) o;
        return Objects.equals(rechargeId, that.rechargeId) &&
                Objects.equals(id, that.id) &&
                Objects.equals(money, that.money) &&
                Objects.equals(rechargeTime, that.rechargeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rechargeId, id, money, rechargeTime);
    }

    @Override
    public String toString() {
        return "rechargeHistory{" +
                "rechargeId=" + rechargeId +
                ", id=" + id +
                ", money=" + money +
                ", rechargeTime=" + rechargeTime +
                '}';
    }
}
