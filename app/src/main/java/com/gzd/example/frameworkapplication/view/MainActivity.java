package com.gzd.example.frameworkapplication.view;

import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gzd.example.frameworkapplication.R;
import com.gzd.example.frameworkapplication.presenter.ImagePresenter;

import java.util.List;

public class MainActivity extends MVPBaseActivity<ImageInterface,ImagePresenter> implements ImageInterface{
    private ImageView mImage1;
    private ImageView mImage2;
    private List<String> urls;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1){
                Glide.with(MainActivity.this).load(R.drawable.ic_launcher_foreground)
                        .override(200,200).into(mImage1);
                Glide.with(MainActivity.this).load(R.drawable.ic_launcher_background)
                        .override(200,200)
                        .thumbnail(0.1f)
                        .fitCenter().into(mImage2);
            }
            return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mPresenter.loadImageToActivity();
    }

    private void initView(){
        mImage1 = findViewById(R.id.img_1);
        mImage2 = findViewById(R.id.img_2);
    }

    @Override
    public ImagePresenter createPresenter() {
        return new ImagePresenter();
    }

    @Override
    public void loadImage(List<String> urls) {
        this.urls = urls;
        Message message = new Message();
        message.what = 1;
        mHandler.sendMessage(message);
    }
}
