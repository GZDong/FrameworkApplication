package com.gzd.example.frameworkapplication.model;

/**
 * Created by gzd on 2019/2/25 0025
 */
public interface DataListener<T> {
    void onFinish(T result);
    void onError();
}
