package com.sanved.parkdemo;

/**
 * Created by Sanved on 26-06-2018.
 */

public class ParkChiMahiti{

    String title, desc, imgid, fireID, phno;

    int open, close;

    double latt, longg;

    // Features/Activities
    boolean bicycle = false ,
            cafe = false ,
            fair = false ,
            parking = false ,
            photo = false ,
            ticket  = false ,
            wifi = false ;

    // TODO: 11-07-2018 Add and remove extra and get icons from freepik.com 

    public ParkChiMahiti(String title, String desc, String imgid, String fireID, int open, int close, double latt, double longg, String phno) {

        this.title = title;
        this.desc = desc;
        this.imgid = imgid;
        this.fireID = fireID;
        this.open = open;
        this.close = close;
        this.latt = latt;
        this.longg = longg;
        this.phno = phno;

    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getImgid() {
        return imgid;
    }

    public String getFireID() {
        return fireID;
    }

    public int getClose() {
        return close;
    }

    public int getOpen() {
        return open;
    }

    public void setBicycle(boolean bicycle) {
        this.bicycle = bicycle;
    }

    public void setCafe(boolean cafe) {
        this.cafe = cafe;
    }

    public void setFair(boolean fair) {
        this.fair = fair;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public void setPhoto(boolean photo) {
        this.photo = photo;
    }

    public void setTicket(boolean ticket) {
        this.ticket = ticket;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isCafe() {
        return cafe;
    }

    public boolean isBicycle() {
        return bicycle;
    }

    public boolean isFair() {
        return fair;
    }

    public boolean isParking() {
        return parking;
    }

    public boolean isPhoto() {
        return photo;
    }

    public boolean isTicket() {
        return ticket;
    }

    public boolean isWifi() {
        return wifi;
    }

    public double getLatt() {
        return latt;
    }

    public double getLongg() {
        return longg;
    }

    public String getPhno() {
        return phno;
    }
}
