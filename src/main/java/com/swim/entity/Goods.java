package com.swim.entity;

import java.util.Objects;

public class Goods {
    private Integer goodsId;
    private String goodsName;
    private String goodsMessage;
    private Float goodsPrice;
    private Integer goodsType;
    private Integer goodsValue;

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsMessage='" + goodsMessage + '\'' +
                ", goodsPrice=" + goodsPrice +
                ", goodsType=" + goodsType +
                ", goodsValue=" + goodsValue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goods goods = (Goods) o;
        return Objects.equals(goodsId, goods.goodsId) &&
                Objects.equals(goodsName, goods.goodsName) &&
                Objects.equals(goodsMessage, goods.goodsMessage) &&
                Objects.equals(goodsPrice, goods.goodsPrice) &&
                Objects.equals(goodsType, goods.goodsType) &&
                Objects.equals(goodsValue, goods.goodsValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsId, goodsName, goodsMessage, goodsPrice, goodsType, goodsValue);
    }

    public Integer getGoodsValue() {
        return goodsValue;
    }

    public void setGoodsValue(Integer goodsValue) {
        this.goodsValue = goodsValue;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsMessage() {
        return goodsMessage;
    }

    public void setGoodsMessage(String goodsMessage) {
        this.goodsMessage = goodsMessage;
    }

    public Float getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Float goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

}
