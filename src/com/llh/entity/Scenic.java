package com.llh.entity;

public class Scenic {
    private int sID;               //系统给景点的编号，用于对表操作时定位
    private String scenicName;     //景点名称
    private String scenicDes;      //详细描述
    private String location;       //所在地
    private int commentsNum;       //评论数
    private double price;          //用户余额
    private String openTime;       //开放时间
    private int ticketsNumber;     //门票数
    private String scenicImg;      //图片

    public Scenic() {}

    public Scenic(int sID, String scenicName, String scenicDes, String location, int commentsNum, double price, String openTime, int ticketsNumber, String scenicImg) {
        this.sID = sID;                 //id
        this.scenicName = scenicName;//名字
        this.scenicDes = scenicDes;//描述
        this.location = location;//地点
        this.commentsNum = commentsNum;//评论数
        this.price = price;//门票
        this.openTime = openTime;//开放时间
        this.ticketsNumber = ticketsNumber;//门票数
        this.scenicImg = scenicImg;//图像
    }

    public int getsID() {
        return sID;
    }

    public void setsID(int sID) {
        this.sID = sID;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public String getScenicDes() {
        return scenicDes;
    }

    public void setScenicDes(String scenicDes) {
        this.scenicDes = scenicDes;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public int getTicketsNumber() {
        return ticketsNumber;
    }

    public void setTicketsNumber(int ticketsNumber) {
        this.ticketsNumber = ticketsNumber;
    }

    public String getScenicImg() {
        return scenicImg;
    }

    public void setScenicImg(String scenicImg) {
        this.scenicImg = scenicImg;
    }

    @Override
    public String toString() {
        return "Scenic{" +
                "sID=" + sID +
                ", scenicName='" + scenicName + '\'' +
                ", scenicDes='" + scenicDes + '\'' +
                ", location='" + location + '\'' +
                ", commentsNum=" + commentsNum +
                ", price=" + price +
                ", openTime='" + openTime + '\'' +
                ", ticketsNumber=" + ticketsNumber +
                ", scenicImg='" + scenicImg + '\'' +
                '}';
    }
}
