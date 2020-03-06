package com.swim.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class SwimHistory {
    private Integer swimId;
    private Integer id;
    private Timestamp swimStart;
    private Timestamp swimEnd;

    @Override
    public String toString() {
        return "SwimHistory{" +
                "swimId=" + swimId +
                ", id=" + id +
                ", swimStart=" + swimStart +
                ", swimEnd=" + swimEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwimHistory that = (SwimHistory) o;
        return Objects.equals(swimId, that.swimId) &&
                Objects.equals(id, that.id) &&
                Objects.equals(swimStart, that.swimStart) &&
                Objects.equals(swimEnd, that.swimEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(swimId, id, swimStart, swimEnd);
    }

    public Integer getSwimId() {
        return swimId;
    }

    public void setSwimId(Integer swimId) {
        this.swimId = swimId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
