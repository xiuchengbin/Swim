package com.swim.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class PointsHistory {
    private Integer pointsId;
    private Integer id;
    private Integer points;
    private Timestamp pointsTime;
    private String source;

    public Integer getPointsId() {
        return pointsId;
    }

    public void setPointsId(Integer pointsId) {
        this.pointsId = pointsId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Timestamp getPointsTime() {
        return pointsTime;
    }

    public void setPointsTime(Timestamp pointsTime) {
        this.pointsTime = pointsTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String pointsName) {
        this.source = pointsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointsHistory that = (PointsHistory) o;
        return Objects.equals(pointsId, that.pointsId) &&
                Objects.equals(id, that.id) &&
                Objects.equals(points, that.points) &&
                Objects.equals(pointsTime, that.pointsTime) &&
                Objects.equals(source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pointsId, id, points, pointsTime, source);
    }

    @Override
    public String toString() {
        return "PointsHistory{" +
                "pointsId=" + pointsId +
                ", id=" + id +
                ", points=" + points +
                ", pointsTime=" + pointsTime +
                ", pointsName='" + source + '\'' +
                '}';
    }
}
