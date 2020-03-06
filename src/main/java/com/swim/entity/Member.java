package com.swim.entity;

import java.sql.Date;
import java.util.Objects;

public class Member {
    private Integer id;
    private Date endTime;
    private Boolean memberStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Boolean memberStatus) {
        this.memberStatus = memberStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id) &&
                Objects.equals(endTime, member.endTime) &&
                Objects.equals(memberStatus, member.memberStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endTime, memberStatus);
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", endTime=" + endTime +
                ", memberStatus=" + memberStatus +
                '}';
    }
}
