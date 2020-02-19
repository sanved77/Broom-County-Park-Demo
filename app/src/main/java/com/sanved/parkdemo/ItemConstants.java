package com.sanved.parkdemo;

/**
 * Created by Sanved on 11-07-2018.
 */

public class ItemConstants {

    String[] imgids = {"","bicycle.png","cafe.png","fair.png","parking.png","photo.png","ticket.png","wifi.png"};
    String[] descs = {"","Bicycle Track","Cafe","Fair","Parking","Photography Allowed","Entry Ticket","WiFi"};

    public InfoItem getItem(int position){

        InfoItem temp = new InfoItem(imgids[position], descs[position]);

        return temp;
    }

}
