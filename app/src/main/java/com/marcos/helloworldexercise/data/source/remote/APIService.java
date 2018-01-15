package com.marcos.helloworldexercise.data.source.remote;

import com.marcos.helloworldexercise.data.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by markc on 13/01/2018.
 */

public interface APIService {

    @GET("getall")
    Call<List<User>> getUsers();

    @GET("get/{id}")
    Call<User> getUser(@Path("id") Integer id);

    @POST("create")
    Call<User> createUser(@Body User user);

    @POST("update")
    Call<User> updateUser(@Body User user);

    @GET("remove/{id}")
    Call<Void> removeUser(@Path("id") Integer id);

}
