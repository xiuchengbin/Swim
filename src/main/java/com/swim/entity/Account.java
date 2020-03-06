package com.swim.entity;

import java.util.Objects;

public class Account {
    private Integer id;
    private Float balance;
    private Integer remainPoints;
    private Integer remainTime;//剩余游泳次数

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Integer getRemainPoints() {
        return remainPoints;
    }

    public void setRemainPoints(Integer remainPoints) {
        this.remainPoints = remainPoints;
    }

    public Integer getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(Integer remainTime) {
        this.remainTime = remainTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(balance, account.balance) &&
                Objects.equals(remainPoints, account.remainPoints) &&
                Objects.equals(remainTime, account.remainTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, remainPoints, remainTime);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", remainPoints=" + remainPoints +
                ", remainTime=" + remainTime +
                '}';
    }
}
