package com.llh.bean;

import java.util.Date;

public class HistoryOrder {
    private int orderId;
    private String scenicName;
    private Date orderTime;
    private Date scheduleTime;
    private int ticketNum;
    private double total;

    public HistoryOrder(int orderId, String scenicName, Date orderTime, Date scheduleTime, int ticketNum, double total) {
        this.orderId = orderId;
        this.scenicName = scenicName;
        this.orderTime = orderTime;
        this.scheduleTime = scheduleTime;
        this.ticketNum = ticketNum;
        this.total = total;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getScenicName() {
        return scenicName;
    }

    public void setScenicName(String scenicName) {
        this.scenicName = scenicName;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(Date scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
