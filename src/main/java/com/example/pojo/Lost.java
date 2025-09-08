package com.example.pojo;

import android.provider.ContactsContract;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

import lombok.Data;
@Data
public class Lost {
    private String lostName;
    private String lostAddress;
    private String lostUseName;
    private String lostUserPhone;
    private String lostWechat;
    private String lostDesc;
    private String picUrl;
    private String time;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date creattime;
    String photo1;
    byte[] photo;

    public Lost() {
    }

    public Lost(String lostName, String lostAddress, String lostUseName, String lostUserPhone, String lostWechat, String lostDesc, String picUrl,String time, String photo1,byte[] photo) {
        this.lostName = lostName;
        this.lostAddress = lostAddress;
        this.lostUseName = lostUseName;
        this.lostUserPhone = lostUserPhone;
        this.lostWechat = lostWechat;
        this.lostDesc = lostDesc;
        this.picUrl = picUrl;
        this.time=time;
        this.photo =photo;
        this.photo1 = photo1;
    }
    public byte[] getPhoto(){return photo;}

    public void setPhoto(byte[] photo){this.photo=photo;}

    public String getPhoto1() {
        return photo1;
    }

    public void setPhoto1(String photo1) {
        this.photo1 = photo1;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLostName() {
        return lostName;
    }

    public void setLostName(String lostName) {
        this.lostName = lostName;
    }

    public String getLostAddress() {
        return lostAddress;
    }

    public void setLostAddress(String lostAddress) {
        this.lostAddress = lostAddress;
    }

    public String getLostUseName() {
        return lostUseName;
    }

    public void setLostUseName(String lostUseName) {
        this.lostUseName = lostUseName;
    }

    public String getLostUserPhone() {
        return lostUserPhone;
    }

    public void setLostUserPhone(String lostUserPhone) {
        this.lostUserPhone = lostUserPhone;
    }

    public String getLostWechat() {
        return lostWechat;
    }

    public void setLostWechat(String lostWechat) {
        this.lostWechat = lostWechat;
    }

    public String getLostDesc() {
        return lostDesc;
    }

    public void setLostDesc(String lostDesc) {
        this.lostDesc = lostDesc;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
