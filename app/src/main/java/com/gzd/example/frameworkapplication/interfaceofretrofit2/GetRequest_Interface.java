package com.gzd.example.frameworkapplication.interfaceofretrofit2;

import com.gzd.example.frameworkapplication.bean.Person;

import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gzd on 2019/2/25 0025
 */
public interface GetRequest_Interface {
    @Headers("Authorization: authorization")
    @GET("/person/{id}/message")
    Call<Person> getPerson(@Path("id") int id, @Header("Authorization") String authorization, @Query("name") String equal);

    @POST("/person/")
    @FormUrlEncoded
    Call<Person> addPerson(@Field("name") String name, @Field("age") int age, @Body Person person, @FieldMap Map<String,String> map);

    @POST("/person/file/")
    @Multipart
    Call<Person> uploadFile(@Part("name") String name, @Part("age") int age, @Part MultipartBody.Part file);
}
