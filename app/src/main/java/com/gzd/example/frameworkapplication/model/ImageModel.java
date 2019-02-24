package com.gzd.example.frameworkapplication.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gzd on 2019/2/25 0025
 */
public class ImageModel {
    private ImageModel(){}

    public static ImageModel getInstance(){
        return ImageModelHolder.instance;
    }
    private static class ImageModelHolder{
        private static final ImageModel instance = new ImageModel();
    }

    public void getImageUrl(final DataListener<List<String>> listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<String> urls = new ArrayList<>();
                urls.add("http://photos.paulhayes.photography/1720/tiger.jpg");
                urls.add("http://images1.fanpop.com/images/photos/1700000/my-fav-picture-brad-pitt-1729043-1143-1650.jpg");
                listener.onFinish(urls);
            }
        }).start();
    }
}
