package com.gzd.example.frameworkapplication.bean;

import java.util.List;

/**
 * Created by gzd on 2019/2/25 0025
 */
public class Person {
    private String name;
    private int age;
    private List<Image> mImages;

    public List<Image> getImages() {
        return mImages;
    }

    public void setImages(List<Image> images) {
        mImages = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
