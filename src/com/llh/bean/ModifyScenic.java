package com.llh.bean;

public class ModifyScenic {
    private  String img;
    private  String name;
    private  String local;
    private  double price;
    private  int numberTickets;
    private  String des;
    private  String openTime;

    public ModifyScenic(String img, String name, String local, double price, int numberTickets, String des, String openTime) {
        this.img = img;
        this.name = name;
        this.local = local;
        this.price = price;
        this.numberTickets = numberTickets;
        this.des = des;
        this.openTime = openTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberTickets() {
        return numberTickets;
    }

    public void setNumberTickets(int numberTickets) {
        this.numberTickets = numberTickets;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }
}
