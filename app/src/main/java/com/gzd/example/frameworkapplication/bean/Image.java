package com.gzd.example.frameworkapplication.bean;

import java.util.Date;

/**
 * Created by gzd on 2019/2/25 0025
 */
public class Image {
    private String name;
    private Date date;
    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
