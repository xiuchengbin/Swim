package com.swim.entity;

import java.util.Objects;

public class PointsGoods {
    private Integer pointsGoodsId;
    private String pointsGoodsName;
    private Integer pointsGoodsType;
    private Integer pointsGoodsValue;
    private Integer pointsGoodsWeight;

    @Override
    public String toString() {
        return "PointsGoods{" +
                "pointsGoodsId=" + pointsGoodsId +
                ", pointsGoodsName='" + pointsGoodsName + '\'' +
                ", pointsGoodsType=" + pointsGoodsType +
                ", pointsGoodsValue=" + pointsGoodsValue +
                ", pointsGoodsWeight=" + pointsGoodsWeight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointsGoods that = (PointsGoods) o;
        return Objects.equals(pointsGoodsId, that.pointsGoodsId) &&
                Objects.equals(pointsGoodsName, that.pointsGoodsName) &&
                Objects.equals(pointsGoodsType, that.pointsGoodsType) &&
                Objects.equals(pointsGoodsValue, that.pointsGoodsValue) &&
                Objects.equals(pointsGoodsWeight, that.pointsGoodsWeight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointsGoodsId, pointsGoodsName, pointsGoodsType, pointsGoodsValue, pointsGoodsWeight);
    }

    public Integer getPointsGoodsWeight() {
        return pointsGoodsWeight;
    }

    public void setPointsGoodsWeight(Integer pointsGoodsWeight) {
        this.pointsGoodsWeight = pointsGoodsWeight;
    }

    public Integer getPointsGoodsType() {
        return pointsGoodsType;
    }

    public void setPointsGoodsType(Integer pointsGoodsType) {
        this.pointsGoodsType = pointsGoodsType;
    }

    public Integer getPointsGoodsValue() {
        return pointsGoodsValue;
    }

    public void setPointsGoodsValue(Integer pointsGoodsValue) {
        this.pointsGoodsValue = pointsGoodsValue;
    }

    public Integer getPointsGoodsId() {
        return pointsGoodsId;
    }

    public void setPointsGoodsId(Integer pointsGoodsId) {
        this.pointsGoodsId = pointsGoodsId;
    }

    public String getPointsGoodsName() {
        return pointsGoodsName;
    }

    public void setPointsGoodsName(String pointsGoodsName) {
        this.pointsGoodsName = pointsGoodsName;
    }

}
