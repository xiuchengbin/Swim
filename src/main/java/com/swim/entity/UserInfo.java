package com.swim.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class UserInfo {
    private Integer id;
    private Boolean status;
    private Integer authority;
    private Timestamp createTime;
    private Boolean swimStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAuthority() {
        return authority;
    }

    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Boolean getSwimStatus() {
        return swimStatus;
    }

    public void setSwimStatus(Boolean swimStatus) {
        this.swimStatus = swimStatus;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", status=" + status +
                ", authority=" + authority +
                ", createTime=" + createTime +
                ", swim_status=" + swimStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(id, userInfo.id) &&
                Objects.equals(status, userInfo.status) &&
                Objects.equals(authority, userInfo.authority) &&
                Objects.equals(createTime, userInfo.createTime) &&
                Objects.equals(swimStatus, userInfo.swimStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, authority, createTime, swimStatus);
    }


}
