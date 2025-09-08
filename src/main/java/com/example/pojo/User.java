package com.example.pojo;

public class User {
    private String username;
    private String wechat;
    private String phone;
    private String address;
    private String password;

    public User(){}
    public User(String username, String wechat, String phone, String address, String password) {
        this.username = username;
        this.wechat = wechat;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}