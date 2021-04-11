package com.llh.entity;

public class User {
    private int uID;         //系统给用户的编号，用于对表操作时定位
    private String userName;     //用户昵称，可自行修改
    private String phoneNumber;//用户登录账号，不可修改
    private String password; //用户密码
    private double balance;  //用户余额
    private int status;   //身份（用户或管理员）
    private String headImg;//头像

    //无参构造
    public User() {}

    public User(int uID, String userName, String phoneNumber, String password, double balance, int status, String headImg) {
        this.uID = uID;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.balance = balance;
        this.status = status;
        this.headImg = headImg;
    }

    //setter and getter
    public int getuID() {
        return uID;
    }

    public void setuID(int uID) {
        this.uID = uID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
