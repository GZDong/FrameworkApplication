package com.gzd.example.frameworkapplication.model;

import com.gzd.example.frameworkapplication.bean.Image;
import com.gzd.example.frameworkapplication.interfaceofretrofit2.Image_Interface;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gzd on 2019/2/25 0025
 */
public class ImageModel {
    private Retrofit retrofit;
    private Image_Interface image_interface ;
    private ImageModel(){
        retrofit = new Retrofit.Builder()
                .baseUrl("www.gzd.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        image_interface = retrofit.create(Image_Interface.class);
    }

    public static ImageModel getInstance(){
        return ImageModelHolder.instance;
    }
    private static class ImageModelHolder{
        private static final ImageModel instance = new ImageModel();
    }

    public void getImageUrl(final DataListener<List<String>> listener){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                List<String> urls = new ArrayList<>();
//                urls.add("http://photos.paulhayes.photography/1720/tiger.jpg");
//                urls.add("http://images1.fanpop.com/images/photos/1700000/my-fav-picture-brad-pitt-1729043-1143-1650.jpg");
//                listener.onFinish(urls);
//            }
//        }).start();
        Call<Image> getCall = image_interface.getImage("2");
        getCall.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                response.body().getName();
                response.body().getDate();
                response.body().getStatus();
                Image image = response.body();
                listener.onFinish(new ArrayList<String>());
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {

            }
        });

    }

    public void addImage(final DataListener<Image> listener){
        MediaType type = MediaType.parse("text/plain");
        RequestBody name = RequestBody.create(type, "newImage");
        RequestBody date = RequestBody.create(type, "now");
        RequestBody f = RequestBody.create(MediaType.parse("application/octet-stream"),new File("test"));
        MultipartBody.Part file = MultipartBody.Part.createFormData("file","test.txt",f);
        Call<Image> addCall = image_interface.addImage(name,date,file);
        addCall.enqueue(new Callback<Image>() {
            @Override
            public void onResponse(Call<Image> call, Response<Image> response) {
                listener.onFinish(response.body());
            }

            @Override
            public void onFailure(Call<Image> call, Throwable t) {
                listener.onError();
            }
        });
    }
}
