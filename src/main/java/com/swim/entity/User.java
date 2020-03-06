package com.swim.entity;

import java.util.Objects;

public class User {
    private Integer id;
    private String username;
    private String password;
    private UserInfo userInfo;
    private UserMessage userMessage;
    private Account account;
    private Member member;
    private SwimHistory swimHistory;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userInfo=" + userInfo +
                ", userMessage=" + userMessage +
                ", account=" + account +
                ", member=" + member +
                ", swimHistory=" + swimHistory +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(userInfo, user.userInfo) &&
                Objects.equals(userMessage, user.userMessage) &&
                Objects.equals(account, user.account) &&
                Objects.equals(member, user.member) &&
                Objects.equals(swimHistory, user.swimHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, userInfo, userMessage, account, member, swimHistory);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public UserMessage getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(UserMessage userMessage) {
        this.userMessage = userMessage;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public SwimHistory getSwimHistory() {
        return swimHistory;
    }

    public void setSwimHistory(SwimHistory swimHistory) {
        this.swimHistory = swimHistory;
    }
}
