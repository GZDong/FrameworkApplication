package com.gzd.example.frameworkapplication.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gzd.example.frameworkapplication.presenter.BasePresenter;

/**
 * Created by gzd on 2019/2/25 0025
 */
public abstract class MVPBaseActivity<V,P extends BasePresenter<V>> extends AppCompatActivity {
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public abstract P createPresenter();
}
