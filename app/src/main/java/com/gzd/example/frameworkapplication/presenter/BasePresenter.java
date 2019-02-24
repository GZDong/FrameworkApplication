package com.gzd.example.frameworkapplication.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by gzd on 2019/2/25 0025
 */
public abstract class BasePresenter<V> {

    protected Reference<V> mViewRef;

    public void attachView(V view){
        mViewRef = new WeakReference<>(view);
    }

    public void detachView(){
        if (mViewRef!=null){
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected V getView(){
        return mViewRef.get();
    }
}
