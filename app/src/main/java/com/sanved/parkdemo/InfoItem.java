package com.sanved.parkdemo;

/**
 * Created by Sanved on 11-07-2018.
 */

public class InfoItem {

    String imgid, desc;

    InfoItem(String imgid, String desc){
        this.imgid = imgid;
        this.desc = desc;
    }

    public String getImgid() {
        return imgid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setImgid(String imgid) {
        this.imgid = imgid;
    }
}
