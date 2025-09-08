package com.example.pojo;


import lombok.Data;

@Data
public class Pick {
    public String pickName;
    public String pickPhone;
    public String pickWechat;
    public String pickAddress;
    public String pickUserAddress;
    public String pickTime;
    public String picUrl;
    public String time;
    public byte[] photo;
    String photo1;

    public Pick(String pickName, String pickPhone, String pickWechat, String pickAddress, String pickUserAddress, String pickTime, String picUrl, String time, String photo1,byte[] photo) {
        this.pickName = pickName;
        this.pickPhone = pickPhone;
        this.pickWechat = pickWechat;
        this.pickAddress = pickAddress;
        this.pickUserAddress = pickUserAddress;
        this.pickTime = pickTime;
        this.picUrl = picUrl;
        this.time = time;
        this.photo=photo;
        this.photo1 = photo1;
    }
    public Pick(){}


    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public byte[] getPhoto(){return photo;}

    public void setPhoto(byte[] photo){this.photo=photo;}

    public String getPickName() {
        return pickName;
    }

    public void setPickName(String pickName) {
        this.pickName = pickName;
    }

    public String getPickPhone() {
        return pickPhone;
    }

    public void setPickPhone(String pickPhone) {
        this.pickPhone = pickPhone;
    }

    public String getPickWechat() {
        return pickWechat;
    }

    public void setPickWechat(String pickWechat) {
        this.pickWechat = pickWechat;
    }

    public String getPickAddress() {
        return pickAddress;
    }

    public void setPickAddress(String pickAddress) {
        this.pickAddress = pickAddress;
    }

    public String getPickUserAddress() {
        return pickUserAddress;
    }

    public void setPickUserAddress(String pickUserAddress) {
        this.pickUserAddress = pickUserAddress;
    }

    public String getPickTime() {
        return pickTime;
    }

    public void setPickTime(String pickTime) {
        this.pickTime = pickTime;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
