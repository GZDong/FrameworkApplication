package com.gzd.example.frameworkapplication.interfaceofretrofit2;

import com.gzd.example.frameworkapplication.bean.Image;

import java.util.Date;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

/**
 * Created by gzd on 2019/2/25 0025
 */
public interface Image_Interface {
    @GET("/images/{id}")
    Call<Image> getImage(@Path("id") String id);

    @POST("/images/")
    @Multipart
    Call<Image> addImage(@Part("name") RequestBody name, @Part("date")RequestBody date, @Part MultipartBody.Part img);

    @DELETE("/images/{id}")
    Call<Image> deleteImage(@Path("id") String name);

    @PUT("/images/")
    @Multipart
    Call<Image> updateImage(@Part("id") String name, @Part MultipartBody.Part img);
}
