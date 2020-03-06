package com.swim.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class Vistor {
    private Integer vistorId;
    private String name;
    private String phoneNumber;
    private Float balance;
    private Float cost;
    private Timestamp swimStart;
    private Timestamp swimEnd;

    @Override
    public String toString() {
        return "Vistor{" +
                "vistorId=" + vistorId +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                ", cost=" + cost +
                ", swimStart=" + swimStart +
                ", swimEnd=" + swimEnd+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vistor vistor = (Vistor) o;
        return Objects.equals(vistorId, vistor.vistorId) &&
                Objects.equals(name, vistor.name) &&
                Objects.equals(phoneNumber, vistor.phoneNumber) &&
                Objects.equals(balance, vistor.balance) &&
                Objects.equals(cost, vistor.cost) &&
                Objects.equals(swimStart, vistor.swimStart) &&
                Objects.equals(swimEnd, vistor.swimEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vistorId, name, phoneNumber, balance, cost, swimStart, swimEnd);
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getVistorId() {
        return vistorId;
    }

    public void setVistorId(Integer vistorId) {
        this.vistorId = vistorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Timestamp getSwimStart() {
        return swimStart;
    }

    public void setSwimStart(Timestamp swimStart) {
        this.swimStart = swimStart;
    }

    public Timestamp getSwimEnd() {
        return swimEnd;
    }

    public void setSwimEnd(Timestamp swimEnd) {
        this.swimEnd = swimEnd;
    }


}
